pipeline {
    agent any
    tools {
        maven "MAVEN_HOME"
    }
    stages {
        stage ('Build Backend') {
            steps {
                sh 'mvn clean package -DskiptTests=true'
            }
        }
    }
}