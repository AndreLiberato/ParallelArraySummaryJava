package br.ufrn.imd.model;

public interface IOperation extends Runnable {

    void sumTotal();

    void sumTotalByGroup();

    void filterIdByTotalLessThanFive();

    void filterIdByTotalGreaterThanFive();

    Result gerPartialResult();

}
