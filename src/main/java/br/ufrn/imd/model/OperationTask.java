package br.ufrn.imd.model;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class OperationTask implements Runnable{

    private CyclicBarrier barrier;
    private IOperation operation;

    public OperationTask(CyclicBarrier barrier, IOperation operation) {
        this.barrier = barrier;
        this.operation = operation;
    }

    @Override
    public void run() {
        try {
            operation.executeAll();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println("[Error]: "+e.getMessage());
        }
    }

}
