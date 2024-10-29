#!/bin/bash

docker rm is-rest
docker rmi wasd0/is-rest

docker build --tag wasd0/is-rest -f Dockerfile .
docker push wasd0/is-rest:latest 