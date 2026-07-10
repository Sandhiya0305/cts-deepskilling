# Task Description

Create SonarQube configuration file for a Maven project. Set properties: sonar.projectKey=dn5-java-fse, sonar.projectName=Digital Nurture Java FSE, sonar.host.url=http://localhost:9000, sonar.login=your-generated-token. Configure analysis scope: sonar.sources=src/main/java, sonar.tests=src/test/java, sonar.java.binaries=target/classes, sonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml. Add exclusions: sonar.exclusions=**/model/**,**/dto/**,**/config/**. Include quality gate wait property.
