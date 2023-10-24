package br.ufrn.imd.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class IOperationImpl implements IOperation{

    @Override
    public double sumTotal(List<Element> elements) {
        double total = 0;
        for(Element element : elements){
            total += element.getTotal();
        }
        return total;
    }

    @Override
    public Map<Integer, Double> sumTotalByGroup(List<Element> elements) {
        Map<Integer, Double> sumTotalMap = new ConcurrentHashMap<>();
        for(Element element : elements){

            double sumTotal = Objects.isNull(sumTotalMap.get(element.getGroup())) ? 0 : sumTotalMap.get(element.getGroup());
            double total = sumTotal + element.getTotal();
            sumTotalMap.put(element.getGroup(), total);
        }
        return sumTotalMap;
    }


    @Override
    public List<Integer> filterIdByTotalLessThanFive(List<Element> elements) {
        return elements.stream().filter(element -> element.getTotal() < 5).map(Element::getId).toList();
    }

    @Override
    public List<Integer> filterIdByTotalGreaterThanFive(List<Element> elements) {
        return elements.stream().filter(element -> element.getTotal() >= 5).map(Element::getId).toList();
    }

}
