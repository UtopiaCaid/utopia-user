pipeline {
    agent any
    environment {
        COMMIT_HASH="${sh(script:'git rev-parse --short HEAD', returnStdout: true).trim()}"
    }
    stages {

        stage('Checkout'){
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/development']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/UtopiaCaid/utopiaadmin']]])
            }
        }
        stage('Package') {
            steps {
                echo 'Building..'

                script {
                    sh 'export PATH=/home/ec2-user/apache-maven-3.8.1/bin:$PATH'
                    sh 'echo $PATH'
                    sh "mvn clean package"
                }
            }
        }
        stage('Build') {
            steps {
                echo 'Deploying....'
                sh "$AWS_LOGIN"                
                sh "docker build --tag utopiaadmin:$COMMIT_HASH ."
                sh "docker tag utopiaadmin:$COMMIT_HASH $AWS_ID/utopia-admin/admin:$COMMIT_HASH"
                sh "docker push $AWS_ID/utopia-admin/admin:$COMMIT_HASH"
            }
        }

        // stage('Deploy') {
        //    steps {
        //        sh "touch ECSService.yml"
        //        sh "rm ECSService.yml"
        //        sh "wget https://raw.githubusercontent.com/SmoothstackUtopiaProject/CloudFormationTemplates/main/ECSService.yml"
        //        sh "aws cloudformation deploy --stack-name UtopiaFlightMS --template-file ./ECSService.yml --parameter-overrides ApplicationName=UtopiaFlightMS ECRepositoryUri=$AWS_ID/utopiaairlines/flightms:$COMMIT_HASH DBUsername=$DB_USERNAME DBPassword=$DB_PASSWORD SubnetID=$SUBNET_ID SecurityGroupID=$SECURITY_GROUP_ID TGArn=$UTOPIA_FLIGHTMS_TARGETGROUP --capabilities \"CAPABILITY_IAM\" \"CAPABILITY_NAMED_IAM\""
        //    }
        // }

        // stage('Cleanup') {
        //     steps {
        //         sh "docker system prune -f"
        //     }
        // }
    }
}
