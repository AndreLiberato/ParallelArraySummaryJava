package br.ufrn.imd;

import br.ufrn.imd.model.Executor;

public class Main {

    public static void main(String[] args) {
        final int N = 5;
        final int T = 1;

        Executor executor = new Executor();

        executor.loadElements(N);
        executor.process(T);
    }
}
