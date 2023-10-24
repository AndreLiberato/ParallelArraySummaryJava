package br.ufrn.imd.model;

import java.util.List;
import java.util.Map;

public interface IOperation {

    double sumTotal(List<Element> element);

    Map<Integer, Double> sumTotalByGroup(List<Element> element);

    List<Integer> filterIdByTotalLessThanFive(List<Element> elements);

    List<Integer> filterIdByTotalGreaterThanFive(List<Element> elements);

}
