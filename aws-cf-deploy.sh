aws cloudformation deploy --region us-east-2 --stack-name UtopiaUserMS \
--template-file utopia-cftemplate --parameter-overrides ApplicationName=UtopiaUserMS \
ECRepositoryUri=$AWS_ID/utopia-user:$COMMIT_HASH DBUrl=$DB_URL DBUsername=$DB_USERNAME \
DBPassword=$DB_PASSWORD ECSCluster=$UTOPIA_CLUSTER ExecutionRoleArn=$EXECUTION_ROLE_ARN \
SubnetID=$UTOPIA_PRIVATE_SUBNET_1 TargetGroupArnDev=$TARGETGROUP_UTOPIA_ACCOUNT_DEV_ARN \
TargetGroupArnProd=$TARGETGROUP_UTOPIA_ACCOUNT_PROD_ARN VpcId=$UTOPIA_PUBLIC_VPC_ID \
UtopiaApplicationLBDNS=$UTOPIA_APPLICATION_LB_DNS --capabilities "CAPABILITY_IAM" "CAPABILITY_NAMED_IAM"