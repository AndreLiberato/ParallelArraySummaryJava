package br.ufrn.imd;

import br.ufrn.imd.utils.Processor;

public class Main {

    public static void main(String[] args) {
        final int N = 2;

        Processor p = new Processor();

        p.loadElements(N);

        p.resolve();
    }
}
