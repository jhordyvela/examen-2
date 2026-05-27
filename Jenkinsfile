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
                sh 'mvn clean test jacoco:report'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
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