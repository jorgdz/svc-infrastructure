# comandos kafka EN EL NUESTRO CONTENEDOR

## CREAR UN TOPICO

> kafka-topics --create --bootstrap-server kafka:9093 --replication-factor 1 --partitions 1 --topic test

## LISTAR LOS TOPICOS

> kafka-topics --list --bootstrap-server kafka:9093

## PRODUCIR ALGUNOS MENSAJES

> kafka-console-producer --bootstrap-server kafka:9093 --topic test

## INICIAR CONSUMER

> kafka-console-consumer --bootstrap-server kafka:9093 --topic test --from-beginning

> kafka-console-consumer --bootstrap-server kafka:9093 --topic MarcacionExternaCreada --from-beginning
> kafka-console-consumer --bootstrap-server kafka:9093 --topic ProcesarMarcacionDiaria --from-beginning