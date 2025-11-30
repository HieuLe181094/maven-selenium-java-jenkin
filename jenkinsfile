pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage('1 - Get Code') {
            steps {
                git branch: 'main', url: 'https://github.com/HieuLe181094/maven-selenium-java-jenkin'
            }
        }

        stage('2 - Compile') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('3 - Run Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('4 - Publish Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'HTML Report'
                ])
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
