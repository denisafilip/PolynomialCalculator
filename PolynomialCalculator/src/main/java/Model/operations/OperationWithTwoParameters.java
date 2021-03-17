package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;

public interface OperationWithTwoParameters {
    Monomial doOperation(Monomial m1, Monomial m2) throws BadInputException;
    Polynomial doOperationOnPolynomial(Polynomial p1, Polynomial p2) throws BadInputException;
}
