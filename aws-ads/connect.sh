#!/bin/bash
vm=${1:-'ubuntu@ec2-54-183-243-160.us-west-1.compute.amazonaws.com'}

ssh -i ".config/ec2-key.pem" $vm
