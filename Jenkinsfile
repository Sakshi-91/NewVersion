pipeline {
    agent any
    stages {
        stage("Generate Files") {
            steps {
                echo "Generating Java Files from ChatGPT API calls"
        	// bat "cd script"

		   
        withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']) {  
          sh '''
            bat "sh -x ./script/codeconvertor.sh WebApplication2/WebApplication2/Program.cs"
          '''
     
      }
		  
	//	sh "git stash"  
	//	sh "git checkout main"
          //      sh "git stash pop" 
		    //sh "git checkout origin/main"
		//sh "chmod -r 755 *"
        	//sh "chmod 755 /var/lib/jenkins/workspace/GetDetails/script/codeconvertor.sh"
                //sh "./script/codeconvertor.sh WebApplication2/WebApplication2/Program.cs"
                 
            }
        }

        stage("Commit Generated Files") {
            steps {
		echo "Staging the generated files and commiting in git"
                bat "git stash"  
		bat "git checkout main"
                bat "git stash pop"                  
                bat "git add ./demo1/src/main/java/com/example/demo/Demo1Application.java"
                bat "git commit -m 'AIGeneratedFiles' || echo 'Commit failed. There is probably nothing to commit.'"
            }
        }

        stage("Push to Repository") {
            steps {
               withCredentials([gitUsernamePassword(credentialsId: 'PAT_Jenk', gitToolName: 'Default')]) {
		     echo "Pushing to remote GitHub Repo"
	          //   bat "git pull origin main"		
                  //   bat "git push -u origin main"
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
	        bat "cd demo1 & dos2unix mvnw & docker build -t sakshidocker12/hackthon-23 ."
		//sh "cd demo1 & chmod a+rw . & dos2unix mvnw & docker build -t sakshidocker12/hackthon-23 ."
            }
        } 

        stage('Push Docker image') {
            environment {
		DOCKERHUB_CREDENTIALS = credentials('sakshidocker12-token')    
            }
            steps {
		 echo "Shiping the Docker Image to DockerHub"    
		 sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'    
		 bat "docker push sakshidocker12/hackthon-23:latest"  
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
