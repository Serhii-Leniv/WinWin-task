# Microservices System: Auth API and Data API

### Prerequisites
* Docker Desktop must be installed and active.

### Deployment
1. Clone the repository to your local machine.
2. From the root directory, execute the following command:
```bash
docker-compose up -d --build
Testing the System

1. User Registration

Endpoint: POST http://localhost:8080/api/auth/register

Payload (JSON):

JSON
{
  "email": "user@example.com",
  "password": "password123"
}
2. User Authentication

Endpoint: POST http://localhost:8080/api/auth/login

Payload (JSON): Use credentials from the registration step.

Response: Copy the token value for the Authorization header.

3. Protected Processing

Endpoint: POST http://localhost:8080/api/process

Header: Authorization: Bearer <YOUR_TOKEN>

Body (Raw Text): Any text string (e.g., "winwin travel test")

4. Database Verification

To confirm audit logs in PostgreSQL, run:

Bash
docker exec -it winwin-db psql -U postgres -d winwin_db -c "SELECT * FROM processing_log;"
Security and Implementation Details

Stateless Auth: The system uses JWT for stateless session management.

Inter-service Security: Service B is protected via a shared secret (X-Internal-Token header).
