package br.ufrn.imd;

import br.ufrn.imd.process.Executor;

/**
 * A classe responsável por iniciar a execução do Executor.
 */
public class Main {

    /**
     * Método principal que inicia o programa.
     *
     * @param args Os argumentos da linha de comando, esperados para serem dois inteiros N e T.
     */
    public static void main(String[] args) {
        int N = 0, T = 0;

        try {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Argument must be an integer.");
            System.exit(1);
        }

        System.out.println("Inciando programa...\n");

        Executor executor = new Executor(N, T);
        executor.loadElements();
        executor.process();
    }
}
