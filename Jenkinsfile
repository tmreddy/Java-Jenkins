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
        sh 'ls -R .'   // debug: show project structure
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
        junit 'target/surefire-reports/*.xml'   // ✅ correct path
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
    success { echo '✅ Build + tests succeeded!' }
    failure { echo '❌ Build or tests failed!' }
  }
}
