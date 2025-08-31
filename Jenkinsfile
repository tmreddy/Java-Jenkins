pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2'  // optional: cache Maven dependencies
        }
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/tmreddy/Java-Jenkins.git', branch: 'main'
            }
        }

        stage('Verify Tools') {
            steps {
                sh 'java -version'
                sh 'mvn -v'
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
            echo '✅ Build succeeded inside Docker!'
        }
        failure {
            echo '❌ Build failed inside Docker!'
        }
    }
}
