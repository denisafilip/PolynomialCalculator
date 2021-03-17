package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;

public class OperationAdd implements OperationWithTwoParameters {
    /**
     * @param m1 - first monomial
     * @param m2 - second monomial
     * @return the resulted monomial after the addition of m1 and m2, if they have the same power
     */
    @Override
    public Monomial doOperation(Monomial m1, Monomial m2) throws BadInputException {
        Monomial result = null;
        if (m1.getExponent().equals(m2.getExponent())) {
            int newCoeff = m1.getIntCoefficient() + m2.getIntCoefficient();
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
            if (m1.isZero()) {
                continue;
            }
            Monomial monomial = result.findMonomial(result.getMonomials(), m1.getExponent());
            if (monomial == null) {
                result.getMonomials().add(m1);
            } else {
                int index = result.getMonomials().indexOf(monomial);
                result.getMonomials().set(index, doOperation(m1, monomial));
            }
        }

        result.addMonomialsWithEqualExponents(new OperationAdd());
        return result;
    }
}
