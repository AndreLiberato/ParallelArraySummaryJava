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
    private boolean flag;                // Indicador para determinar o predicado de filtragem

    /**
     * Construtor da classe TotalFilterImpl.
     *
     * @param subset          Subconjunto de elementos a ser filtrado.
     * @param referenceResult Resultado final a ser atualizado com informações da filtragem.
     * @param flag            Indicador para determinar o predicado de filtragem.
     */
    public TotalFilterImpl(List<Element> subset, FinalResult referenceResult, boolean flag) {
        this.subset = subset;
        this.referenceResult = referenceResult;
        this.flag = flag;
    }

    /**
     * Implementação do método execute da interface Operation.
     * Filtra o subconjunto de elementos com base no predicado e atualiza o resultado final com informações da filtragem.
     */
    @Override
    public void execute() {
        Predicate<Element> predicate = flag ? element -> element.group() >= 5 : element -> element.group() < 5;
        long quantity = subset.stream().filter(predicate).count();

        if (flag) {
            referenceResult.updateIdsGreaterOrEqualToFive(quantity);
        } else {
            referenceResult.updateIdsLessThanFive(quantity);
        }
    }
}
