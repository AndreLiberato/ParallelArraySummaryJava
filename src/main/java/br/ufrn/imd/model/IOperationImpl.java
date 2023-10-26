package br.ufrn.imd.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class IOperationImpl implements IOperation {

    private List<Element> elements;
    private Result result;
    private CyclicBarrier cyclicBarrier;

    public IOperationImpl(List<Element> elements, CyclicBarrier cyclicBarrier) {
        this.elements = elements;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public double sumTotal() {
        double total = 0;
        for (Element element : this.elements) {
            total += element.getTotal();
        }
        return total;
    }

    @Override
    public Map<Integer, Double> sumTotalByGroup() {
        Map<Integer, Double> sumTotalMap = new ConcurrentHashMap<>();
        for(Element element : this.elements){

            double sumTotal = Objects.isNull(sumTotalMap.get(element.getGroup())) ? 0 : sumTotalMap.get(element.getGroup());
            double total = sumTotal + element.getTotal();
            sumTotalMap.put(element.getGroup(), total);
        }
        return sumTotalMap;
    }

    @Override
    public List<Integer> filterIdByTotalLessThanFive() {
        return elements.stream().filter(element -> element.getTotal() < 5).map(Element::getId).toList();
    }

    @Override
    public List<Integer> filterIdByTotalGreaterThanFive() {
        return elements.stream().filter(element -> element.getTotal() >= 5).map(Element::getId).toList();
    }

    @Override
    public Result extractProcessing() {
        return this.result;
    }

    @Override
    public void run() {
        double sumTotal = this.sumTotal();
        List<Integer> lessThanFive = this.filterIdByTotalLessThanFive();
        List<Integer> greaterThanFive = this.filterIdByTotalGreaterThanFive();
        Map<Integer, Double> sumByGroup = this.sumTotalByGroup();

        this.result = new Result(sumTotal, sumByGroup, lessThanFive, greaterThanFive);

        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
