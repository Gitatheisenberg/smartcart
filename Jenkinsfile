pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'  // Must match what you installed in Jenkins
        jdk 'JDK 17'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Gitatheisenberg/smartcart.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo '✅ Build succeeded!'
        }
        failure {
            echo '❌ Build failed!'
        }
    }
}
