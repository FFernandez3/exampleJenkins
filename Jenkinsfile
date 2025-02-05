pipeline {
    agent any

    environment {
        IMAGE_NAME = 'jenkins-springboot'
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
                    sh '''
                        ls -la
                        docker run --rm \
                        -v "${WORKSPACE}:/var/jenkins_home/workspace/spring_pipeline" \
                        -w /var/jenkins_home/workspace/spring_pipeline \
                        jenkins-springboot mvn test
                    '''
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
                failure {
                    echo "Los tests han fallado"
                }
            }
        }
    }
}
