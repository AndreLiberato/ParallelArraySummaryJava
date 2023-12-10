package br.ufrn.imd.model.operations;

import br.ufrn.imd.model.Element;
import br.ufrn.imd.model.FinalResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A classe GroupFilter implementa a interface Operation e é responsável por realizar a operação de filtragem e agrupamento
 * em um subconjunto de elementos.
 */
public class GroupFilter implements Operation {

    private List<Element> subset;        // Subconjunto de elementos a ser filtrado e agrupado
    private FinalResult referenceResult; // Resultado final a ser atualizado com os totais agrupados

    /**
     * Construtor da classe GroupFilter.
     *
     * @param subset          Subconjunto de elementos a ser filtrado e agrupado.
     * @param referenceResult Resultado final a ser atualizado com os totais agrupados de forma parcial.
     */
    public GroupFilter(List<Element> subset, FinalResult referenceResult) {
        this.subset = subset;
        this.referenceResult = referenceResult;
    }

    /**
     * Implementação do método execute da interface Operation.
     * Realiza a operação de filtragem e agrupamento, atualizando o resultado final.
     */
    @Override
    public void execute() {
        Map<Integer, Double> sumTotalMap = new HashMap<>();

        for (Element element : subset) {
            double sumTotal = Objects.isNull(sumTotalMap.get(element.group())) ? 0 : sumTotalMap.get(element.group());
            sumTotalMap.put(element.group(), sumTotal + element.total());
        }

        referenceResult.updateSumTotalByGroup(sumTotalMap);
    }
}
