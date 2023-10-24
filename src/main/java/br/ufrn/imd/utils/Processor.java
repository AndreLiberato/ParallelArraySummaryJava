package br.ufrn.imd.utils;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.model.Element;
import br.ufrn.imd.model.ParallelArraySummary;

public class Processor {

    private ParallelArraySummary parallelArraySummary;
    private double totalElements;
    private List<Double> totalElementsByGroup;
    private List<Integer> idElementsLessThanFive;
    private List<Integer> idElementsGreaterOrEqualToFive;

    public Processor() {
        this.parallelArraySummary = new ParallelArraySummary();
        this.totalElements = 0;
        this.totalElementsByGroup = new ArrayList<Double>();
        for (int i = 0; i < 5; ++i) {
            totalElementsByGroup.add(0.0);
        }
        this.idElementsLessThanFive = new ArrayList<Integer>();
        this.idElementsGreaterOrEqualToFive = new ArrayList<Integer>();
    }

    public void loadElements(int N) {
        final int size = (int) Math.pow(10, N);
        for (int index = 0; index < size; index++) {
            this.parallelArraySummary
                    .addElement(new Element(index, RandomGenerator.generateValue(), RandomGenerator.generateGroup()));
        }
    }

    public void resolve() {
        for (Element element : parallelArraySummary.getElements()) {
            this.sumTotal(element);
            this.sumTotalByGroup(element);
            this.filterIdByTotal(element);
        }
        this.showResult();
    }

    private void sumTotal(Element element) {
        totalElements += element.getTotal();
    }

    private void sumTotalByGroup(Element element) {
        switch (element.getGroup()) {
            case 1:
                totalElementsByGroup.set(0, totalElementsByGroup.get(0) + element.getTotal());
                break;
            case 2:
                totalElementsByGroup.set(1, totalElementsByGroup.get(1) + element.getTotal());
                break;
            case 3:
                totalElementsByGroup.set(2, totalElementsByGroup.get(2) + element.getTotal());
                break;
            case 4:
                totalElementsByGroup.set(3, totalElementsByGroup.get(3) + element.getTotal());
                break;
            case 5:
                totalElementsByGroup.set(4, totalElementsByGroup.get(4) + element.getTotal());
            default:
                break;
        }
    }

    private void filterIdByTotal(Element element) {
        if (element.getTotal() < 5) {
            idElementsLessThanFive.add(element.getId());
        } else {
            idElementsGreaterOrEqualToFive.add(element.getId());
        }
    }

    private void showResult() {
        System.out.println("Sum of totals: " + this.totalElements);
        System.out.println("Sum totals of groups: " + totalElementsByGroup.toString());
        System.out.println(
                "Id's less than five (" + idElementsLessThanFive.size() + "): " + idElementsLessThanFive.toString());
        System.out.println("Id's greater or equal to five (" + idElementsGreaterOrEqualToFive.size() + "): "
                + idElementsGreaterOrEqualToFive.toString());
    }
}
