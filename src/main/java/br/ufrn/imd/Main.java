package br.ufrn.imd;

import br.ufrn.imd.model.Element;
import br.ufrn.imd.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class ParallelArraySummary {

    public static void main( String[] args ) {

        final int N = 2;
        final int size = (int) Math.pow(10, N);

        List<Element> elements = new ArrayList<>();
        for(int index = 0; index < size; index++){
            elements.add(new Element(index, RandomGenerator.generateValue(), RandomGenerator.generateGroup()));
        }

        elements.forEach(element -> System.out.println(element.toString()));

    }
}
