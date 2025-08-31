pipeline {
    agent any

    tools {
        maven 'Maven3'   // Configure "Maven3" in Jenkins global tools
        jdk 'Java21'     // Configure "Java17" in Jenkins global tools
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/tmreddy/Java-Jenkins', branch: 'main'
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
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
    }
    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
