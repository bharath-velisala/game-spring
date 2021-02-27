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

        stage('sonar analysis'){
            steps{
                withSonarQubeEnv('SonarQube'){
                    withMaven(maven:'maven'){
                        sh 'mvn sonar:sonar'
                    }
                }
            }

        }
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
                    sh 'scp -r /var/jenkins_home/workspace/game-spring/artifacts/*.jar StrictHostKeyChecking=no ubuntu@ec2-13-233-112-23.ap-south-1.compute.amazonaws.com:/home/ubuntu/artifacts'
        }
            mail bcc: '', body: 'build was successful ', cc: '', from: '', replyTo: '', subject: 'build successful', to: 'bharath.velisala@gmail.com'

      }
      failure {
            echo 'Build failed :('
         mail bcc: '', body: 'build failure ', cc: '', from: '', replyTo: '', subject: 'build failure', to: 'bharath.velisala@gmail.com'

        }

  }
}   
  

  
