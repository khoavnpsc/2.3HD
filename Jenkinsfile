pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                echo "Building using Maven"
                bat "mvn clean package"
                echo "Build complete"
            }
        }
        
        stage("Unit Tests") {
            steps {
                echo "Unit testing using JUnit"
                bat "mvn test"
                echo "Unit test complete"
            }
        }
        
        stage("Code Analysis") {
            steps {
                withSonarQubeEnv(installationName: 'sq1') {
                    echo "Analyzing code using SonarQube"
                    bat ".\\mvnw.cmd org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar"
                    echo "Code Analysis complete"
                }
            }
        }
        
        stage("Build Docker Image") {
            steps {
                echo "Docker Image building"
                bat "docker build -t fibonacci-app ."
                echo "Image build completed"
            }
        }
        
        stage("Pushing to Dockerhub") {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    echo "Logging in"
                    bat "docker login -u khoavnpsc -p ${dockerhubpwd}"
                    bat "docker tag fibonacci-app khoavnpsc/fibonacci-app:latest"
                    bat "docker push khoavnpsc/fibonacci-app:latest"
                    bat "docker-compose down"
                    bat "docker-compose up -d --force-recreate"
                    echo "Image pushed to Dockerhub successfully"
                }
            }
        }
        
        stage("Deploy") {
            steps {
                echo "Deploy with Docker Compose"
                bat "docker-compose down"
                bat "docker-compose up -d --force-recreate"
                echo "Deploy completed"
            }
        }
    }

    post {
        always {
            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'C:\\Users\\khoav\\OneDrive\\Desktop',
                reportFiles: 'screenshot_*.png',
                reportName: 'Screenshots'
            ])
        }
    }
}