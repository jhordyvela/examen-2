pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/jhordyvela/examen-2.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test jacoco:report'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://sonarqube:9000'
            }
        }

        stage('Docker Build & Deploy') {
            steps {
                sh 'mvn package -DskipTests'
                sh 'docker stop api-orders-container || true'
                sh 'docker rm api-orders-container || true'
                sh 'docker build -t api-orders .'
                sh 'docker run -d -p 8080:8080 --name api-orders-container api-orders'
            }
        }
    }
}