package br.ufrn.imd.model;

import java.util.List;
import java.util.Map;

public record Result(double sumTotal, Map<Integer, Double> sumTotalByGroup, List<Integer> idsLessThanFive, List<Integer> idsGreaterOrEqualToFive) {
}
