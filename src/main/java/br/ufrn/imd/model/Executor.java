package br.ufrn.imd.model;

import br.ufrn.imd.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * A classe Executor é responsável por carregar elementos, processá-los em paralelo e exibir os resultados.
 */
public class Executor {

    private List<Element> elements;     // Lista de elementos a serem processados
    private int N;                      // Parâmetro N (expoente) para determinar o tamanho da lista de elementos
    private int T;                      // Número de threads para processamento paralelo
    private long runtime;               // Tempo de execução


    /**
     * Construtor da classe Executor.
     *
     * @param N O parâmetro N (expoente) para determinar o tamanho da lista de elementos.
     * @param T O número de threads para processamento paralelo.
     */
    public Executor(int N, int T) {
        this.elements = new ArrayList<>();
        this.N = N;
        this.T = T;
        this.runtime = 0;
    }

    /**
     * Carrega elementos na lista com valores aleatórios gerados.
     */
    public void loadElements() {
        System.out.println("[1] Carregando os elementos ...");

        long startLoading = System.currentTimeMillis();

        final long size = (long) Math.pow(10, N);
        for (int index = 0; index < size; index++) {
            this.elements.add(new Element(index, RandomGenerator.generateValue(), RandomGenerator.generateGroup()));
        }

        long endLoading = System.currentTimeMillis();
        runtime = endLoading - startLoading;

        System.out.println(">> Duração do carrgamento "+ runtime +" ms. <<\n");
    }

    /**
     * Processa os elementos em paralelo usando threads.
     *
     * Este método inicia o processo de processamento paralelo dos elementos. Ele cria uma instância de FinalResult
     * para armazenar os resultados finais das operações, configura uma barreira cíclica para sincronizar threads e
     * chama o método auxiliar processElements para iniciar as tarefas de operação em paralelo.
     *
     * @throws RuntimeException se ocorrer um erro durante o processamento.
     */
    public void process() throws RuntimeException {
        System.out.println("[2] Processando os elementos ...");

        long startProcess = System.currentTimeMillis();

        FinalResult finalResult = new FinalResult();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(T, () -> {
            long endProcess = System.currentTimeMillis();
            System.out.println(">> Duração do processamento "+(endProcess - startProcess)+" ms. <<\n");

            runtime = runtime + (endProcess - startProcess);
            showResult(finalResult, runtime);
        });

        processElements(finalResult, cyclicBarrier);
    }

    /**
     * Inicia o processamento paralelo dos elementos usando threads.
     *
     * Este método divide a lista de elementos em chunks e inicia uma thread para cada chunk. Cada thread executa
     * as operações definidas na interface IOperation para o seu conjunto de elementos e aguarda a barreira cíclica
     * para garantir que todas as threads concluíram antes de prosseguir para a exibição dos resultados finais.
     *
     * @param finalResult    O resultado final acumulado das operações.
     * @param cyclicBarrier  A barreira cíclica para sincronização de threads.
     */
    private void processElements(FinalResult finalResult, CyclicBarrier cyclicBarrier) {
        int chunkSize = elements.size() / T;
        int extraElements = elements.size() % T;

        int start = 0;
        for (int i = 0; i < T; i++) {
            int end = start + chunkSize + (i < extraElements ? 1 : 0);

            IOperation operation = new IOperationImpl(elements.subList(start, end), finalResult);
            OperationTask operationTask = new OperationTask(cyclicBarrier, operation);

            new Thread(operationTask).start();
            start = end;
        }
    }

    /**
     * Exibe os resultados finais do processamento.
     *
     * @param finalResult       O resultado final acumulado das operações.
     * @param processingTime    O tempo total de processamento.
     */
    private void showResult(FinalResult finalResult, long processingTime) {
        System.out.println("Operações Finalizadas... Formatando o resultado ....\n");
        finalResult.print();

        String output = String.format(">> Para um N = %d e um T = %d, o tempo total de execução do programa foi de %d ms.", N, T, processingTime);
        System.out.println(output);
    }
}
