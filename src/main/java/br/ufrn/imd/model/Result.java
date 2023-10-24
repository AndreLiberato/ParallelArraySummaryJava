package br.ufrn.imd.model;

import java.util.List;
import java.util.Map;

public record Result(double totalElements, Map<Integer, Double> totalByGroup, List<Integer> idElementsLessThanFive, List<Integer> idElementsGreaterOrEqualToFive) {
}
