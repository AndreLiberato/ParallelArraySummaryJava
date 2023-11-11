package br.ufrn.imd.utils;

import java.util.Random;

/**
 * A classe RandomGenerator fornece métodos para gerar valores aleatórios.
 */
public class RandomGenerator {

    /**
     * Gera um número aleatório para representar um grupo.
     *
     * @return Um número aleatório entre 1 e 5.
     */
    public static int generateGroup() {
        return new Random().nextInt(5) + 1;
    }

    /**
     * Gera um valor aleatório do tipo double.
     *
     * @return Um valor aleatório do tipo double no intervalo de 0 a 10.
     */
    public static double generateValue() {
        return new Random().nextDouble() * 10;
    }
}
