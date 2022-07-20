## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)


## Authors

- [@alievanigar](https://github.com/alievanigar)

curl --location --request POST 'http://localhost:8080/authenticate' \
--header 'Authorization: Basic ZGlndW1iOkRVMTIzNDU2' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=BF7499FCE4246F9722DEC18D8981A99A' \
--data-raw '{
    "username":"digumb",
    "password":"DU123456"
}'

curl --location --request POST 'http://localhost:8080/getInfoExchange?date=18.07.2022&valyuta=USD' \
--header 'Authorization: Basic ZGlndW1iOkRVMTIzNDU2' \
--header 'Cookie: JSESSIONID=BF7499FCE4246F9722DEC18D8981A99A' \
--data-raw ''

curl --location --request DELETE 'http://localhost:8080/deleteInfoExchange/15.07.2022' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE2NTgyNzAyNzAsImlhdCI6MTY1ODIzNDI3MH0.CO-_GjQnXOpzOaSpoykgapdR-DK9nnA7IMUPdtp-EOU' \
--header 'Cookie: JSESSIONID=BF7499FCE4246F9722DEC18D8981A99A'

curl --location --request POST 'http://localhost:8080/saveInfoExchange/15.07.2022' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJleHAiOjE2NTgyNzAyNzAsImlhdCI6MTY1ODIzNDI3MH0.CO-_GjQnXOpzOaSpoykgapdR-DK9nnA7IMUPdtp-EOU' \
--header 'Cookie: JSESSIONID=BF7499FCE4246F9722DEC18D8981A99A'

## Tech Stack
Hibernate, Gradle, JWT, Basic auth, Liquibase, Testing, Spring boot

