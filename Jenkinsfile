pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'JDK 17'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-repo/hotelbooking.git'
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

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                // Deploy WAR to server, e.g., Tomcat
                sh 'cp target/hotelbooking-1.0-SNAPSHOT.war /path/to/tomcat/webapps/'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.war', fingerprint: true
        }
    }
}