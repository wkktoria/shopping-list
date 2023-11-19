# Shopping List

> **_NOTE:_**  Frontend is not connected to Jetty and has to be started separately.

## Setup

1. Set port where server starts in VM options: `-Dport=PORT`
2. Set Flyway configuration file in VM options: `-Dflyway.configFiles=FLYWAY_CONFIG_FILE`
3. Set Hibernate connection settings in VM options:
    - `-Dhibernate_url=URL`
    - `-Dhibernate_username=USERNAME`
    - `-Dhibernate_password=PASSWORD`
