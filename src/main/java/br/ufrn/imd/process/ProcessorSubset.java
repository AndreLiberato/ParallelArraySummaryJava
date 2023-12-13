package br.ufrn.imd.process;

import br.ufrn.imd.model.Element;
import br.ufrn.imd.model.FinalResult;
import br.ufrn.imd.model.operations.GroupFilter;
import br.ufrn.imd.model.operations.TotalFilterImpl;
import br.ufrn.imd.model.operations.TotalSumImpl;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * A classe ProcessorSubset implementa a interface Runnable e é responsável por processar um subconjunto de elementos
 * de forma paralela usando múltiplas operações.
 */
public class ProcessorSubset implements Runnable{

    private CyclicBarrier barrier;      // Barreira cíclica para sincronização de threads
    private List<Element> subset;       // Subconjunto a ser processado.
    private FinalResult subSetResult;

    static final int NUMBER_OF_PARALLEL_OPERATIONS = 3;     // Número fixo de operações paralelas que serão executadas

    /**
     * Construtor da classe ProcessorSubset.
     *
     * @param barrier       Barreira cíclica para sincronização de threads.
     * @param subset        Lista de elementos que representa o subconjunto a ser processado.
     * @param subSetResult  Estrutura compartilhada para ser atualizada com o resultado de cada processamento.
     */
    public ProcessorSubset(CyclicBarrier barrier, List<Element> subset, FinalResult subSetResult) {
        this.barrier = barrier;
        this.subset = subset;
        this.subSetResult = subSetResult;
    }

    @Override
    public void run() {
        CyclicBarrier parallelBarrier = new CyclicBarrier(NUMBER_OF_PARALLEL_OPERATIONS, () -> {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });

        ParallelOperation[] parallelOperations = buildSubsetParallelOperation(parallelBarrier);
        for(int i = 0; i < parallelOperations.length; i++){
            new Thread(parallelOperations[i]).start(); // Inicializa a execução das operações paralelas no subconjunto
        }
    }

    /**
     * Constrói e retorna um array de operações paralelas associadas ao subconjunto.
     *
     * @param parallelBarrier Barreira para sincronização das operações paralelas.
     * @return Array de operações paralelas.
     */
    private ParallelOperation[] buildSubsetParallelOperation(CyclicBarrier parallelBarrier) {
        ParallelOperation[] parallelOperations = new ParallelOperation[NUMBER_OF_PARALLEL_OPERATIONS];

        // Constrói cada operação paralela com uma instância específica de operação
        parallelOperations[0] = new ParallelOperation(new TotalSumImpl(subset, subSetResult), parallelBarrier);
        parallelOperations[1] = new ParallelOperation(new GroupFilter(subset, subSetResult), parallelBarrier);
        parallelOperations[2] = new ParallelOperation(new TotalFilterImpl(subset, subSetResult), parallelBarrier);

        return parallelOperations;
    }
}
