# Task Description

Document launching an AWS EC2 instance. Steps: sign into AWS Console, navigate to EC2, click Launch Instance, choose Amazon Linux 2 AMI, select t2.micro (free tier), configure security group (SSH port 22 from your IP, HTTP port 80, HTTPS port 443), create/download key pair (.pem file), launch. Connect via SSH: ssh -i key.pem ec2-user@public-ip. Install Java, run Spring Boot application. Document AMIs (custom images), instance types (compute optimized, memory optimized), and auto-scaling groups.
