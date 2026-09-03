# senegai

## Start Senegai

Start the server

```
./gradlew :server:bootRun
```

Open a browser on http://localhost:8080.


Start the client

```
cd client

npm install

npm run start
```

Open a browser on http://localhost:5200.


Start the postgres DB

```
docker run --name postgres-senegai -e POSTGRES_PASSWORD=password -p 5432:5432 postgres:16-alpine
```

Import the data into postgres

```
docker exec -i postgres-senegai psql -U postgres -d postgres < ./postgres-db-dump/postgres-sakila-schema.sql
docker exec -i postgres-senegai psql -U postgres -d postgres < ./postgres-db-dump/postgres-sakila-insert-data.sql
```

Remove the data from postgres

```
docker exec -i postgres-senegai psql -U postgres -d postgres < ./postgres-db-dump/postgres-sakila-delete-data.sql
docker exec -i postgres-senegai psql -U postgres -d postgres < ./postgres-db-dump/postgres-sakila-drop-objects.sql
```
