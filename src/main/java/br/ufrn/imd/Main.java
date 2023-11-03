package br.ufrn.imd;

import br.ufrn.imd.model.Executor;

public class Main {

    public static void main(String[] args) {
        int N = 1;
        int T = 1;

        try {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            System.err.println("Argument must be an integer.");
            System.exit(1);
        }

        System.out.println("Inciando programa...");
        long start = System.currentTimeMillis();

        Executor executor = new Executor(N);

        executor.loadElements(N);
        executor.process(T);

        long end = System.currentTimeMillis();
        System.out.println("Execução completa finalizada em(ms): " + (end - start));
        System.out.println("-------------------------------------------------");
    }
}
