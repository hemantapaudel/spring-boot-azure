# spring-boot-azure


Go to terminal where you can build this project
 
# Check Docker version on system?
  * command $: docker --version
  * OUTPUT : Docker version 19.03.5, build 633a0ea

# Create docker image
 * command $: docker build -f Dockerfile -t spring-boot-azure .

# check docker images on your system
 * docker images
 
# Run docker image and map port internal and external 
* command $: docker run -p 80:80 spring-boot-azure


rgname=my-personal-resource-group-dev
acrname=myarchemantap
aciname=myacihemantap
location='EastAsia'
regsvrname=myarchemantap.azurecr.io

az group create --name $rgname --location $location --output table

az acr create --name $acrname --resource-group  $rgname --sku Basic

az acr login --name $acrname

docker tag spring-boot-azure myarchemantap.azurecr.io/spring-boot-azure

docker push myarchemantap.azurecr.io/spring-boot-azure


az acr credential show --name $acrname


az container create --resource-group  $rgname --name $aciname --image myarchemantap.azurecr.io/spring-boot-azure:latest --cpu 1 --memory 1 --registry-login-server myarchemantap.azurecr.io --registry-username $acrname --registry-password $password --dns-name-label=myapphemantap --port 80 --location EastAsia




