package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;

public class OperationSubtract implements OperationWithTwoParameters {

    /**
     * @param m1 - first monomial
     * @param m2 - second monomial
     * @return the resulted monomial after the subtraction of m1 and m2, if they have the same power
     */
    @Override
    public Monomial doOperation(Monomial m1, Monomial m2) throws BadInputException {
        Monomial result = null;
        if (m1.getExponent().equals(m2.getExponent())) {
            double newCoeff = m1.getCoefficient() - m2.getCoefficient();
            if (newCoeff == 0) {
                result = new Monomial(newCoeff, 0, m1.getVariable());
            } else {
                result = new Monomial(newCoeff, m1.getExponent(), m1.getVariable());
            }
        }
        return result;
    }

    @Override
    public Polynomial doOperationOnPolynomial(Polynomial p1, Polynomial p2) throws BadInputException {
        Polynomial result = p1.deepCopyPolynomial();
        if (p1.getDegree() < p2.getDegree()) {
            result.setDegree(p2.getDegree());
        }
        for (Monomial m1 : p2.getMonomials()) {
            Monomial monomial = result.findMonomial(result.getMonomials(), m1.getExponent());
            if (monomial == null) {
                m1.setCoefficient(-m1.getCoefficient());
                result.getMonomials().add(m1);
            } else {
                int index = result.getMonomials().indexOf(monomial);
                Monomial subtractResult = doOperation(monomial, m1);
                if (subtractResult.getCoefficient() != 0) {
                    result.getMonomials().set(index, subtractResult);
                } else {
                    result.getMonomials().remove(monomial);
                }

            }
        }
        result.addMonomialsWithEqualExponents(new OperationAdd());
        return result;
    }
}
