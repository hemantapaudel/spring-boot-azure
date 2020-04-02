# spring-boot-azure



Go to terminal where you can build this project
 
# Check Docker version on system?
  * command $: docker --version
  * OUTPUT : Docker version 19.03.5, build 633a0ea

# Create docker image
 * command $: docker build -f Dockerfile -t spring-boot-jpa-docker .

# check docker images on your system
 * docker images
 * REPOSITORY               TAG                 IMAGE ID            CREATED             SIZE
 > spring-boot-jpa-docker   latest              273cb3eb3584        10 minutes ago      559MB
 > openjdk                  8                   cdf26cc71b50        5 days ago          510MB


# Run docker image and map port internal and external 
* command $: docker run -p 8082:8082 spring-boot-jpa-docker


