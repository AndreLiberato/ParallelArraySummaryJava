package br.ufrn.imd.model;

public interface IOperation {

    void sumTotal();

    void sumTotalByGroup();

    void filterIdByTotalLessThanFive();

    void filterIdByTotalGreaterThanFive();

    void executeAll();

}
