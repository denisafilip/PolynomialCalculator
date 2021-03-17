package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperationAddTest {

    private final OperationWithTwoParameters addOperation = new OperationAdd();

    @Test
    public void test_doOperation_sameExponentAndSameSign() throws BadInputException {
        Monomial m1 = new Monomial(2, 5, 'x');
        Monomial m2 = new Monomial(1, 5, 'x');
        Monomial result = new Monomial(3, 5, 'x');
        Monomial computedResult = addOperation.doOperation(m1, m2);

        assertEquals("The result of adding the monomials 2x^5 and x^5 is 3x^5", result, computedResult);
    }

    @Test
    public void test_doOperation_differentExponentAndSameSign() throws BadInputException {
        Monomial m1 = new Monomial(2, 2, 'x');
        Monomial m2 = new Monomial(1, 3, 'x');
        Monomial computedResult = addOperation.doOperation(m1, m2);

        assertNull("The result of adding the monomials 2x^2 and x^3 is null", computedResult);
    }

    @Test
    public void test_doOperation_sameExponentAndDifferentSign() throws BadInputException {
        Monomial m1 = new Monomial(-2, 5, 'x');
        Monomial m2 = new Monomial(1, 5, 'x');
        Monomial result = new Monomial(-1, 5, 'x');
        Monomial computedResult = addOperation.doOperation(m1, m2);

        assertEquals("The result of adding the monomials -2x^5 and x^5 is -x^5", result, computedResult);
    }

    @Test
    public void test_doOperation_differentExponentAndDifferentSign() throws BadInputException {
        Monomial m1 = new Monomial(-2, 2, 'x');
        Monomial m2 = new Monomial(1, 3, 'x');
        Monomial computedResult = addOperation.doOperation(m1, m2);

        assertNull("The result of adding the monomials -2x^2 and x^3 is null", computedResult);
    }

    @Test
    public void test_doOperation_exponentZeroAndSameSign() throws BadInputException {
        Monomial m1 = new Monomial(4, 0, 'x');
        Monomial m2 = new Monomial(5, 0, 'x');
        Monomial result = new Monomial(9, 0, 'x');
        Monomial computedResult = addOperation.doOperation(m1, m2);

        assertEquals("The result of adding the monomials 4 and 5 is 9", result, computedResult);
    }

    @Test
    public void test_doOperation_exponentZeroAndDifferentSign() throws BadInputException {
        Monomial m1 = new Monomial(-4, 0, 'x');
        Monomial m2 = new Monomial(5, 0, 'x');
        Monomial result = new Monomial(1, 0, 'x');
        Monomial computedResult = addOperation.doOperation(m1, m2);

        assertEquals("The result of adding the monomials -4 and 5 is 1", result, computedResult);
    }

    @Test
    public void test_doOperation_commutativeOperation() throws BadInputException {
        Monomial m1 = new Monomial(2, 2, 'x');
        Monomial m2 = new Monomial(3, 2, 'x');
        Monomial computedResult = addOperation.doOperation(m1, m2);

        Monomial computedResult2 = addOperation.doOperation(m2, m1);

        assertEquals("The result of 2x^2+3x^2 is the same as the result of 3x^2+2x^2.", computedResult, computedResult2);
    }

    @Test
    public void test_doOperationOnPolynomial_positivePolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("x^3+x^2+x+1");
        Polynomial p2 = new Polynomial("x^4+x^2+x+3");
        Polynomial result = new Polynomial("x^4+x^3+2x^2+2x+4");
        Polynomial computedResult = addOperation.doOperationOnPolynomial(p1, p2);

        assertEquals("The result of adding the polynomials x^3+x^2+x+1 and x^4+x^2+x+3 is x^4+x^3+2x^2+2x+4", result, computedResult);
    }

    @Test
    public void test_doOperationOnPolynomial_negativePolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("-2x-2x^2");
        Polynomial p2 = new Polynomial("-3x-8x^5");
        Polynomial result = new Polynomial("-5x-2x^2-8x^5");
        Polynomial computedResult = addOperation.doOperationOnPolynomial(p1, p2);

        assertEquals("The result of adding the polynomials -2x-2x^2 and -3x-8x^5 is -5x-2x^2-8x^5", result, computedResult);
    }

    @Test
    public void test_doOperationOnPolynomial_differentLengthPolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("x^3");
        Polynomial p2 = new Polynomial("6x^5+3x^3");
        Polynomial result = new Polynomial("6x^5+4x^3");
        Polynomial computedResult = addOperation.doOperationOnPolynomial(p1, p2);

        assertEquals("The result of adding the polynomials x^3 and 6x^5+3x^3 is 6x^5+4x^3", result, computedResult);
    }

    @Test
    public void test_doOperationOnPolynomial_addingPolynomialToZero() throws BadInputException {
        Polynomial p1 = new Polynomial("0");
        Polynomial p2 = new Polynomial("x");
        Polynomial result = new Polynomial("x");
        Polynomial computedResult = addOperation.doOperationOnPolynomial(p1, p2);

        assertEquals("The result of adding the polynomials 0 and x is x", result, computedResult);
    }

    @Test
    public void test_doOperationOnPolynomial_inversePolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("x+x^2");
        Polynomial p2 = new Polynomial("-x-x^2");
        Polynomial result = new Polynomial("0");
        Polynomial computedResult = addOperation.doOperationOnPolynomial(p1, p2);

        assertEquals("The result of adding the polynomials x+x^2 and -x-x^2 is 0", result, computedResult);
    }


}