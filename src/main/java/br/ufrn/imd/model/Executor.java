package br.ufrn.imd.model;

import br.ufrn.imd.model.*;
import br.ufrn.imd.utils.RandomGenerator;

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

    public Result process() {
        IOperation operation = new IOperationImpl();

        List<Element> elements = parallelArraySummary.getElements();

        double sumTotal = operation.sumTotal(elements);
        List<Integer> lessThanFive = operation.filterIdByTotalLessThanFive(elements);
        List<Integer> greaterThanFive = operation.filterIdByTotalGreaterThanFive(elements);
        Map<Integer, Double> sumByGroup = operation.sumTotalByGroup(elements);


        return new Result(sumTotal, sumByGroup, lessThanFive, greaterThanFive);
    }
}
