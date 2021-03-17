package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperationMultiplyTest {

    private final OperationWithTwoParameters multiplyOperation = new OperationMultiply();

    @Test
    public void test_doOperation_sameExponentAndSamePlusSign() throws BadInputException {
        Monomial m1 = new Monomial(2, 5, 'x');
        Monomial m2 = new Monomial(1, 5, 'x');
        Monomial result = new Monomial(2, 10, 'x');
        Monomial computedResult = multiplyOperation.doOperation(m1, m2);

        assertEquals("The result of multiplying the monomials 2x^5 and x^5 is 2x^10", result, computedResult);
    }

    @Test
    public void test_doOperation_sameExponentAndSameMinusSign() throws BadInputException {
        Monomial m1 = new Monomial(-3, 4, 'x');
        Monomial m2 = new Monomial(-7, 4, 'x');
        Monomial result = new Monomial(21, 8, 'x');
        Monomial computedResult = multiplyOperation.doOperation(m1, m2);

        assertEquals("The result of multiplying the monomials -3x^4 and -7x^4 is 21x^8", result, computedResult);
    }

    @Test
    public void test_doOperation_differentExponentAndSamePlusSign() throws BadInputException {
        Monomial m1 = new Monomial(2, 2, 'x');
        Monomial m2 = new Monomial(5, 4, 'x');
        Monomial result = new Monomial(10, 6, 'x');
        Monomial computedResult = multiplyOperation.doOperation(m1, m2);

        assertEquals("The result of multiplying the monomials 2x^2 and 5x^4 is 10x^6", result, computedResult);
    }

    @Test
    public void test_doOperation_differentExponentAndSameMinusSign() throws BadInputException {
        Monomial m1 = new Monomial(-2, 2, 'x');
        Monomial m2 = new Monomial(-5, 4, 'x');
        Monomial result = new Monomial(10, 6, 'x');
        Monomial computedResult = multiplyOperation.doOperation(m1, m2);

        assertEquals("The result of multiplying the monomials -2x^2 and -5x^4 is 10x^6", result, computedResult);
    }

    @Test
    public void test_doOperation_sameExponentAndDifferentSign() throws BadInputException {
        Monomial m1 = new Monomial(-2, 5, 'x');
        Monomial m2 = new Monomial(1, 5, 'x');
        Monomial result = new Monomial(-2, 10, 'x');
        Monomial computedResult = multiplyOperation.doOperation(m1, m2);

        assertEquals("The result of multiplying the monomials -2x^5 and x^5 is -2x^10", result, computedResult);
    }

    @Test
    public void test_doOperation_differentExponentAndDifferentSign() throws BadInputException {
        Monomial m1 = new Monomial(-2, 2, 'x');
        Monomial m2 = new Monomial(2, 3, 'x');
        Monomial result = new Monomial(-4, 5, 'x');
        Monomial computedResult = multiplyOperation.doOperation(m1, m2);

        assertEquals("The result of multiplying the monomials -2x^2 and 2x^3 is -4x^5", result, computedResult);
    }

    @Test
    public void test_doOperation_exponentZeroAndSameSign() throws BadInputException {
        Monomial m1 = new Monomial(4, 0, 'x');
        Monomial m2 = new Monomial(5, 0, 'x');
        Monomial result = new Monomial(20, 0, 'x');
        Monomial computedResult = multiplyOperation.doOperation(m1, m2);

        assertEquals("The result of multiplying the monomials 4 and 5 is 20", result, computedResult);
    }

    @Test
    public void test_doOperation_exponentZeroAndDifferentSign() throws BadInputException {
        Monomial m1 = new Monomial(-4, 0, 'x');
        Monomial m2 = new Monomial(5, 0, 'x');
        Monomial result = new Monomial(-20, 0, 'x');
        Monomial computedResult = multiplyOperation.doOperation(m1, m2);

        assertEquals("The result of multiplying the monomials -4 and 5 is -20", result, computedResult);
    }

    @Test
    public void test_doOperationOnPolynomials_positivePolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("x^2+x");
        Polynomial p2 = new Polynomial("x+1");
        Polynomial result = new Polynomial("x^3+2x^2+x");

        assertEquals("The result of multiplying the polynomials x^2+x and x+1 is x^3+2x^2+x", result, multiplyOperation.doOperationOnPolynomial(p1, p2));
    }

    @Test
    public void test_doOperationOnPolynomials_oneNegativeAndOnePositivePolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("x^2+x");
        Polynomial p2 = new Polynomial("-x-1");
        Polynomial result = new Polynomial("-x^3-2x^2-x");

        assertEquals("The result of multiplying the polynomials x^2+x and -x-1 is -x^3-2x^2-x", result, multiplyOperation.doOperationOnPolynomial(p1, p2));
    }

    @Test
    public void test_doOperationOnPolynomials_negativePolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("-x^2-x");
        Polynomial p2 = new Polynomial("-x-1");
        Polynomial result = new Polynomial("x^3+2x^2+x");

        assertEquals("The result of multiplying the polynomials -x^2-x and -x-1 is x^3+2x^2+x", result, multiplyOperation.doOperationOnPolynomial(p1, p2));
    }

    @Test
    public void test_doOperationOnPolynomials_multiplyingByZero() throws BadInputException {
        Polynomial p1 = new Polynomial("x+1");
        Polynomial p2 = new Polynomial("0");
        Polynomial result = new Polynomial("0");

        assertEquals("The result of multiplying the polynomials x+1 and 0 is 0", result, multiplyOperation.doOperationOnPolynomial(p1, p2));
    }

    @Test
    public void test_doOperationOnPolynomials_multiplyingPolynomialByConstant() throws BadInputException {
        Polynomial p1 = new Polynomial("x+1");
        Polynomial p2 = new Polynomial("3");
        Polynomial result = new Polynomial("3x+3");

        assertEquals("The result of multiplying the polynomials x+1 and 3 is 3x+3", result, multiplyOperation.doOperationOnPolynomial(p1, p2));
    }

    @Test
    public void test_doOperationOnPolynomials_commutativeOperation() throws BadInputException {
        Polynomial p1 = new Polynomial("x+1");
        Polynomial p2 = new Polynomial("x^2+x");

        assertEquals("The multiplication operation is commutative.", multiplyOperation.doOperationOnPolynomial(p2, p1), multiplyOperation.doOperationOnPolynomial(p1, p2));
    }




}