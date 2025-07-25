pipeline {
    agent any

    tools {
        maven 'Maven 3.8.8'  // Must match what you installed in Jenkins
        jdk 'JDK 11'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Gitatheisenberg/smartcart-git.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
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
