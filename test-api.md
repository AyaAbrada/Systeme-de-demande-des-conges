# API Testing Guide - Fix 403 Forbidden Error

## Step 1: Register a User

First, register a user to create an account:

```bash
curl -X POST http://localhost:8083/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123",
    "name": "Test",
    "prenom": "User",
    "role": "EMPLOYE"
  }'
```

## Step 2: Login to Get JWT Token

Login with the registered user to get a JWT token:

```bash
curl -X POST http://localhost:8083/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

**Expected Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "role": "EMPLOYE"
}
```

## Step 3: Create Leave Request with JWT Token

Use the token from step 2 to create a leave request:

```bash
curl -X POST http://localhost:8083/api/demandes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE" \
  -d '{
    "typeConge": "Annuel",
    "employe": {
      "id": 1
    },
    "manager": {
      "id": 1
    },
    "dateDebut": "2025-08-06",
    "dateFin": "2025-08-16"
  }'
```

## Step 4: Register a Manager (if needed)

If you need a manager for the leave request:

```bash
curl -X POST http://localhost:8083/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "manager1",
    "password": "password123",
    "name": "Manager",
    "prenom": "One",
    "role": "MANAGER"
  }'
```

## Common Issues and Solutions:

### Issue 1: 403 Forbidden
**Cause:** Missing or invalid JWT token
**Solution:** Make sure you're logged in and include the Authorization header

### Issue 2: Invalid Request Format
**Cause:** Wrong JSON structure
**Solution:** Use the correct format with `employe` and `manager` objects containing `id` fields

### Issue 3: User Not Found
**Cause:** Invalid employe or manager ID
**Solution:** Use valid user IDs (usually 1 for the first registered user)

## Testing with Postman:

1. **Register User:**
   - Method: POST
   - URL: `http://localhost:8083/api/v1/auth/register`
   - Body (raw JSON):
   ```json
   {
     "username": "testuser",
     "password": "password123",
     "name": "Test",
     "prenom": "User",
     "role": "EMPLOYE"
   }
   ```

2. **Login:**
   - Method: POST
   - URL: `http://localhost:8083/api/v1/auth/login`
   - Body (raw JSON):
   ```json
   {
     "username": "testuser",
     "password": "password123"
   }
   ```

3. **Create Leave Request:**
   - Method: POST
   - URL: `http://localhost:8083/api/demandes`
   - Headers: 
     - `Content-Type: application/json`
     - `Authorization: Bearer YOUR_TOKEN_HERE`
   - Body (raw JSON):
   ```json
   {
     "typeConge": "Annuel",
     "employe": {
       "id": 1
     },
     "manager": {
       "id": 1
     },
     "dateDebut": "2025-08-06",
     "dateFin": "2025-08-16"
   }
   ```

## Debugging Tips:

1. **Check if the application is running:**
   ```bash
   curl http://localhost:8083/api/v1/auth/login
   ```

2. **Check the logs** for any authentication errors

3. **Verify the JWT token** is valid and not expired

4. **Make sure the user exists** in the database before referencing their ID
