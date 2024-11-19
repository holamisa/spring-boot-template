pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'bongjaejeong/spring-boot-template-pre'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/pre']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/holamisa/spring-boot-template.git',
                        credentialsId: 'git_access_token'
                    ]]
                ])
            }
        }
        stage('Set Gradlew Permissions') {
            steps {
                sh 'chmod +x ./gradlew'
            }
        }
        stage('Build') {
            steps {
                sh './gradlew :module-web-api:clean :module-web-api:build'
            }
        }
        stage('Docker Build & Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker_hub_credentials',
                                                  usernameVariable: 'USERNAME',
                                                  passwordVariable: 'PASSWORD')]) {
                    sh '''
                        docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} -t ${DOCKER_IMAGE}:latest ./module-web-api
                        echo "${PASSWORD}" | docker login -u "${USERNAME}" --password-stdin
                        docker push ${DOCKER_IMAGE}:${BUILD_NUMBER}
                        docker push ${DOCKER_IMAGE}:latest
                    '''
                }
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                    docker pull ${DOCKER_IMAGE}:${BUILD_NUMBER}
                    docker stop spring-boot-template-pre || true
                    docker rm spring-boot-template-pre || true
                    docker run -d --name spring-boot-template-pre -p 8082:8082 ${DOCKER_IMAGE}:${BUILD_NUMBER}
                '''
            }
        }
    }
    post {
        always {
            sh '''
                echo "Performing cleanup..."
                docker system prune -f
            '''
        }
    }
}