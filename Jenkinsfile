pipeline {
    agent any
    
    stages {
        stage('Clone Repo') {
            steps {


               git branch: 'prod', credentialsId: 'GIT_Credentials', url: 'https://github.com/vilas639/eos-catalog-api.git'
               echo "clone repo"
            }
        }
        stage('Maven Build') {
            steps {
                sh 'mvn clean package'
               echo "mvn clean pacakage"
            }
        }
        stage('Sonar Scan') {
            steps {
               withSonarQubeEnv('SonarServer')
	              {
	              sh 'mvn sonar:sonar'
	               }
               echo "Sonar Scan" 
            }
        }

     stage('Artifactory configuration') {
            steps {
               // sh 'mvn clean package'
		    
               echo "Artifactory configuration"
            }
        }
         stage('Deploy Artifacts') {
            steps {
            
	       nexusArtifactUploader artifacts: [[artifactId: 'catalog', classifier: '', file: 'target/catalog-0.0.1-RELEASE.jar', type: 'jar']], credentialsId: 'nexus-cred', groupId: 'com.org.proto', nexusUrl: '51.20.74.27:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'catalog-snapshot-repo', version: '0.0.1-RELEASE'      
               echo "Deploy Artifacts"
            }
        }
        
        stage('Publish build info') {
            steps {
               // sh 'mvn clean package'
               echo "Publish build info"
            }
        }
        
        stage('Docker Image') {
            steps {
               sh 'docker build -t vilasjdhv639/eos-catalog-api:latest .'
               echo "build docker images"
              
              
            }
        }
      
        stage('Docker Push') {
            steps {

      
          sh "docker login -u vilasjdhv639 -p Vilas@123"
          sh "docker push vilasjdhv639/eos-catalog-api:latest"
       
          echo "Docker Push"  

            }
        }

        

        stage('k8s deployment') {
            steps {

                //   sh "kubectl apply -f eos-micro-services-admin-deploy.yaml"
               //sh "kubectl apply -f eos-micro-services-admin-service.yaml"
                echo "k8s deployment"  

            }
            }



}
}
