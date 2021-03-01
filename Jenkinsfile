pipeline{
    triggers{
        pollSCM('* * * * *')
    }
    agent any
    tools{
        maven 'maven'
    }

    environment{
         AWS_REGION='ap-south-1'
         AWS_DEFAULT_REGION='ap-south-1'
     }

    stages{
        stage('maven clean'){
            steps{
                sh 'mvn clean'
            }
        }
        stage('maven compile'){
            steps{
                sh 'mvn compile'
            }
        }

        /*stage('sonar analysis'){
            steps{
                withSonarQubeEnv('SonarQube'){
                    withMaven(maven:'maven'){
                        sh 'mvn sonar:sonar'
                    }
                }
            }

        }*/
        stage('maven package'){
            steps{
                sh 'mvn package'
            }
        }

    }

    post{
      success{
           rtUpload (
    serverId: 'Jfrog',
    spec: '''{
          "files": [
            {
              "pattern": "target/*.jar",
              "target": "art-doc-dev-loc-spring"
            }
         ]
    }''',
    buildName: 'holyFrog',
    buildNumber: '1'
)


        rtDownload (
        serverId: "Jfrog",
        spec:
              """{
                "files": [
                  {
                    "pattern": "art-doc-dev-loc-spring/**",
                    "target": "artifacts/"      
                  }
               ]
              }"""
      )
      stage('Deploy to S3 Bucket'){
                steps{
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: '889f1bce-4edc-48a1-9f8a-d38a7ffb6af0', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                        s3Upload(file:'/var/jenkins_home/workspace/game-spring/artifacts/game-0.0.1-SNAPSHOT.jar', bucket:'bharathvelisala', path:'sampleFile/game-0.0.1-SNAPSHOT.jar')
}
                }
            }      
       sshagent(['ed975733-0480-4c23-a8f3-4f0683ed2a43']){
                    sh 'ssh -o StrictHostKeyChecking=no ubuntu@13.127.138.117 pwd'
                    sh 'scp -r /var/jenkins_home/workspace/game-spring/artifacts/*.jar ubuntu@13.127.138.117:/home/ubuntu/artifacts'
        }
            mail bcc: '', body: 'build was successful ', cc: '', from: '', replyTo: '', subject: 'build successful', to: 'bharath.velisala@gmail.com'

      }
      failure {
            echo 'Build failed :('
         mail bcc: '', body: 'build failure ', cc: '', from: '', replyTo: '', subject: 'build failure', to: 'bharath.velisala@gmail.com'

        }

  }
}   
  

  
