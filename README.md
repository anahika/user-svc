# About the project
A basic CRUD application built with Spring webflux, using MongoDB for data storage.

## Prerequisites
Before you begin, make sure to install MongoDB:
  -  **MongoDB**: You can install MongoDB using the instructions found [here](https://www.mongodb.com/try/download/community).

## Getting Started
To get a local copy up and running, follow these simple steps.

    git clone https://github.com/anahika/user-svc.git
    cd user-svc

## Configuration
Open the application.yaml file in the src/main/resources directory and update the MongoDB configuration if necessary:

    # MongoDB Configuration
    spring:
      data:
        mongodb:
          host: localhost
          port: 27017
          database: db
    # Add other MongoDB configuration properties as needed

## Build and Run
Using Maven

    mvn clean install
