# Rabbit MQ Test

Buid the image to run inside the container

```

docker build -t alexandergarbuz/rabbitmq-test .

```

If you plan on running the projet against RabbitMQ deployed into another Docker container then you need to create a shared network first

```

docker network create shared-network

```

then you need to pull the RabbitMQ image and deploy it with specific environment vaiables (user name and password that will be used by the applicaiton).

```

docker pull rabbitmq:3-management

docker run -d --network=shared-network --name rabbitmq -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:3-management

```

After all of this is done you can deploy and run your image that you built at the beginning

``` 

docker run -it -p 80:80 --network=shared-network --env-file=".env.docker" --name=rabbitmq-test alexandergarbuz/rabbitmq-test

```

To stop and remove all containers run:

```

docker stop rabbitmq-test

docker rm rabbitmq-test

docker stop rabbitmq

docker rm rabbitmq

docker network rm shared-network

```