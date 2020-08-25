#!/bin/bash
dockerhub_id=${1:-ghshu}

sudo docker tag ads-aws $dockerhub_id/ads-aws
sudo docker login
sudo docker push $dockerhub_id/ads-aws
