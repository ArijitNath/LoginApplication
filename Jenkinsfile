pipeline {
    agent any 
    stages {
    	stage("Build") {
            steps {
                sh "./x.sh"
            }
        }
        stage("Build and start test image") {
            steps {
                sh "docker-compose up --build"
            }
        }
    }
}

//steps {
//            script{
//                def image = docker.build("image-name:test", ' .')
//            }
//      }


//        stage('Build') { 
//           steps {
//          		withMaven(maven : 'maven_3_6_3') {
//         			sh 'mvn clean compile'
//          		}
//            }
//        }
//        stage('Test') { 
 //           steps {
 //               withMaven(maven : 'maven_3_6_3') {
   //       			sh 'mvn test'
     //     		} 
       //     }
        //}
//        stage('Deploy') { 
 //           steps {
  ///              echo "Deploying Login Application..."
    //            withMaven(maven : 'maven_3_6_3') {
     //     			sh 'mvn deploy'
      //    		} 
      //      }
       // }