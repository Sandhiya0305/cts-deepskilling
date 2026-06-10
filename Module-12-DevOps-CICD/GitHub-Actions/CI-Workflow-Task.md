# Task Description

Create a GitHub Actions workflow (.github/workflows/ci.yml). Triggers: push to main, pull_request to main. Jobs: build-and-test (runs-on: ubuntu-latest, steps: checkout, setup JDK 17, cache Maven dependencies, run tests, upload test results), code-quality (runs SonarCloud scan), build-docker (builds Docker image, tags with commit SHA, pushes to GitHub Container Registry). Use matrix strategy to test on multiple Java versions (17, 21). Show artifact upload and download between jobs. Include status badge in README.
