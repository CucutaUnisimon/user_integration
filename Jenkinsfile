#!groovy
pipeline {
	agent none
      stages {
        stage('Maven Clean') {
            agent { label 'master' }
          steps {
            sh 'mvn clean'
          }
        } 
        stage('Maven Package'){
            agent { label 'master' }
            steps {
                sh 'mvn package'
            }
        }
        stage('Load Properties'){
            agent { label 'master' }
            steps {
                script {
                	sh 'cat src/main/resources/application.properties'
                	
                    properties = readProperties file: 'src/main/resources/application.properties'
                    env.SERVER_PORT = properties['server.port']
                    env.NAME_APP = properties['spring.application.name']
                }
            }
        }
        stage('Jar Run') {
            agent { label 'master' }
            steps {
                withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                    sh 'fuser -k $SERVER_PORT/tcp || true'
                    sh 'nohup java -jar target/$NAME_APP-0.0.1-SNAPSHOT.jar > log-greetings.log 2>&1 &'
                }
            }
        }
    }
}