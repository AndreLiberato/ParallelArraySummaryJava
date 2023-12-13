package br.ufrn.imd.model.operations;

import br.ufrn.imd.model.Element;
import br.ufrn.imd.model.FinalResult;

import java.util.List;

/**
 * A classe TotalSumImpl implementa a interface Operation e é responsável por calcular a soma total de um subconjunto de elementos.
 */
public class TotalSumImpl implements Operation {

    private List<Element> subset;        // Subconjunto de elementos para calcular a soma total
    private FinalResult referenceResult; // Resultado final a ser atualizado com a soma total

    /**
     * Construtor da classe TotalSumImpl.
     *
     * @param subset          Subconjunto de elementos para calcular a soma total.
     * @param referenceResult Resultado final a ser atualizado com a soma total de forma parcial.
     */
    public TotalSumImpl(List<Element> subset, FinalResult referenceResult) {
        this.subset = subset;
        this.referenceResult = referenceResult;
    }

    /**
     * Implementação do método execute da interface Operation.
     * Calcula a soma total do subconjunto de elementos e atualiza o resultado final.
     */
    @Override
    public void execute() {
        double total = 0;

        for (Element element : subset) {
            total += element.total();
        }

        referenceResult.updateSumTotal(total);
    }
}
