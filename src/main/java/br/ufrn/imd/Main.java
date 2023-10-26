package br.ufrn.imd;

import br.ufrn.imd.model.Executor;
import br.ufrn.imd.model.Result;
import br.ufrn.imd.utils.Output;

public class Main {

    public static void main(String[] args) {
        final int N = 2;
        final int numThreads = 23;

        Executor p = new Executor();

        p.loadElements(N);

        Result output = p.process(numThreads);

        Output.showResult(output);
    }
}
