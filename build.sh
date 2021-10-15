#!/usr/bin/env bash

## build
mvn clean package

## Lambda docker images
readonly DATE_STAMP=$(date +%Y%0m%0d-%0H%0M)
readonly AWS_ACCOUNT='108170245965'
readonly AWS_PROFILE="default"
readonly AWS_REGION="us-west-2"

aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin $AWS_ACCOUNT.dkr.ecr.us-west-2.amazonaws.com
# Use following ecr syntax for aws cli version 2
#aws ecr get-login-password --region $AWS_REGION --profile $AWS_PROFILE | docker login -u AWS --password-stdin $AWS_ACCOUNT.dkr.ecr.$AWS_REGION.amazonaws.com
docker build -t sns-subscription . --progress=plain --no-cache
docker tag sns-subscription:latest $AWS_ACCOUNT.dkr.ecr.$AWS_REGION.amazonaws.com/sns-subscription:latest
docker tag sns-subscription:latest $AWS_ACCOUNT.dkr.ecr.$AWS_REGION.amazonaws.com/sns-subscription:$DATE_STAMP
docker push $AWS_ACCOUNT.dkr.ecr.$AWS_REGION.amazonaws.com/sns-subscription:latest
docker push $AWS_ACCOUNT.dkr.ecr.$AWS_REGION.amazonaws.com/sns-subscription:$DATE_STAMP