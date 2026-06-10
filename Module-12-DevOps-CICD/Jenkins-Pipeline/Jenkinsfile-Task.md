# Task Description

Create a Jenkinsfile (declarative pipeline) for a Java project. Stages: Checkout (git clone from repository), Build (mvn clean compile), Unit Test (mvn test with JUnit reports), Code Quality (mvn sonar:sonar), Integration Test (mvn verify), Package (mvn package -DskipTests), Deploy (docker build and push to registry). Include post-build actions: archive artifacts (JAR file), publish test results, send email/Slack notification on success/failure. Use environment variables for credentials. Show parallel stages for independent tasks.
