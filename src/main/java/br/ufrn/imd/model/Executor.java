package br.ufrn.imd.model;

import br.ufrn.imd.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Executor {

    private List<Element> elements;
    private int N;
    private int T;
    private long processingTime;

    public Executor(int N, int T) {
        this.elements = new ArrayList<>();
        this.N = N;
        this.T = T;
        this.processingTime = 0;
    }

    public void loadElements() {
        long startLoading = System.currentTimeMillis();

        final long size = (long) Math.pow(10, N);
        for (int index = 0; index < size; index++) {
            this.elements.add(new Element(index, RandomGenerator.generateValue(), RandomGenerator.generateGroup()));
        }

        long endLoading = System.currentTimeMillis();
        processingTime = endLoading - startLoading;
    }

    public void process() throws RuntimeException {
        long startProcess = System.currentTimeMillis();

        FinalResult finalResult = new FinalResult();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(T, () -> {
            long endProcess = System.currentTimeMillis();
            processingTime = processingTime + (endProcess - startProcess);

            showResult(finalResult, processingTime);
        });

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

    private void showResult(FinalResult finalResult, long processingTime) {
        System.out.println("\nOperações Finalizadas... Formatando o resultado ....\n");
        finalResult.print();

        String output = String.format(">> Para um N = %d e um T = %d, o tempo de processamento foi de %d ms", N, T, processingTime);
        System.out.println(output);
    }
}
