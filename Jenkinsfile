pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'
        jdk 'JDK 17'
    }

    stages {
        stage('Build Microservices') {
            steps {
                script {
                    def services = ['smartcart-user-service', 'order-service', 'product-service','inventory-service', 'smartcart-api-gateway',
                     'smartcart-service-registry'] // 🔁 add your actual folder names

                    for (service in services) {
                        dir(service) {
                            echo "🔨 Building ${service}"
                            bat "mvn clean install"
                        }
                    }
                }
            }
        }

        stage('Test Microservices') {
            steps {
                script {
                    def services = ['smartcart-user-service', 'order-service', 'product-service','inventory-service', 'smartcart-api-gateway',
                                                         'smartcart-service-registry']

                    for (service in services) {
                        dir(service) {
                            echo "🧪 Running tests for ${service}"
                            bat "mvn test"
                        }
                    }
                }
            }
        }

        stage('Package Microservices') {
            steps {
                echo '📦 Packaging done during build phase for Maven.'
            }
        }
    }

    post {
        failure {
            echo '❌ Build failed!'
        }
        success {
            echo '✅ Build successful!'
        }
    }
}
