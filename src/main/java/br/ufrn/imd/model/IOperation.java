package br.ufrn.imd.model;

/**
 * A interface IOperation define operações que podem ser realizadas em uma lista de elementos.
 */
public interface IOperation {

    /**
     * Calcula a soma total dos valores dos elementos.
     */
    void sumTotal();

    /**
     * Calcula a soma total dos valores dos elementos por grupo.
     */
    void sumTotalByGroup();

    /**
     * Filtra os identificadores dos elementos cujo total é inferior a cinco.
     */
    void filterIdByTotalLessThanFive();

    /**
     * Filtra os identificadores dos elementos cujo total é maior ou igual a cinco.
     */
    void filterIdByTotalGreaterThanFive();

    /**
     * Executa todas as operações definidas na interface.
     */
    void executeAll();
}
