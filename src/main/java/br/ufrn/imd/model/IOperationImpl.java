package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IOperationImpl implements IOperation {

    List<Element> elements;
    double sumTotal;
    Map<Integer, Double> sumByGroup;
    List<Integer> lessThanFive;
    List<Integer> greaterThanFive;

    public IOperationImpl(List<Element> elements) {
        this.elements = elements;
        sumTotal = 0.0;
        lessThanFive = new ArrayList<Integer>();
        greaterThanFive = new ArrayList<Integer>();
        sumByGroup = new HashMap<Integer, Double>();
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public void sumTotal() {
        for (Element element : elements) {
            sumTotal += element.getTotal();
        }
    }

    @Override
    public void sumTotalByGroup() {
        for (Element element : elements) {
            double sumTotal = Objects.isNull(sumByGroup.get(element.getGroup())) ? 0
                    : sumByGroup.get(element.getGroup());
            double total = sumTotal + element.getTotal();
            sumByGroup.put(element.getGroup(), total);
        }
    }

    @Override
    public void filterIdByTotalLessThanFive() {
        lessThanFive = elements.stream().filter(element -> element.getTotal() < 5).map(Element::getId).toList();
    }

    @Override
    public void filterIdByTotalGreaterThanFive() {
        greaterThanFive = elements.stream().filter(element -> element.getTotal() >= 5).map(Element::getId).toList();
    }

    public Result gerPartialResult() {
        return new Result(sumTotal, sumByGroup, lessThanFive, greaterThanFive);
    }

    @Override
    public void run() {
        this.sumTotal();
        this.sumTotalByGroup();
        this.filterIdByTotalLessThanFive();
        this.filterIdByTotalGreaterThanFive();
    }
}
