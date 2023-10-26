package br.ufrn.imd.utils;

import br.ufrn.imd.model.Result;

public class Output {

    public static void showResult(Result result) {
        System.out.println("Soma total = " + result.totalElements());

        System.out.println("Soma total por grupo:");
        result.totalByGroup().forEach((key, value) -> System.out.println("Grupo " + key + ": " + value));

        System.out.println("Ids menores que 5: ");
        result.idElementsLessThanFive().forEach(id -> System.out.print(id + " "));

        System.out.println("Ids maiores ou igual que 5: ");
        result.idElementsGreaterOrEqualToFive().forEach(id -> System.out.print(id + " "));
    }

}
