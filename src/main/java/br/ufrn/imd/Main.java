package br.ufrn.imd;

import br.ufrn.imd.model.ParallelArraySummary;

public class Main {

    public static void main(String[] args) {
        final int N = 2;

        ParallelArraySummary parallelArraySummary = new ParallelArraySummary();
        parallelArraySummary.loadElements(N);

        parallelArraySummary.getElements().forEach(element -> System.out.println(element.toString()));

    }
}
