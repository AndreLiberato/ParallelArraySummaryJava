package br.ufrn.imd.process;

import br.ufrn.imd.model.operations.Operation;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * A classe ParallelOperation é responsável por executar uma operação de forma paralela.
 */
public class ParallelOperation implements Runnable {

    private Operation operation;    // Operação a ser executada de forma paralela
    private CyclicBarrier barrier;  // Barreira cíclica para sincronização das operações

    /**
     * Construtor da classe ParallelOperation.
     *
     * @param operation Operação a ser executada de forma paralela.
     * @param barrier   Barreira cíclica para sincronização das operações.
     */
    public ParallelOperation(Operation operation, CyclicBarrier barrier) {
        this.operation = operation;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            operation.execute();  // Executa a operação associada
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
