# PowerShell Script to Test API and Fix 403 Forbidden Error

Write-Host "=== API Testing Script ===" -ForegroundColor Green

# Step 1: Register a user
Write-Host "`n1. Registering a user..." -ForegroundColor Yellow
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
    if ($_.Exception.Response.StatusCode -eq 400) {
        Write-Host "User might already exist, continuing..." -ForegroundColor Yellow
    } else {
        Write-Host "Error registering user: $($_.Exception.Message)" -ForegroundColor Red
        exit 1
    }
}

# Step 2: Login to get JWT token
Write-Host "`n2. Logging in to get JWT token..." -ForegroundColor Yellow
$loginBody = @{
    username = "testuser"
    password = "password123"
} | ConvertTo-Json

try {
    $loginResponse = Invoke-RestMethod -Uri "http://localhost:8083/api/v1/auth/login" -Method POST -Body $loginBody -ContentType "application/json"
    $token = $loginResponse.token
    Write-Host "Login successful! Token received." -ForegroundColor Green
    Write-Host "Token: $($token.Substring(0, 20))..." -ForegroundColor Cyan
} catch {
    Write-Host "Error logging in: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# Step 3: Create leave request with JWT token
Write-Host "`n3. Creating leave request with JWT token..." -ForegroundColor Yellow
$demandeBody = @{
    typeConge = "Annuel"
    employe = @{
        id = 1
    }
    manager = @{
        id = 1
    }
    dateDebut = "2025-08-06"
    dateFin = "2025-08-16"
} | ConvertTo-Json

$headers = @{
    "Content-Type" = "application/json"
    "Authorization" = "Bearer $token"
}

try {
    $demandeResponse = Invoke-RestMethod -Uri "http://localhost:8083/api/demandes" -Method POST -Body $demandeBody -Headers $headers
    Write-Host "Leave request created successfully!" -ForegroundColor Green
    Write-Host "Response: $($demandeResponse | ConvertTo-Json)" -ForegroundColor Cyan
} catch {
    Write-Host "Error creating leave request: $($_.Exception.Message)" -ForegroundColor Red
    if ($_.Exception.Response.StatusCode -eq 403) {
        Write-Host "403 Forbidden - This means the JWT token is not being accepted." -ForegroundColor Red
        Write-Host "Possible causes:" -ForegroundColor Yellow
        Write-Host "1. Token is invalid or expired" -ForegroundColor Yellow
        Write-Host "2. Token is not being sent correctly" -ForegroundColor Yellow
        Write-Host "3. Backend security configuration issue" -ForegroundColor Yellow
    }
    exit 1
}

Write-Host "`n=== Test completed successfully! ===" -ForegroundColor Green
