echo "Starting to deploy docker image.."
DOCKER_IMAGE=sakshidocker12/hackthon-23:latest
docker pull $DOCKER_IMAGE
docker ps -q --filter ancestor=$DOCKER_IMAGE | xargs -r docker stop
docker container stop hackthon-23
docker container rm hackthon-23 
docker run --name hackthon-23 -d -p 8086:8085 -p 8089:8088 $DOCKER_IMAGE
