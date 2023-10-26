package br.ufrn.imd.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ResultProcessor {

    public static List<Integer> extractIds(List<List<Integer>> resultsWithMultipleIds){
        return resultsWithMultipleIds.stream()
                .flatMap(List::stream)
                .toList();
    }

    public static double sumListOfTotals(List<Double> totals){
        return totals.stream().reduce(0d, Double::sum);
    }

    public static Map<Integer, Double> sumListOfTotalsByGroup(List<Map<Integer, Double>> resultsWithMultipleTotalsByGroup){
        Map<Integer, Double> resultMap = new HashMap<>();

        for(Map<Integer, Double> map : resultsWithMultipleTotalsByGroup){
            for(Integer group : map.keySet()){
                double totalByGroup = Objects.isNull(resultMap.get(group)) ? 0 : resultMap.get(group);
                resultMap.put(group, totalByGroup + map.get(group));
            }
        }

        return resultMap;

        /*
        return resultsWithMultipleTotalsByGroup.stream()
            .flatMap(map -> map.entrySet().stream())
            .collect(Collectors.toMap(
                Map.Entry::getKey, // Chave (grupo)
                Map.Entry::getValue, // Valor (total)
                Double::sum)); // Função para resolver conflitos de chave (soma)
}
         */
    }



}
