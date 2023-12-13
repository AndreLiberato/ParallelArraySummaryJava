package br.ufrn.imd.model;

import java.util.*;

/**
 * A classe FinalResult representa o resultado final acumulado das operações realizadas.
 */
public class FinalResult {

    private double sumTotal;                        // Soma total dos valores dos elementos
    private long quantityLessThanFive;              // Número de identificadores cujo valor total é menor que cinco
    private long quantityGreaterOrEqualToFive;      // Número de identificadores cujo valor total é maior ou igual a cinco
    private Map<Integer, Double> sumTotalByGroup;   // Soma total dos valores dos elementos por grupo


    /**
     * Construtor da classe FinalResult. Inicializa as estruturas de dados.
     */
    public FinalResult() {
        this.sumTotalByGroup = new HashMap<>();
    }

    /**
     * Atualiza a soma total dos valores dos elementos.
     *
     * @param sum O valor a ser adicionado à soma total.
     */
    public synchronized void updateSumTotal(double sum) {
        sumTotal = sumTotal + sum;
    }

    /**
     * Atualiza a soma total dos valores dos elementos por grupo.
     *
     * @param totalsByGroup Um mapa representando a soma total por grupo.
     */
    public synchronized void updateSumTotalByGroup(Map<Integer, Double> totalsByGroup) {
        for (Integer group : totalsByGroup.keySet()) {
            double totalMap = Objects.isNull(sumTotalByGroup.get(group)) ? 0 : sumTotalByGroup.get(group);
            sumTotalByGroup.put(group, totalMap + totalsByGroup.get(group));
        }
    }

    /**
     * Atualiza o contador de elementos cujo total é menor que cinco.
     *
     * @param quantityBySublist quantidade a ser somada ao contador total.
     */
    public synchronized void updateTotalLessThanFive(long quantityBySublist) {
        quantityLessThanFive += quantityBySublist;
    }

    /**
     * Atualiza o contador de elementos cujo total é maior ou igual a cinco.
     *
     * @param quantityBySublist quantidade a ser somada ao contador total.
     */
    public synchronized void updateTotalGreaterOrEqualToFive(long quantityBySublist) {
        quantityGreaterOrEqualToFive += quantityBySublist;
    }

    /**
     * Imprime o resultado final na saída padrão.
     */
    public void print() {
        System.out.println("Soma total = " + this.sumTotal);

        System.out.println("\nSoma total por grupo:");
        this.sumTotalByGroup.forEach((key, value) -> System.out.println("Grupo " + key + ": " + value));

        System.out.println("\nQtd. de Ids cujo total é menor que 5: " + this.quantityLessThanFive + ".");

        System.out.println("\nQtd. de Ids cujo total é maior ou igual a 5: " + this.quantityGreaterOrEqualToFive + ".\n");
    }
}
