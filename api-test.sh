#!/bin/bash
# Define the base URL for your API
BASE_URL="http://localhost:8080/api"  # Change this to the correct URL if needed

# Define the output file
OUTPUT_FILE="api_test_output.txt"

# Function to log the output to a file
log_output() {
    echo "$1" >> $OUTPUT_FILE
}

# Clean the output file before starting
> $OUTPUT_FILE

# 1. Test POST /tasks (Create Task)
echo "Testing POST /tasks..."
POST_PAYLOAD='{
    "title": "Sample Task",
    "description": "This is a sample task",
    "priority": "HIGH",
    "dueDate": "2024-12-01",
    "assignee": "John Doe"
}'

# Execute POST request and capture response
POST_RESPONSE=$(curl -s -w "%{http_code}" -X POST -H "Content-Type: application/json" -d "$POST_PAYLOAD" "$BASE_URL/tasks")
# Extract task ID from the response (assuming the task ID is in the response body as JSON)
TASK_ID=$(echo "$POST_RESPONSE" | jq -r '.id')  # Use jq to parse JSON and get the id field

# Check if the task ID was successfully extracted
if [[ -z "$TASK_ID" ]]; then
    log_output "Error: Task creation failed, no task ID returned."
    exit 1
fi

log_output "POST /tasks Response: Task ID $TASK_ID created successfully."

# 2. Test GET /tasks (List Tasks)
echo "Testing GET /tasks..."
GET_RESPONSE=$(curl -s -w "%{http_code}" -X GET "$BASE_URL/tasks")
log_output "GET /tasks Response: $GET_RESPONSE"

# 3. Test GET /tasks/{id} (Get Task by ID)
echo "Testing GET /tasks/$TASK_ID..."
GET_TASK_RESPONSE=$(curl -s -w "%{http_code}" -X GET "$BASE_URL/tasks/$TASK_ID")
log_output "GET /tasks/$TASK_ID Response: $GET_TASK_RESPONSE"

# 4. Test PUT /tasks/{id} (Update Task)
echo "Testing PUT /tasks/$TASK_ID..."
PUT_PAYLOAD='{
    "title": "Updated Task",
    "description": "This is an updated task description",
    "priority": "LOW",
    "dueDate": "2024-12-15",
    "assignee": "Jane Doe"
}'

PUT_RESPONSE=$(curl -s -w "%{http_code}" -X PUT -H "Content-Type: application/json" -d "$PUT_PAYLOAD" "$BASE_URL/tasks/$TASK_ID")
log_output "PUT /tasks/$TASK_ID Response: $PUT_RESPONSE"

# 5. Test DELETE /tasks/{id} (Delete Task)
echo "Testing DELETE /tasks/$TASK_ID..."
DELETE_RESPONSE=$(curl -s -w "%{http_code}" -X DELETE "$BASE_URL/tasks/$TASK_ID")
log_output "DELETE /tasks/$TASK_ID Response: $DELETE_RESPONSE"

# 6. Test POST /tasks/{id}/comments (Add Comment)
echo "Testing POST /tasks/$TASK_ID/comments..."
COMMENT_PAYLOAD='{
    "text": "This is a comment on the task",
    "author": "John Doe"
}'

COMMENT_RESPONSE=$(curl -s -w "%{http_code}" -X POST -H "Content-Type: application/json" -d "$COMMENT_PAYLOAD" "$BASE_URL/tasks/$TASK_ID/comments")
log_output "POST /tasks/$TASK_ID/comments Response: $COMMENT_RESPONSE"

# 7. Test GET /tasks/search (Search Tasks)
echo "Testing GET /tasks/search..."
SEARCH_RESPONSE=$(curl -s -w "%{http_code}" -X GET "$BASE_URL/tasks/search?keyword=sample")
log_output "GET /tasks/search Response: $SEARCH_RESPONSE"

# Final message
log_output "API Testing completed."

echo "API tests completed. Check the output in '$OUTPUT_FILE'."
