# Table of Contents

-   [Architecture](#org690aff4)
    -   [Used AWS Service:](#orgceb377f)
-   [Run the Ads-AWS](#org54683cd)
    -   [Setup AWS environment](#org527db14)
    -   [Run the Ads server w/o docker](#org0c78858)
    -   [Run the Ads server w/ docker](#orgd283ead)
    -   [Publish your docker images (local or one EC2 VM)](#org53e7698)



<a id="org690aff4"></a>

# Architecture

![img](../demo-architecture.png "Architecture for a simple Ads upload backend using native AWS services")   


<a id="orgceb377f"></a>

## Used AWS Service:

-   EC2
-   S3
-   RDS: MySQL
-   Docker
-   EKS


<a id="org54683cd"></a>

# Run the Ads-AWS


<a id="org527db14"></a>

## Setup AWS environment

-   `mkdir ~/.config`    # save ssh key for log into EC2 vms
-   `mkdir ~/.aws`       # save aws related credentials into a file call `credentials`.


<a id="org0c78858"></a>

## Run the Ads server w/o docker

-   run `./sync.sh` to transfer the code into EC2 VM  (Remember to update the machine IP)
-   `./connect.sh  vm_name`
-   `./run_server.sh`
-   access the service in browser: `ip:8080`


<a id="orgd283ead"></a>

## Run the Ads server w/ docker

-   run `./sync.sh` to transfer the code into EC2 VM  (Remember to update the machine IP)
-   `./connect.sh  vm_name`
-   In host VM: `./docker_run.sh KEY_ID  KEY`
-   In any browser: `ip:8080`


<a id="org53e7698"></a>

## Publish your docker images (local or one EC2 VM)

-   Build image: `./docker_build.sh`
-   publish the docker image to DockerHub: `./docker_publish.sh`

