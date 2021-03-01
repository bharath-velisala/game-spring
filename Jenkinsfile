pipeline{
    triggers{
        pollSCM('* * * * *')
    }
    agent any
    tools{
        maven 'maven'
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
      
       sshagent(['ed975733-0480-4c23-a8f3-4f0683ed2a43']){
                    sh 'ssh -o StrictHostKeyChecking=no ubuntu@13.127.138.117'
                    sh 'scp -r /var/jenkins_home/workspace/game-spring/artifacts/*.jar ubuntu@13.127.138.117:/home/ubuntu/artifacts'
        }

        withAWS(region:'ap-south-1',credentials:'889f1bce-4edc-48a1-9f8a-d38a7ffb6af0') {
                    s3Upload(file:'artifacts/game-0.0.1-SNAPSHOT.jar', bucket:'bharathvelisala', path:'artifacts/')
            }

            mail bcc: '', body: 'build was successful ', cc: '', from: '', replyTo: '', subject: 'build successful', to: 'bharath.velisala@gmail.com'

      }
      failure {
            echo 'Build failed :('
         mail bcc: '', body: 'build failure ', cc: '', from: '', replyTo: '', subject: 'build failure', to: 'bharath.velisala@gmail.com'

        }

  }
}   
  

  
