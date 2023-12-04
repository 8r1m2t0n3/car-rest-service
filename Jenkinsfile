pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat './mvnw spring-boot:run'
            }
        }
        stage('Test') {
            steps {
                bat './mvnw test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarserver') {
                    bat './mvnw clean verify sonar:sonar \
                        -Dsonar.projectKey=gitlabjenkinsproject-key \
                        -Dsonar.projectName="gitlabjenkinsproject" \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.token=sqp_cd4102a4f2b51eaf7243ce0d23606ff10db067e4'
                }
            }
        }
        stage('Docker Build & Start') {
            steps {
      	        bat 'docker-compose up'     
            }
        }
    }
}
