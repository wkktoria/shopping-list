# Shopping List

Shopping List helps you manage the products you want to buy.

## Table of Contents

1. [Demo](#demo)
2. [Setup](#setup)
3. [Cautions](#cautions)

## Demo

### Adding product

![Adding product](gifs/add-product.gif)

### Deleting product

![Deleting product](gifs/delete-product.gif)

### Deleting all products

![Deleting all products](gifs/delete-all-products.gif)

### Toggling if product is bought

![Toggling product](gifs/toggle-product.gif)

## Setup

1. Set port where server starts in VM options: `-Dport=PORT`.
2. Create Flyway configuration file. See [flyway-example.conf](flyway-example.conf).
3. Set Flyway configuration file in VM options: `-Dflyway.configFiles=FLYWAY_CONFIG_FILE`.
4. Set Hibernate connection settings in VM options:
    - `-Dhibernate_url=URL`
    - `-Dhibernate_username=USERNAME`
    - `-Dhibernate_password=PASSWORD`

## Cautions

- ⚠️ Frontend is not connected to Jetty and has to be started separately.
- ⚠️ `mvn package` won't create valid jar/war file.
