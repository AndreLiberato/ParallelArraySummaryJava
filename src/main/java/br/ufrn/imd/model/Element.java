package br.ufrn.imd.model;

/**
 * Representa um elemento imutável com id, valor total associado e identificador de grupo.
 * */
public record Element(long id, double total, int group){
}