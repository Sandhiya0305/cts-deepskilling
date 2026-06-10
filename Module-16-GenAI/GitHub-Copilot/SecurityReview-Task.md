# Task Description

Document security practices for AI-generated code. Risks: hardcoded secrets (API keys, passwords), SQL injection vulnerabilities, insecure dependencies, XSS vulnerabilities, logic flaws. Review checklist: scan for secrets before committing, validate all inputs, check for proper error handling, verify SQL uses parameterized queries, ensure authentication/authorization is correct, run security linters (SonarQube, Snyk). Never commit AI-generated code without human review. Include examples of vulnerable AI-generated code and the corrected versions.
