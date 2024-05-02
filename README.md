## Requirement

- Java 8
- Docker Desktop
- IntelliJ
- Postman

## Run the app

- Build the project
```sh
mvn clean package -DskipLiquibase=true -DskipTests=true
```
- Build docker container
```sh
cd iapps-xmlreader
```
```sh
docker-compose up
```
- Now build the project with
```sh
mvn clean package
```
## API Endpoints
- POST API: http://localhost:8080/api/uploadXml
    - in form-data add key "paperXml" change type to file and attach the file.
    - xml will be processed.

- GET API: http://localhost:8080/api/getEpapers
    - parameters for this API:
        - search: if need search data based on newspaper name or filename.
        - sortBy: need to sort by any specific column(By default: id)
        - order: true for Ascending | false for Descending order.
        - pageNo: starts from 0
        - pageSize: no of record need to get in result. 
        - fromDate: It's start date can be in format YYYY-MM-DD
        - toDate: It's end date can be in format YYYY-MM-DD
