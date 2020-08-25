#!/bin/bash
keyid=$1
key=$2
dockerhub_id=${3:-ghshu}   # update it to your own dockerhub id

sudo docker run -d -p 8080:8080 -e AWS_ACCESS_KEY_ID=$keyid -e AWS_SECRET_ACCESS_KEY=$key $dockerhub_id/ads-aws:latest
