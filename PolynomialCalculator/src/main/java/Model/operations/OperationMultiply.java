package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;

public class OperationMultiply implements OperationWithTwoParameters {

    /**
     * @param m1 - first monomial
     * @param m2 - second monomial
     * @return the resulted monomial after the multiplication of m1 and m2
     */
    @Override
    public Monomial doOperation(Monomial m1, Monomial m2) throws BadInputException {
        double restultedCoeff = m1.getCoefficient() * m2.getCoefficient();
        int resultedPower = m1.getExponent() + m2.getExponent();
        return new Monomial(restultedCoeff, resultedPower, m1.getVariable());
    }

    @Override
    public Polynomial doOperationOnPolynomial(Polynomial p1, Polynomial p2) throws BadInputException {
        Polynomial result = new Polynomial();
        for (Monomial m1 : p1.getMonomials()) {
            for (Monomial m2 : p2.getMonomials()) {
                result.getMonomials().add(doOperation(m1, m2));
            }
        }
        result.addMonomialsWithEqualExponents(new OperationAdd());
        return result;
    }
}
