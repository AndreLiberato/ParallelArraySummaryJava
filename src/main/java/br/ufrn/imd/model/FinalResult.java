package br.ufrn.imd.model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

public class FinalResult {

    private double sumTotal;
    private Set<Integer> idsLessThanFive;
    private Set<Integer> idsGreaterOrEqualToFive;
    private Map<Integer, Double> sumTotalByGroup;

    public FinalResult() {
        this.sumTotalByGroup = new HashMap<>();
        this.idsGreaterOrEqualToFive = new HashSet<>();
        this.idsLessThanFive = new HashSet<>();
    }

    public synchronized void updateSumTotal(double sum) {
        sumTotal = sumTotal + sum;
    }

    public synchronized void updateSumTotalByGroup(Map<Integer, Double> totalsByGroup) {
        for (Integer group : totalsByGroup.keySet()) {
            double totalMap = Objects.isNull(sumTotalByGroup.get(group)) ? 0 : sumTotalByGroup.get(group);
            sumTotalByGroup.put(group, totalMap + totalsByGroup.get(group));
        }
    }

    public synchronized void updateIdsLessThanFive(List<Integer> ids) {
        idsLessThanFive.addAll(ids);
    }

    public synchronized void updateIdsGreaterOrEqualToFive(List<Integer> ids) {
        idsGreaterOrEqualToFive.addAll(ids);
    }

    public void print() throws FileNotFoundException {
        System.out.println("Soma total = " + this.sumTotal);

        System.out.println("\nSoma total por grupo:");
        this.sumTotalByGroup.forEach((key, value) -> System.out.println("Grupo " + key + ": " + value));

        System.out.println("Total de elementos: " + (this.idsLessThanFive.size() + this.idsGreaterOrEqualToFive.size()));

        System.out.println("Escrevendo ids menores que cinco: "+ this.idsLessThanFive.size());
        OutputStream osLess = new FileOutputStream("id_less_five.csv");
        Writer wrLess = new OutputStreamWriter(osLess);
        BufferedWriter brLess = new BufferedWriter(wrLess); 
        
        this.idsLessThanFive.forEach(id -> {
            try {
                brLess.write(new String(id + "\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            brLess.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Escrevendo ids maiores ou igual a cinco: "+ this.idsGreaterOrEqualToFive.size());

        OutputStream osGreater= new FileOutputStream("id_greater_five.csv");
        Writer wrGreater = new OutputStreamWriter(osGreater);
        BufferedWriter brGreater = new BufferedWriter(wrGreater); 

        this.idsGreaterOrEqualToFive.forEach(id -> {
            try {
                brGreater.write(new String(id + "\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            brGreater.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
