pipeline {
    agent any

    environment {
        IMAGE_NAME = 'example-jenkins'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/FFernandez3/exampleJenkins.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t $IMAGE_NAME .'
                }
            }
        }

        stage('Run Tests in Container') {
            steps {
                script {
                    sh 'docker run --rm $IMAGE_NAME mvn test'
                }
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
    }
}
