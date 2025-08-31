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
                sh '''
                    echo "=== Repo Structure ==="
                    pwd
                    ls -R
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
        steps {
            dir('src') {
                sh 'mvn test'
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
