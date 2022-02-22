pipeline {
    agent any
    tools {
        maven "MAVEN_HOME"
    }
    stages {
        stage ('Build Backend') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv ('SONAR_LOCAL') {
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://192.168.0.129:9000 -Dsonar.login=237247b2f6e36f319e53f4516a1f16551c8ce028 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**.mvn/**,**/src/test**,**Application.java"
                }
            }
        }
        stage ('Quality Gate') {
            steps {
                sleep(10)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage ('Deploy Backend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcat_user', path: '', url: 'http://192.168.0.105:8080')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
    }
}