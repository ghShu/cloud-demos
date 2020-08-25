#!/bin/bash

sudo apt-get update

sudo apt-get install python-pip
sudo apt-get install gcc

sudo pip install flask
sudo pip install flask_session

sudo apt install mysql-client-core-5.7
sudo apt install mariadb-client-core-10.1
sudo apt-get install default-libmysqlclient-dev
sudo pip install MySQL-python

# S3
sudo pip install awscli
sudo pip install boto3


# Docker
# uninstall old versions
sudo apt-get remove docker docker-engine docker.io contained runc

# set up reposotory
sudo apt-get udpate
sudo apt-get install \
     apt-transport-https \
     ca-certificates \
     curl \
     gnupg-agent \
     software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88
sudo add-apt-repository \
     "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
     $(lsb_release -cs) \
     stable"

# install docker CE
sudo apt-get udpate
sudo apt-get install docker-ce docker-ce-cli containerd.io

# path for IAM user credentials
mkdir ~/.aws

# temporary directory to store uploaded files to S3
mkdir /tmp/upload_tmp
