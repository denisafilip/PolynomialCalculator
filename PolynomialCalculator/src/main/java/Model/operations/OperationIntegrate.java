package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;

public class OperationIntegrate implements OperationWithOneParameter {
    @Override
    public Monomial doOperation(Monomial m) throws BadInputException {
        int resultedPower = m.incrementExponent();
        double resultedCoeff = m.getCoefficient() / resultedPower;
        return new Monomial(resultedCoeff, resultedPower, m.getVariable());
    }

    @Override
    public Polynomial doOperationOnPolynomial(Polynomial p) throws BadInputException {
        Polynomial result = new Polynomial();
        for (Monomial m1 : p.getMonomials()) {
            result.getMonomials().add(doOperation(m1));
        }
        result.addMonomialsWithEqualExponents(new OperationAdd());
        return result;
    }
}
