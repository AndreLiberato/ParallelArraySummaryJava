package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;

public class ParallelArraySummary {

    private List<Element> elements;

    public ParallelArraySummary() {
        this.elements = new ArrayList<Element>();
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<Element> getElements() {
        return this.elements;
    }

    public void addElement(Element element) {
        this.elements.add(element);
    }
}
