SFG Beer Works - RESTful Brewery Service
This project is to support learning about Restful APIs.

### Commands:

#### Finding the Name of a Docker Container

We’ll need to provide docker exec with the name (or container ID) of the container we want to work with. We can find this information using the docker ps command:

`docker ps`

This command lists all the Docker containers running on the server, and provides some high-level information about them:


##### "docker build -f [docker File Location Address] -t [Docker Image Name] ."

`docker build  -f ./src/main/dockerBase/Dockerfile -t kbe-rest .`

#### Run docker image

##### "docker run -p [port On Which You Want To Run Docker Image] -d [Docker Image Name]"

`docker run -p 8080:8080 -d kbe-rest`

`docker image ls`

#### Run a Container Interactively  

Docker allows you to run a container in interactive mode. This means you can execute commands inside the container while it is still running.

By using the container interactively, you can access a command prompt inside the running container.  

Command for tun interactively  

##### "docker run -it -p portOnWhichYouWantToRunImage [Docker Image Name]"

`docker run -it -p 8080:8080 kbe-rest`

###### Note: when using pom plugin for image in docker if ${project.artifactId} is used in name convention then name in ${project.artifactId} should be in small letters.

#### Terminate the JVI

Just press **[ctrl+c]** in command line to terminate the JVI

KBE - Pushing to Docker Hub

command to push on docker

`mvn clean package docker:build docker:push`

above command is used to clean , then build new image, run some tests and push it on docker

#### display list of contexts

`kubectl config get-contexts`

#### set default context to [Your required Context]

kubectl config use-context [Name of your context from list]

`kubectl config use-context docker-desktop`

A Pod always runs on a Node. A Node is a worker machine in Kubernetes and may be either a virtual or a physical machine, depending on the cluster. Each Node is managed by the control plane. A Node can have multiple pods, and the Kubernetes control plane automatically handles scheduling the pods across the Nodes in the cluster. The control plane's automatic scheduling takes into account the available resources on each Node.

#### Check application configuration

Let's verify that the application we deployed in the previous scenario is running. We'll use the kubectl get command and look for existing Pods:

`kubectl get nodes`

#### get cluster  

Using the kubectl get all command we can list down all the pods, services, statefulsets, etc. in a namespace.

`kubectl get all`

The above command will get the following resources running in your namespace, prefixed with the type of resource:

* pod

* service

* daemonset

* deployment

* replicaset

* statefulset

* job

* cronjobs

### 384 KBE - Create Deployment

#### Generate the yaml file for deployment

kubectl create deployment [Name_0f_The_Application] --image [name_of_the_image_inside_docker_hub] --dry-run=client -o=yaml > deployment.yml

* in above command => [--dry-run=client] this part tells compiler run it locally

`kubectl create deployment KBE_SpringBootrest --image springboot_application/kbe_spring_boot_rest --dry-run=client -o=yaml > deployment.yml`

##### to run the above image

`kubectl apply -f deployment.yml`

##### to check pod successfully created

`kubectl get all`

#### KBE - Create Service

kubectl create service clusterip [name for image] --tcp=8080:8080 --dry-run=client -o=yaml >> service.yml

`kubectl create service clusterip kbe.springbootrest --tcp=8080:8080 --dry-run=client -o=yaml >> service.yml`

creating service from service file we have generated earlier

`kubectl apply -f service.yml`

#### 386 KBE - Port Forwarding

we will use the following command to forward the cluster  
It's essentially setting up a tunnel to go from my network to the cluster network.

kubectl port-forwarding service/[service image name] [port Number:port number]  

`kubectl port-forwarding service/kbe-rest 8080:8080`

the following command is used for checking the effect of above command

`curl localhost:8080/actutor/health`

387 KBE - Terminating/Shut down Services and Deployments

the following command is use for deleting or terminating kubernetes service we have created earlier.

{ kubectl delete service [service name] }

`kubectl delete service kbe-rest`

the following command is use for deleting or terminating kubernetes deployment we have created earlier.

{ kubectl delete deployment [deployment name] }

`kubectl delete deployment kbe-rest`


#### KBE - Exposing Services

`vi service.yml`

above command will load the service.yml definition 

can see down here on the type: [clusterIp] => change it to [NodePort]

due to this we can get rid of tunnel in port-forwarding; we have to create for the service to run

#### 389 KBE - Accessing Logs

###### In docker context

run    `docker ps`

get id of container interested in,

run `docker logs [ Container ID Here ]`

###### In kubernetes context

run    `kubectl get all`

get id of pod interested in,

run `kubectl logs -- tail=20 [pod id]`


#### 390 KBE - Setting Environment Variables

`vi deployment.yml`

in deployment - container we will set another property  
    `env:`  
    `- name: [LOGGING_LEVEL_[NAME_HERE]]`  // this should be in uppercase  
    `value: "info"`

then run  `kubectl apply -f deployment.yml` to get changes applied.


#### 391 KBE - Readiness Probe

so readiness probe basically tells kubernetes that the container is ready to start accepting traffic

we need to do two things here to enable the readiness probe.  
one is to set the environment properties for spring boot actuator to enable this.  
second is to tell kubernetes to utilize it, 

run     `vi deployment.yml`

add this in containers block  under `env:` tag
for part one  
    `-name: MANAGEMENT_ENDPOINT_HEALTH_PROBES_ENABLED`  
    `value: "true"`  
    `-name: MANAGEMENT_HEALTH_READINESSTATE_ENABLED`  
    `value: "true"`  
for part second below above code add the following  
    `readinessProbe:`  
        `httpGet:`  
            `port: 8080 `  
            `path: /actuator/health/readiness`  
    

then run  `kubectl apply -f deployment.yml` to get changes applied.

to check in postman use http://localhost:[PortNumber]/actuator/health/readiness


#### 392 KBE - Liveness Probe  
this tell the application is still alive and able to accept traffic.

add this in containers block  under `env:` tag  
    `-name: MANAGEMENT_HEALTH_LIVENESSSTATE_ENABLED`  
    `value: "true"`  
    `livenessProbe:`  
    `httpGet:`  
    `port: 8080 `  
    `path: /actuator/health/liveness`  

then run  `kubectl apply -f deployment.yml` to get changes applied.

393 KBE - Graceful Shutdown

add this in containers block  under `env:` tag  
    `-name: SERVER_SHUTDOWN`  
    `value: "graceful"`  
    `lifecycle:`
        `preStop:`
            `exce:`
                `command: ["sh","-c","sleep 10]`

then run  `kubectl apply -f deployment.yml` to get changes applied.

