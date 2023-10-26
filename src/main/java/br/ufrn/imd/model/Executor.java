package br.ufrn.imd.model;

import br.ufrn.imd.utils.ResultProcessor;
import br.ufrn.imd.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

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

    public Result process(int T){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(T, () -> System.out.println("Operações Finalizadas... Formatando o resultado ....\n"));
        List<IOperation> operations = new ArrayList<>(T);

        startOperations(operations, cyclicBarrier, T);

        try {
            cyclicBarrier.await();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }

        List<Result> results = operations.stream().map(IOperation::extractProcessing).toList();
        return this.combineResults(results);
    }

    private void startOperations(List<IOperation> operations, CyclicBarrier cyclicBarrier, int T) {
        List<Element> elements = parallelArraySummary.getElements();

        int chunkSize = elements.size() / T;
        int extraElements = elements.size() % T;
        int startIndex = 0;

        for (int i = 0; i < T; i++) {
            int subListSize = chunkSize + (i < extraElements ? 1 : 0);
            int endIndex = startIndex + subListSize;

            IOperation operation = new IOperationImpl(elements.subList(startIndex, endIndex), cyclicBarrier);
            operations.add(operation);

            new Thread(operation).start();
            startIndex = endIndex;
        }
    }


    private Result combineResults(List<Result> results){
        double sumTotal = ResultProcessor.sumListOfTotals(results.stream().map(Result::sumTotal).toList());
        List<Integer> idsLessThanFive = ResultProcessor.extractIds(results.stream().map(Result::idsLessThanFive).toList());
        List<Integer> idsGreaterOrEqualToFive = ResultProcessor.extractIds(results.stream().map(Result::idsGreaterOrEqualToFive).toList());
        Map<Integer, Double> sumTotalsByGroup = ResultProcessor.sumListOfTotalsByGroup(results.stream().map(Result::sumTotalByGroup).toList());

        return new Result(sumTotal, sumTotalsByGroup, idsLessThanFive, idsGreaterOrEqualToFive);
    }

}
