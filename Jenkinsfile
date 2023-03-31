pipeline {
    agent any
    
    stages {
        stage ("Git Checkout"){
            steps{
            git branch: 'omar-branch', 
            url: 'https://github.com/nawresEsprit/devopProject.git'
            }
        
        }
        
        stage('Maven Clean') {
            steps {
                echo "Cleaning Project"
                sh 'mvn clean'
            }
        }
        
        stage('Maven Build') {
            steps {
                echo "Building Project"
                sh 'mvn clean install'
            }
        }
        
        stage('Unit Test') {
            steps {
                echo "Testing Project"
                sh 'mvn compile validate test'
            }
        }
        
        stage('Sonarqube Test') {
            steps {
                  echo "Sonarqube Testing "
                sh 'mvn sonar:sonar -Dsonar.login="admin" -Dsonar.password="sonar" -Ptest'
               	
                } 
              
                
            }
        }
        
        stage('Create Package') {
            steps {
                echo "Creating Package"
                sh 'mvn package'
            }
        }
        
         stage("Publish to Nexus") {
             steps {
        sh 'mvn deploy'
      }
    
    }
        
    
        stage("Build our Image") {
          steps {
          
              sh 'docker build -t omarsoltani/validation:$BUILD_NUMBER .'
              
             }
       }
       
       stage("Push to DockerHub") { 
            steps { 
                script {
                    
			sh 'echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin';
                sh 'sudo docker push omarsoltani/validation';
                }
            } 
            
            
        }
        
        stage("Docker-Compose") {
          steps {
              sh 'docker-compose up -d'
             }
       
       
       } 
      
       
     }
}