# MicroServices Righttek
## _Practices_

[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

## Developed services

- Entity Service
- Micro service
- Utility service

## Docker

Start postgres in docker.

```sh
docker run -p 5433:5432 --name mipostgres -v C:\Users\JORGE\postgresdb:/var/lib/postgresql/data -e POSTGRES_PASSWORD=admin -d postgres:13.2
```

Build docker compose after compiling the apps.

```sh
cd dir/workspace
docker-compose build
```

```sh
docker-compose up -d
```

## License

MIT

**Free Software, Hell Yeah!**
