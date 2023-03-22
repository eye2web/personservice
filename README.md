# personservice

Requires Java17 to run

# Run application

```bash
./gradlew bootRun
```

# Test application

```bash
./gradlew test
```

# Endpoints

Get a list of all persons who have a partner and three children with that partner and one of the children has an age
below 18

As base64 encoded CSV

```bash
 GET http://localhost:8080/api/v1/persons/filtered/csv
```

As JSON

```bash
 GET http://localhost:8080/api/v1/persons/filtered
```

Get a list of all available persons. Sorting is optional

```bash
GET http://localhost:8080/api/v1/persons?sort=id&direction=desc
```

```bash
GET http://localhost:8080/api/v1/persons
```