package br.ufrn.imd.model;

import java.util.List;
import java.util.Map;

public interface IOperation extends Runnable {

    double sumTotal();

    Map<Integer, Double> sumTotalByGroup();

    List<Integer> filterIdByTotalLessThanFive();

    List<Integer> filterIdByTotalGreaterThanFive();

    Result extractProcessing();


}
