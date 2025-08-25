# Quick test to verify the API works
Write-Host "Testing API..." -ForegroundColor Green

# Test 1: Register a user
Write-Host "1. Registering user..." -ForegroundColor Yellow
$registerBody = @{
    username = "testuser"
    password = "password123"
    name = "Test"
    prenom = "User"
    role = "EMPLOYE"
} | ConvertTo-Json

try {
    $registerResponse = Invoke-RestMethod -Uri "http://localhost:8083/api/v1/auth/register" -Method POST -Body $registerBody -ContentType "application/json"
    Write-Host "User registered successfully!" -ForegroundColor Green
} catch {
    Write-Host "Register error: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# Test 2: Login
Write-Host "2. Logging in..." -ForegroundColor Yellow
$loginBody = @{
    username = "testuser"
    password = "password123"
} | ConvertTo-Json

try {
    $loginResponse = Invoke-RestMethod -Uri "http://localhost:8083/api/v1/auth/login" -Method POST -Body $loginBody -ContentType "application/json"
    $token = $loginResponse.token
    Write-Host "Login successful! Token: $($token.Substring(0, 20))..." -ForegroundColor Green
} catch {
    Write-Host "Login error: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# Test 3: Create leave request with CORRECT JSON format
Write-Host "3. Creating leave request..." -ForegroundColor Yellow
$headers = @{
    "Authorization" = "Bearer $token"
    "Content-Type" = "application/json"
}

$demandeBody = @{
    typeConge = "Annuel"
    employe = @{ id = 1 }
    manager = @{ id = 1 }
    dateDebut = "2025-08-06"
    dateFin = "2025-08-16"
} | ConvertTo-Json

try {
    $demandeResponse = Invoke-RestMethod -Uri "http://localhost:8083/api/demandes" -Method POST -Headers $headers -Body $demandeBody
    Write-Host "Leave request created successfully!" -ForegroundColor Green
    Write-Host "Response: $($demandeResponse | ConvertTo-Json)" -ForegroundColor Cyan
} catch {
    Write-Host "Error creating leave request: $($_.Exception.Message)" -ForegroundColor Red
    if ($_.Exception.Response.StatusCode -eq 403) {
        Write-Host "403 Forbidden - JWT token issue" -ForegroundColor Red
    } elseif ($_.Exception.Response.StatusCode -eq 500) {
        Write-Host "500 Server Error - Check server logs" -ForegroundColor Red
    }
    exit 1
}

Write-Host "All tests passed! API is working correctly." -ForegroundColor Green

