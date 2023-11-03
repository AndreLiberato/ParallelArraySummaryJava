package br.ufrn.imd.model;

import br.ufrn.imd.utils.RandomGenerator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Executor {

    private List<Element> elements;

    public Executor(int N) {
        final int size = (int) Math.pow(10, N);
        System.out.println("Inciando alocação de recursos.");
        long start = System.currentTimeMillis();

        this.elements = new ArrayList<>(Arrays.asList(new Element[size]));

        long end = System.currentTimeMillis();
        System.out.println("Alocação terminada. Duração(ms): " + (end - start));
        System.out.println("-------------------------------------------------");
    }

    public void loadElements(int N) {
        System.out.println("Preparando dados para execução.");
        long start = System.currentTimeMillis();
        final int size = (int) Math.pow(10, N);
        for (int index = 0; index < size; index++) {
            this.elements.set(index,
                    new Element(index, RandomGenerator.generateValue(), RandomGenerator.generateGroup()));
        }
        long end = System.currentTimeMillis();
        System.out.println("Preparação terminada. Duração(ms): " + (end - start));
        System.out.println("-------------------------------------------------");

    }

    public void process(int T) throws RuntimeException {

        System.out.println("Iniciando processamento dos dados.");
        long startProgram = System.currentTimeMillis();

        FinalResult finalResult = new FinalResult();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(T, () -> {
            long endProgram = System.currentTimeMillis();
            System.out.println("Processamento de dados terminados. Duração(ms): " + (endProgram - startProgram));
            System.out.println("-------------------------------------------------");

            try {
                finalResult.print();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        int chunkSize = elements.size() / T;
        int extraElements = elements.size() % T;

        int start = 0;
        List<Thread> lt = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            int end = start + chunkSize + (i < extraElements ? 1 : 0);

            IOperation operation = new IOperationImpl(elements.subList(start, end), finalResult);
            OperationTask operationTask = new OperationTask(cyclicBarrier, operation);

            lt.add(new Thread(operationTask));
            start = end;
        }

        lt.forEach(t -> {
            t.start();
        });

        lt.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        
    }
}
