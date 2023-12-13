package br.ufrn.imd.model.operations;

import br.ufrn.imd.model.Element;
import br.ufrn.imd.model.FinalResult;

import java.util.List;
import java.util.function.Predicate;

/**
 * Esta classe implementa a interface Operation e é responsável por filtrar um subconjunto de elementos
 * com base em um predicado.
 */
public class TotalFilterImpl implements Operation {

    private List<Element> subset;        // Subconjunto de elementos a ser filtrado
    private FinalResult referenceResult; // Resultado final a ser atualizado com informações da filtragem

    /**
     * Construtor da classe TotalFilterImpl.
     *
     * @param subset          Subconjunto de elementos a ser filtrado.
     * @param referenceResult Resultado final a ser atualizado com informações da filtragem.
     */
    public TotalFilterImpl(List<Element> subset, FinalResult referenceResult) {
        this.subset = subset;
        this.referenceResult = referenceResult;
    }

    /**
     * Implementação do método execute da interface Operation.
     * Filtra o subconjunto de elementos com base no predicado e atualiza o resultado final com informações da filtragem.
     */
    @Override
    public void execute() {
        long totalGreaterOrEqualToFive = 0, totalLessThanFive = 0;
        for(Element element : subset){
            if (element.total() >= 5) {
                totalGreaterOrEqualToFive+=1;
            } else {
                totalLessThanFive+=1;
            }
        }

        referenceResult.updateTotalGreaterOrEqualToFive(totalGreaterOrEqualToFive);
        referenceResult.updateTotalLessThanFive(totalLessThanFive);
    }
}
