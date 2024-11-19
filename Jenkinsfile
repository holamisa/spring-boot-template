pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
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
        stage('Dockerize') {
            steps {
                sh '''
                    docker stop spring-boot-template || true
                    docker rm spring-boot-template || true
                    docker rmi spring-boot-template || true
                    docker build -t spring-boot-template:${BUILD_NUMBER} ./module-web-api
                '''
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                    docker run -d --name spring-boot-template -p 8081:8081 spring-boot-template:${BUILD_NUMBER}
                '''
            }
        }
    }
    post {
        always {
            // Cleanup steps go here directly
            sh '''
                echo "Performing cleanup..."
                docker system prune -f
            '''
        }
    }
}