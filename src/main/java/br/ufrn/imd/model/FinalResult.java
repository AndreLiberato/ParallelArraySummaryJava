package br.ufrn.imd.model;

import java.util.*;

public class FinalResult {

    private double sumTotal;
    private Set<Integer> idsLessThanFive;
    private Set<Integer> idsGreaterOrEqualToFive;
    private Map<Integer, Double> sumTotalByGroup;

    public FinalResult(){
        this.sumTotalByGroup = new HashMap<>();
        this.idsGreaterOrEqualToFive = new HashSet<>();
        this.idsLessThanFive = new HashSet<>();
    }

    public synchronized void updateSumTotal(double sum){
        sumTotal = sumTotal + sum;
    }

    public synchronized void updateSumTotalByGroup(Map<Integer, Double> totalsByGroup){
        for(Integer group : totalsByGroup.keySet()){
            double totalMap = Objects.isNull(sumTotalByGroup.get(group)) ? 0 : sumTotalByGroup.get(group);
            sumTotalByGroup.put(group, totalMap + totalsByGroup.get(group));
        }
    }

    public synchronized void updateIdsLessThanFive(List<Integer> ids){
        idsLessThanFive.addAll(ids);
    }

    public synchronized void updateIdsGreaterOrEqualToFive(List<Integer> ids){
        idsGreaterOrEqualToFive.addAll(ids);
    }

    public void print(){
        System.out.println("Soma total = " + this.sumTotal);

        System.out.println("\nSoma total por grupo:");
        this.sumTotalByGroup.forEach((key, value) -> System.out.println("Grupo " + key + ": " + value));

        System.out.println("\nIds menores que 5 | Qtd.: "+idsLessThanFive.size()+" , são eles: ");
        this.idsLessThanFive.forEach(id -> System.out.print(id + " "));

        System.out.println("\nIds maiores ou igual que 5 | Qtd.: "+idsGreaterOrEqualToFive.size()+" , são eles: ");
        this.idsGreaterOrEqualToFive.forEach(id -> System.out.print(id + " "));
    }

}
