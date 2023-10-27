package br.ufrn.imd.model;

import br.ufrn.imd.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Executor {

    private List<Element> elements;

    public Executor() {
        this.elements = new ArrayList<>();
    }

    public void loadElements(int N) {
        final int size = (int) Math.pow(10, N);
        for (int index = 0; index < size; index++) {
            this.elements.add(new Element(index, RandomGenerator.generateValue(), RandomGenerator.generateGroup()));
        }
    }

    public void process(int T) throws RuntimeException {
        FinalResult finalResult = new FinalResult();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(T, () -> {
            System.out.println("Operações Finalizadas... Formatando o resultado ....\n");
            finalResult.print();
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
}
