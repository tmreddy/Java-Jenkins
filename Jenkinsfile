pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/tmreddy/Java-Jenkins.git', branch: 'main'
                sh 'ls -R'  // debug: verify where pom.xml is
            }
        }

        stage('Build') {
            steps {
                dir('src') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                dir('src') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit 'src/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                dir('src') {
                    sh 'mvn package'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: 'src/target/*.jar', fingerprint: true
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
