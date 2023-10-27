package br.ufrn.imd.model;

import java.util.List;
import java.util.Map;

public interface IOperation {

    void sumTotal();

    void sumTotalByGroup();

    void filterIdByTotalLessThanFive();

    void filterIdByTotalGreaterThanFive();

    void executeAll();

}
