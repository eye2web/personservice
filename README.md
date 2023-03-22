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
 GET http://localhost:8080/api/v1/persons/csv
```

As JSON

```bash
 GET http://localhost:8080/api/v1/persons
```