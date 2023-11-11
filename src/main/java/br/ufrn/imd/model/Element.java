package br.ufrn.imd.model;

/**
 * A classe Element representa um elemento com um identificador único.
 */
public class Element {

    private int id;         //  Identificador único do elemento.

    private double total;   // Valor associado ao elemento.

    private int group;      // Grupo ao qual o elemento pertence


    /**
     * Construtor da classe Element.
     *
     * @param id    O identificador único do elemento.
     * @param total O valor associado ao elemento.
     * @param group O grupo ao qual o elemento pertence.
     */
    public Element(int id, double total, int group) {
        this.id = id;
        this.total = total;
        this.group = group;
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

    /**
     * Sobrescrita do método toString para fornecer uma representação em string do objeto Element.
     *
     * @return Uma string que representa o objeto Element.
     */
    @Override
    public String toString() {
        return "Elemento [ id = " + this.id + ", total = " + this.total + ", grupo = " + this.group + " ]";
    }
}
