package br.ufrn.imd.model;

import br.ufrn.imd.utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class ParallelArraySummary {

    private List<Element> elements;

    public ParallelArraySummary(){
        this.elements = new ArrayList<>();
    }

    public List<Element> getElements() {
        return this.elements;
    }

    public void loadElements(int N){
        final int size = (int) Math.pow(10, N);
        for(int index = 0; index < size; index++){
            this.elements.add(new Element(index, RandomGenerator.generateValue(), RandomGenerator.generateGroup()));
        }
    }

    public void process(){}
}
