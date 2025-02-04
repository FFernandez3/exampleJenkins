pipeline {
    agent {
        docker {
            image 'maven:3.9.5-eclipse-temurin-17'  // Usa Maven con Java 17 dentro del contenedor
        }
    }

    environment {
        DOCKER_BUILDKIT = 1  // Habilita Docker BuildKit para mejorar el build
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/FFernandez3/exampleJenkins.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'  // Genera reportes de test en Jenkins
                }
                failure {
                    echo "Los tests han fallado"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t example-jenkins .'
                }
            }
        }

        stage('Run Tests in Container') {
            steps {
                script {
                    sh 'docker run --rm example-jenkins'
                }
            }
        }
    }
}
