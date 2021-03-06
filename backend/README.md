# spotify-recommender – backend

Backend application powered by Spring Boot. It handles access to the database (PostgreSQL) containing the dataset as well as user ratings. Also handling access to the Spotify Web API including OAuth 2 authentication.

## Getting Started

### Building from Source

The build tool we use for this project is Maven. So you can create a JAR using the following command:

```sh
mvn clean package
```

Note that Java 15 is required.

### Running the Application

We can make use of Springs Maven plugin to start the application like so:

```sh
mvn spring-boot:run
```

Note that there are different application profiles requiring different environment variables. You can set those like this:

```sh
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Application Profiles

### default

This profile automatically activates if you do not provide a profile. In this case, the application will use the in-memory H2 database which does not contain any data. This is useful if you want to access the Spotify Web API only (e.g. to test login, fetch recently played tracks, etc).

| Environment Variable     | Description                      |
| ------------------------ | -------------------------------- |
| SR_SPOTIFY_CLIENT_ID     | Client ID of the Spotify app     |
| SR_SPOTIFY_CLIENT_SECRET | Client Secret of the Spotify app |

Note that these environment variables are required in any of the following profiles unless otherwise stated.

### init

Profile used to convert the Spotify Million Playlist Dataset to CSV so that it can be imported into PostgreSQL (see [COPY](https://www.postgresql.org/docs/12/sql-copy.html)). Will not start a webserver and thus does not require setting client id/secret.

| Environment Variable | Description                                  |
| -------------------- | -------------------------------------------- |
| SR_DATASET_PATH      | Path to the Spotify Million Playlist Dataset |

### dev

Profile used to do backend development. This profile will not forward you to the frontend dashboard after the first request/login with Spotify. Requires running an SSH tunnel to the remote database (or Docker).

| Environment Variable | Description       |
| -------------------- | ----------------- |
| SR_POSTGRES_USERNAME | Database username |
| SR_POSTGRES_PASSWORD | Database password |

### prod

Profile used for productive deployments.

| Environment Variable | Description       |
| -------------------- | ----------------- |
| PGUSER               | Database username |
| PGPASSWORD           | Database password |

See the PostgreSQL documentation on [environment variables](https://www.postgresql.org/docs/12/libpq-envars.html).
