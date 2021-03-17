package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;

public class OperationDifferentiate implements OperationWithOneParameter{
    @Override
    public Monomial doOperation(Monomial m) throws BadInputException {
        int resultedCoeff = m.getIntCoefficient()*m.getExponent();
        int resultedExponent = m.decrementExponent();
        return new Monomial(resultedCoeff, resultedExponent, m.getVariable());
    }

    @Override
    public Polynomial doOperationOnPolynomial(Polynomial p) throws BadInputException {
        Polynomial result = new Polynomial();
        for (Monomial m : p.getMonomials()) {
            result.getMonomials().add(doOperation(m));
        }
        result.addMonomialsWithEqualExponents(new OperationAdd());
        return result;
    }
}
