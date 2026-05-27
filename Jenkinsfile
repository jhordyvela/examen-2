pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test jacoco:report'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

        stage('Docker Build & Deploy') {
            steps {
                bat 'docker stop api-orders-container || exit 0'
                bat 'docker rm api-orders-container || exit 0'
                bat 'docker build -t api-orders .'
                bat 'docker run -d -p 8080:8080 --name api-orders-container api-orders'
            }
        }
    }
}