package br.ufrn.imd.model;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * A classe OperationTask representa uma tarefa de operação a ser executada em uma thread.
 */
public class OperationTask implements Runnable {

    private CyclicBarrier barrier;  // Barreira cíclica para sincronização de threads
    private IOperation operation;   // Operação a ser executada pela tarefa

    /**
     * Construtor da classe OperationTask.
     *
     * @param barrier   A barreira cíclica para sincronização de threads.
     * @param operation A operação a ser executada pela tarefa.
     */
    public OperationTask(CyclicBarrier barrier, IOperation operation) {
        this.barrier = barrier;
        this.operation = operation;
    }

    /**
     * Executa a operação e aguarda a barreira cíclica para sincronização de threads.
     */
    @Override
    public void run() {
        try {
            operation.executeAll();  // Executa a operação
            barrier.await();         // Aguarda a barreira cíclica para sincronização
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }
}
