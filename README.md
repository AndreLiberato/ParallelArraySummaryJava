# ParallelArraySummaryJava
Implementação do projeto com java.

## Como buildar

```
mvn package
```

Esse comando criará um arquivo `.jar` no diretório `./target/ParallelArraySummary-1.0.jar`.

## Como executar 

```
java -cp ./target/ParallelArraySummary-1.0.jar br.ufrn.imd.Main [N] [T]
```

Sendo `N` aplicado a 10^N elementos e `T` o número de threads criada.
