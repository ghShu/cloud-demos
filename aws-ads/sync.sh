#!/bin/bash
vm=${1:-'ubuntu@ec2-54-183-243-160.us-west-1.compute.amazonaws.com'}

scp -i .config/ec2-key.pem setup.sh $vm:~/ads-aws
scp -i .config/ec2-key.pem src/server.py $vm:~/ads-aws/src
scp -i .config/ec2-key.pem src/templates/index.html  $vm:~/ads-aws/src/templates
scp -i .config/ec2-key.pem .aws/config  $vm:~/ads-aws/.aws
scp -i .config/ec2-key.pem .aws/credentials $vm:~/ads-aws/.aws
scp -i .config/ec2-key.pem run_server.sh $vm:~/ads-aws/
scp -i .config/ec2-key.pem install_docker.sh $vm:~/ads-aws/
scp -i .config/ec2-key.pem run_server.sh $vm:~/ads-aws/
scp -i .config/ec2-key.pem docker_build.sh $vm:~/ads-aws/
scp -i .config/ec2-key.pem docker-build/Dockerfile $vm:~/ads-aws/docker-build/Dockerfile
scp -i .config/ec2-key.pem docker_run.sh $vm:~/ads-aws/
