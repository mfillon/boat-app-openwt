# Challenge Boat App

## Links
- Project board : https://github.com/users/mfillon/projects/1/views/1
- CI : https://app.circleci.com/pipelines/github/mfillon/boat-app-openwt

## Build

`./mvnw clean install`

## Run tests

`./mvnw clean test`

## Backend

Spring Boot based RESTfull app
Secured with basic auth
Users:
- user1/password
- user2/password

Exposing endpoints on /api/boats
- GET `/api/boats?owner=...`
- PUT `/api/boats/{id}`
- POST `/api/boats`
- DELETE `/api/boats/{id}`

## Frontend

Frontend code is localted on ./frontend/ folder See frontend README for more details
