package br.ufrn.imd.utils;

import br.ufrn.imd.model.Result;

public class Output {

    public static void showResult(Result result) {
        System.out.println("Soma total = " + result.sumTotal());

        System.out.println("\nSoma total por grupo:");
        result.sumTotalByGroup().forEach((key, value) -> System.out.println("Grupo " + key + ": " + value));

        System.out.println("\nIds menores que 5: ");
        result.idsLessThanFive().forEach(id -> System.out.print(id + " "));

        System.out.println("\nIds maiores ou igual que 5: ");
        result.idsGreaterOrEqualToFive().forEach(id -> System.out.print(id + " "));
    }

}
