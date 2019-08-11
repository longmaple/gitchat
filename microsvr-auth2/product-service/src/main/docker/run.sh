echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z eurekaserver  $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Waiting for the authorization server to start on port $AUTHSERVER_PORT"
echo "********************************************************"
while ! `nc -z authenticationservice $AUTHSERVER_PORT`; do sleep 3; done
echo "*******  Authorization Server has started"

echo "********************************************************"
echo "Starting Product Service " ON PORT: $SERVER_PORT;
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -Dsecurity.oauth2.resource.jwt.key-uri=$AUTHSERVER_URI               \
     -jar /usr/local/productservice/@project.build.finalName@.jar
