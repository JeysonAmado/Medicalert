# Medicalert


Aquí está el comando de ejecución de migraciones en formato Markdown:

## Ejecución de Migraciones
Para ejecutar las migraciones se debe usar el siguiente comando en la carpeta rapiz del proyecto

```shell
docker run -it --workdir="/project" --rm -v $PWD:/project --network=develop_java-medicalert liquibase/liquibase --defaultsFile=db-migrations/liquidbase.properties --changelog-file=db-migrations/changelog.xml update
```
