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

