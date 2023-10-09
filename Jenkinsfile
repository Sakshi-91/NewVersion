pipeline {
    agent any
    stages {
        stage("Generate Files") {
            steps {
                echo "Generating Java Files from AI foundation model"
		    bat "git pull origin main"
 	        //bat "sh -x ./script/codeconvertor.sh"
 
            }
        }

        stage("Commit Generated Files") {
            steps {
		echo "Staging the generated files and commiting in git"
              
                bat "touch ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/AccountRequest.java"
		bat "touch ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/AccountResponse.java"
		bat "touch ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/Address.java"
		bat "touch ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/GetAccountDetailsApplication.java"
		bat "touch ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/WebService1.java"
		bat "touch ./code_conversion/GetAccountDetails/pom.xml"

		bat "git add ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/AccountRequest.java"
		bat "git add ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/AccountResponse.java"
		bat "git add ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/Address.java"
		bat "git add ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/GetAccountDetailsApplication.java"
		bat "git add ./code_conversion/GetAccountDetails/src/main/java/com/GetAccountDetails/WebService1.java"
		bat "git add ./code_conversion/GetAccountDetails/pom.xml"
		bat "git commit --amend -m 'AIGeneratedFiles'"
		//bat "git commit -m 'AIGeneratedFiles' --force"
                //bat "git commit -m 'AIGeneratedFiles' || echo 'Commit failed. There is probably nothing to commit.'"
            }
        }

        stage("Push to Repository") {
            steps {
               withCredentials([gitUsernamePassword(credentialsId: 'PAT_Jenk', gitToolName: 'Default')]) {
		     echo "Pushing to remote GitHub Repo"
	          //   bat "git pull origin main"		
                 //  bat "git push origin main"
//bat "git push --set-upstream origin main"
		       bat "git push https://github.com/Sakshi-Git1/NewVersion.git HEAD:main"
                }
            }
        }

        stage("Sync Repository") {
            steps {
                  echo "Sync working directory with remote GitHub Repo"
                  bat "git pull origin main"
		  bat "git status"	 
            }
        }
        stage('Build Docker image') {
            steps {
                echo "Building the docker Image based on Dockerfile"
		bat "cd code_conversion/GetAccountDetails & docker build -t sakshidocker12/hackthon-24 . --no-cache=true"    
            }
        } 

        stage('Push Docker image') {
            environment {
		DOCKERHUB_CREDENTIALS = credentials('sakshidocker12-token')    
            }
            steps {
		 echo "Shiping the Docker Image to DockerHub"    
		 sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'    
		 bat "docker push sakshidocker12/hackthon-24:latest"  
		 bat "docker logout"  
            }
        }
        stage('Deploy to AWS') {
             steps {
                echo "Deploying the Docker Image on AWS -EC2"  
                build job: "Deploy", wait: true
              }
           }
       }
}
