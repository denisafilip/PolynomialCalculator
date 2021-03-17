package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;

public interface OperationWithOneParameter {
    Monomial doOperation(Monomial m) throws BadInputException;
    Polynomial doOperationOnPolynomial(Polynomial p) throws BadInputException;
}
