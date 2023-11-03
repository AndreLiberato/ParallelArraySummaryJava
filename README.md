# ParallelArraySummaryJava
Implementação do projeto com java.

# Como buildar

```
mvn package
```

Esse comando criará um arquivo `.jar` no diretório `./target/ParallelArraySummary-1.0.jar`.

# Como executar 

```
java -cp ./target/ParallelArraySummary-1.0.jar br.ufrn.imd.Main [N] [T]
```

Sendo `N` aplicado a 10^N elementos e `T` o número de threads criada.

# Arquivo de ids

Ao final da execução, será gerado dois arquivos.

> `id_less_five.csv`: ids menores que cinco 

> `id_greater_five.csv`: ids maiores ou iguais a cinco

Dependendo do número de elementos, esses arquivos podem ficar pesado.