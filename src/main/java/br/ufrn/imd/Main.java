package br.ufrn.imd;

import br.ufrn.imd.model.Executor;

public class Main {

    public static void main(String[] args) {
        int N = 0, T = 0;

        try {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        } catch(IllegalArgumentException e) {
            System.err.println("Argument must be an integer.");
            System.exit(1);
        }

        System.out.println("Inciando programa...");

        Executor executor = new Executor(N, T);
        executor.loadElements();
        executor.process();

    }
}
