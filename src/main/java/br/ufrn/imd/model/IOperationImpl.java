package br.ufrn.imd.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A classe IOperationImpl implementa as operações definidas na interface IOperation.
 */
public class IOperationImpl implements IOperation {

    private List<Element> elements;    // Lista de elementos para operações
    private FinalResult finalResult;   // Resultado final acumulado das operações

    /**
     * Construtor da classe IOperationImpl.
     *
     * @param elements    Lista de elementos para operações.
     * @param finalResult Resultado final acumulado das operações.
     */
    public IOperationImpl(List<Element> elements, FinalResult finalResult) {
        this.elements = elements;
        this.finalResult = finalResult;
    }

    @Override
    public void sumTotal() {
        double total = 0;
        for (Element element : this.elements) {
            total += element.getTotal();
        }

        finalResult.updateSumTotal(total);
    }

    @Override
    public void sumTotalByGroup() {
        Map<Integer, Double> sumTotalMap = new HashMap<>();
        for (Element element : this.elements) {
            double sumTotal = Objects.isNull(sumTotalMap.get(element.getGroup())) ? 0 : sumTotalMap.get(element.getGroup());
            sumTotalMap.put(element.getGroup(), sumTotal + element.getTotal());
        }

        finalResult.updateSumTotalByGroup(sumTotalMap);
    }

    @Override
    public void filterIdByTotalLessThanFive() {
        List<Integer> ids = elements.stream().filter(element -> element.getTotal() < 5).map(Element::getId).toList();
        finalResult.updateIdsLessThanFive(ids);
    }

    @Override
    public void filterIdByTotalGreaterThanFive() {
        List<Integer> ids = elements.stream().filter(element -> element.getTotal() >= 5).map(Element::getId).toList();
        finalResult.updateIdsGreaterOrEqualToFive(ids);
    }

    @Override
    public void executeAll() {
        this.sumTotal();
        this.filterIdByTotalLessThanFive();
        this.filterIdByTotalGreaterThanFive();
        this.sumTotalByGroup();
    }
}
