# smart-ads-gcp
Backend for a Ads system in Go with Tools in GCP, including GCS, BigTable, Docker, GKE and etc.

To setup gcloud ssh with emacs, please add the following snippet into init.el (for emacs) or into dotspacemacs/user-init for spacemacs.
```lisp
(require 'tramp)
(add-to-list 'tramp-methods
             '("gcssh"
               (tramp-login-program        "gcloud compute ssh")
               (tramp-login-args           (("%h")))
               (tramp-async-args           (("-q")))
               (tramp-remote-shell         "/bin/sh")
               (tramp-remote-shell-args    ("-c"))
               (tramp-gw-args              (("-o" "GlobalKnownHostsFile=/dev/null")
                                            ("-o" "UserKnownHostsFile=/dev/null")
                                            ("-o" "StrictHostKeyChecking=no")))
               (tramp-default-port         22)))
```
With this, gcould compute VM can be accessed with the following command:
`SPC f f`
`/gcssh:<username>@<instance-name>:<filename>` (`filename` is optional).

# Set up Golang (main backend development)
```bash
sudo add-apt-repository ppa:longsleep/golang-backports
sudo apt-get update
sudo apt-get install golang-go
go version

```

# Set up Java development environment 
(Dataflow and BigQuery do not have have API for Go yet)
## install maven
```bash
sudo apt-get install maven
mvn archetype:generate     -DarchetypeArtifactId=google-cloud-dataflow-java-archetypes-examples  -DarchetypeGroupId=com.google.cloud.dataflow       -DarchetypeVersion=2.0.0       -DgroupId=com.ads     -DartifactId=dataflow       -Dversion="0.1"       -DinteractiveMode=false       -Dpackage=com.ads
rm -rf dataflow/src/main/java/com/ads/*
rm -rf dataflow/src/test/java/com/ads/*
```

## update pom.xml
## Add a simple main function for dataflow without any operations
## Add Big Table read
## Add BigQuery write
## Run dataflow in the terminal
```bash
cd ~/dataflow
mvn compile exec:java -Dexec.mainClass=com.ads.PostDumpFlow -Dexec.args=" --project=sodium-inverter-233402 --stagingLocation=gs://ad-dataflow/staging/ --tempLocation=gs://ad-dataflow/output --runner=DataflowPipelineRunner --jobName=dataflow-intro"


mvn compile exec:java -Dexec.mainClass=com.ads.PostDumpFlow -Dexec.args=" --project=sodium-inverter-233402 --stagingLocation=gs://ads-resource/staging/ --tempLocation=gs://ads-resource/output --runner=DataflowPipelineRunner --jobName=dataflow-intro"
```
