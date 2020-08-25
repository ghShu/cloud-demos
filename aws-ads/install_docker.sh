#!/bin/bash
# official guide: https://docs.docker.com/install/linux/docker-ce/debian/
# uninstall old versions
sudo apt-get remove docker docker-engine docker.io containerd runc

# set up repository
sudo apt-get update
sudo apt-get install             \
     apt-transport-https         \
     ca-certificates             \
     curl                        \
     gnupg-agent                 \
     software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
sudo apt-key  fingerprint 0EBFCD88
sudo add-apt-repository          \
     "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
     $(lsb_release -cs)                                         \
     stable"

# install docker CE
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io

# verify docker CE installation
sudo docker run hello-world
