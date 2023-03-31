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
        
        
        stage('Create Package') {
            steps {
                echo "Creating Package"
                sh 'mvn package'
            }
        }
        
        stage('Deploy Artifact to Nexus') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true -Pprod'
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
                    
                    withCredentials([string(credentialsId: 'DockerId', variable: 'Docker')]) {
                        sh 'docker login -u omarsoltani -p ${Docker}'
                        sh 'docker image push omarsoltani/validation:$BUILD_NUMBER'
                }
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