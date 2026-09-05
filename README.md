# senegai

## Spring Boot Server

Start the server

```
./gradlew :server:app:bootRun
```

or if you are using postgreSQL

```
./gradlew :server:app:bootRun --args='--senegai.persistence.type=postgres'
```


Open a browser on http://localhost:8081.

## Angular Client
Start the client

```
cd client

npm install

npm run start
```

Open a browser on http://localhost:5200.

## Database

Start the postgres DB

```
docker run --name postgres-senegai -e POSTGRES_PASSWORD=password -p 5432:5432 postgres:16-alpine
```
Migrate the database schema

```
./gradlew :database:postgresql-dsl-schema:flywayMigrate
```

The migrations live in `database/src/main/resources/db/migration`. Schema changes are added as a new
`V<n>__<description>.sql` file there, never by editing a migration that has already been applied.
`./gradlew :database:flywayInfo` shows which migrations a database has.

Flyway connects to the postgres container above by default. Point it somewhere else with
`-Pflyway.url`, `-Pflyway.user` and `-Pflyway.password`, or with the `FLYWAY_URL`, `FLYWAY_USER` and
`FLYWAY_PASSWORD` environment variables:

```
./gradlew :database:postgresql-dsl-schema:flywayMigrate -Pflyway.url=jdbc:postgresql://localhost:5432/senegai
```

If you created the schema by hand before Flyway was introduced, record it as already migrated once,
so that Flyway does not try to create the tables a second time:

```
./gradlew :database:postgresql-dsl-schema:flywayBaseline -Pflyway.baselineVersion=1
```

## sakila schema and data

Migrate the database schema

```
./gradlew :database:postgresql-sakila-db-dump:flywayMigrate
```

### Manual import with psql

Import the sakila schema and data

```
docker exec -i postgres-senegai psql -U postgres -d postgres < ./database/postgres-sakila-db-dump/V00001__postgres-sakila-schema.sql
docker exec -i postgres-senegai psql -U postgres -d postgres < ./database/postgres-sakila-db-dump/V00002__postgres-sakila-insert-data.sql
```

Remove the sakila schema and data

```
docker exec -i postgres-senegai psql -U postgres -d postgres < ./database/postgres-sakila-db-dump/postgres-sakila-delete-data.sql
docker exec -i postgres-senegai psql -U postgres -d postgres < ./database/postgres-sakila-db-dump/postgres-sakila-drop-objects.sql
```

