package br.ufrn.imd.model;

import br.ufrn.imd.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Executor {

    private ParallelArraySummary parallelArraySummary;

    public Executor() {
        this.parallelArraySummary = new ParallelArraySummary();
    }

    public void loadElements(int N) {
        final int size = (int) Math.pow(10, N);
        for (int index = 0; index < size; index++) {
            this.parallelArraySummary
                    .addElement(new Element(index, RandomGenerator.generateValue(), RandomGenerator.generateGroup()));
        }
    }

    public Result process(int numThreads) {
        List<Element> elements = parallelArraySummary.getElements();

        int elementsPerThreads = elements.size() / numThreads;

        int restElements = elements.size() % numThreads;

        int begin = 0;
        int end = 0;

        List<List<Element>> subListOfElements = new ArrayList<List<Element>>();

        for (int i = 0; i < elements.size() - restElements; i += elementsPerThreads) {
            begin = i;
            end = begin + elementsPerThreads;
            subListOfElements.add(elements.subList(begin, end));
        }

        List<Element> subListOfRestElements = new ArrayList<Element>(elements.subList(end, end + restElements));

        List<Element> firstSubListCopy = new ArrayList<>(subListOfElements.get(0));
        for (Element element : subListOfRestElements) {
            firstSubListCopy.add(element);
        }
        subListOfElements.set(0, firstSubListCopy);

        List<IOperation> operations = new ArrayList<IOperation>();
        for (List<Element> listOfElements : subListOfElements) {
            operations.add(new IOperationImpl(listOfElements));
        }

        List<Thread> threads = new ArrayList<Thread>();

        for (IOperation operation : operations) {
            threads.add(new Thread(operation));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double sumTotal = 0.0;
        List<Integer> lessThanFive = new ArrayList<Integer>();
        List<Integer> greaterThanFive = new ArrayList<Integer>();
        Map<Integer, Double> sumByGroup = new HashMap<Integer, Double>();

        for (IOperation operation : operations) {
            Result result = operation.gerPartialResult();
            sumTotal += result.totalElements();
            lessThanFive.addAll(result.idElementsLessThanFive());
            greaterThanFive.addAll(result.idElementsGreaterOrEqualToFive());

            for (Map.Entry<Integer, Double> entry : result.totalByGroup().entrySet()) {
                int key = entry.getKey();
                double value = entry.getValue();
                sumByGroup.put(key, sumByGroup.getOrDefault(key, 0.0) + value);
            }
        }

        return new Result(sumTotal, sumByGroup, lessThanFive, greaterThanFive);
    }
}
