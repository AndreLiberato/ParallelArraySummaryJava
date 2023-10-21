package br.ufrn.imd.utils;

import java.util.Random;

public class RandomGenerator {

    public static int generateGroup(){
        return new Random().nextInt(5) + 1;
    }

    public static double generateValue(){
        return new Random().nextDouble() * 10d;
    }
}
