# Task Description

Document AWS serverless architecture. Lambda: create function (Java/Node.js/Python), configure trigger (S3 upload, API Gateway, DynamoDB stream), set memory and timeout, deploy. API Gateway: create REST API, define resources and methods, integrate with Lambda, deploy to stages (dev, staging, prod), configure throttling and API keys. Show end-to-end flow: client calls API Gateway → triggers Lambda → Lambda processes → stores in DynamoDB → returns response. Include pricing: Lambda pay-per-invocation, API Gateway pay-per-request.
