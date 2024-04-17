pipeline {
    agent any
    stages {
        stage('Jenkins Partner') {
            steps {
                echo 'Partner Service'
            }
        }
        stage('Build Interface') { 
            steps {
                build job: 'store.partner', wait: true
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }  
        stage('Build Image'){
            steps {
                script {
                    partner = docker.build("alemagno10/partner:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }    
        stage('Push Image'){
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        partner.push("${env.BUILD_ID}")
                        partner.push("latest")
                    }
                }
            }
        }
    }
}