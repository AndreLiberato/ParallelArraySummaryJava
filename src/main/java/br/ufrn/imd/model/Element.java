package br.ufrn.imd.model;

public class Element {

    private int id;
    private double total;
    private int group;

    public Element(int id, double total, int group) {
        this.id = id;
        this.total = total;
        this.group = group;
    }

    public Element() {
        this.id = 0;
        this.total = 0;
        this.group = 0;
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Elemento [ id = "+this.id+", total = "+this.total+", grupo = "+this.group+" ]";
    }
}
