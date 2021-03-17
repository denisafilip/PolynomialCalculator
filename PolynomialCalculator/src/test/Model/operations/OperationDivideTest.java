package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OperationDivideTest {

    private final OperationDivide divideOperation = new OperationDivide();

    @Test
    public void test_divideMonomials_positiveMonomialsWithSameExponent() throws BadInputException {
        Monomial m1 = new Monomial(2, 5, 'x');
        Monomial m2 = new Monomial(1, 5, 'x');
        Monomial result = new Monomial(2, 0, 'x');
        Monomial computedResult = divideOperation.divideMonomials(m1, m2);

        assertEquals("The result of dividing the monomials 2x^5 and x^5 is 2", result, computedResult);
    }

    @Test
    public void test_divideMonomials_positiveMonomialsWithDifferentExponent() throws BadInputException {
        Monomial m1 = new Monomial(2, 7, 'x');
        Monomial m2 = new Monomial(1, 5, 'x');
        Monomial result = new Monomial(2, 2, 'x');
        Monomial computedResult = divideOperation.divideMonomials(m1, m2);

        assertEquals("The result of dividing the monomials 2x^7 and x^5 is 2x^2", result, computedResult);
    }

    @Test
    public void test_divideMonomials_negativeMonomialsWithSameExponent() throws BadInputException {
        Monomial m1 = new Monomial(-2, 5, 'x');
        Monomial m2 = new Monomial(-1, 5, 'x');
        Monomial result = new Monomial(2, 0, 'x');
        Monomial computedResult = divideOperation.divideMonomials(m1, m2);

        assertEquals("The result of dividing the monomials -2x^5 and -x^5 is 2", result, computedResult);
    }

    @Test
    public void test_divideMonomials_negativeMonomialsWithDifferentExponent() throws BadInputException {
        Monomial m1 = new Monomial(-2, 7, 'x');
        Monomial m2 = new Monomial(-1, 5, 'x');
        Monomial result = new Monomial(2, 2, 'x');
        Monomial computedResult = divideOperation.divideMonomials(m1, m2);

        assertEquals("The result of dividing the monomials -2x^7 and -x^5 is 2x^2", result, computedResult);
    }

    @Test
    public void test_divideMonomials_sameExponentAndDifferentSign() throws BadInputException {
        Monomial m1 = new Monomial(-2, 5, 'x');
        Monomial m2 = new Monomial(1, 5, 'x');
        Monomial result = new Monomial(-2, 0, 'x');
        Monomial computedResult = divideOperation.divideMonomials(m1, m2);

        assertEquals("The result of dividing the monomials -2x^5 and x^5 is -2", result, computedResult);
    }

    @Test
    public void test_divideMonomials_differentExponentAndDifferentSign() throws BadInputException {
        Monomial m1 = new Monomial(-2, 3, 'x');
        Monomial m2 = new Monomial(2, 2, 'x');
        Monomial result = new Monomial(-1, 1, 'x');
        Monomial computedResult = divideOperation.divideMonomials(m1, m2);

        assertEquals("The result of dividing the monomials -2x^3 and 2x^2 is -x", result, computedResult);
    }

    @Test
    public void test_divideMonomials_constantMonomials() throws BadInputException {
        Monomial m1 = new Monomial(4, 0, 'x');
        Monomial m2 = new Monomial(5, 0, 'x');
        Monomial result = new Monomial(0.8, 0, 'x');
        Monomial computedResult = divideOperation.divideMonomials(m1, m2);

        assertEquals("The result of dividing the monomials 4 and 5 is 0.8", result, computedResult);
    }

    @Test
    public void test_divideMonomials_denominatorWithGreaterExponentThanNumerator() throws BadInputException {
        Monomial m1 = new Monomial(4, 4, 'x');
        Monomial m2 = new Monomial(5, 6, 'x');
        Monomial computedResult = divideOperation.divideMonomials(m1, m2);

        assertNull("The result of dividing the monomials 4x^4 and 5x^6 is null", computedResult);
    }

    @Test(expected = ArithmeticException.class)
    public void test_divideMonomials_divisionByZero() throws BadInputException {
        Monomial m1 = new Monomial(4, 4, 'x');
        Monomial m2 = new Monomial(0, 0, 'x');
        divideOperation.divideMonomials(m1, m2);
    }

    @Test
    public void test_divideMonomials_zeroNumerator() throws BadInputException {
        Monomial m1 = new Monomial(0, 0, 'x');
        Monomial m2 = new Monomial(1, 1, 'x');
        Monomial result = new Monomial(0, 0, 'x');
        Monomial computedResult = divideOperation.divideMonomials(m1, m2);

        assertEquals("The result of dividing the monomials 0 and x is 0", result, computedResult);

    }

    @Test
    public void test_dividePolynomials_positivePolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("x^2+x");
        Polynomial p2 = new Polynomial("x+1");

        List<Polynomial> result = new ArrayList<>();
        result.add(new Polynomial("x"));
        result.add(new Polynomial("0"));

        assertEquals("The result of dividing the polynomials x^2+x and x+1 has the quotient x and remainder 0.", result, divideOperation.dividePolynomials(p1, p2));
    }

    @Test
    public void test_dividePolynomials_oneNegativeAndOnePositivePolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("x^2+x");
        Polynomial p2 = new Polynomial("-x-1");

        List<Polynomial> result = new ArrayList<>();
        result.add(new Polynomial("-x"));
        result.add(new Polynomial("0"));

        assertEquals("The result of dividing the polynomials x^2+x and -x-1 has the quotient -x and remainder 0.", result, divideOperation.dividePolynomials(p1, p2));
    }

    @Test
    public void test_dividePolynomials_negativePolynomials() throws BadInputException {
        Polynomial p1 = new Polynomial("-x^2-x");
        Polynomial p2 = new Polynomial("-x-1");

        List<Polynomial> result = new ArrayList<>();
        result.add(new Polynomial("x"));
        result.add(new Polynomial("0"));

        assertEquals("The result of dividing the polynomials -x^2-x and -x-1 has the quotient x and remainder 0.", result, divideOperation.dividePolynomials(p1, p2));
    }

    @Test(expected = ArithmeticException.class)
    public void test_dividePolynomials_dividingByZero() throws BadInputException {
        Polynomial p1 = new Polynomial("x+1");
        Polynomial p2 = new Polynomial("0");

        divideOperation.dividePolynomials(p1, p2);
    }

    @Test
    public void test_dividePolynomials_dividingPolynomialByConstant() throws BadInputException {
        Polynomial p1 = new Polynomial("6x+6");
        Polynomial p2 = new Polynomial("3");

        List<Polynomial> result = new ArrayList<>();
        result.add(new Polynomial("2x+2"));
        result.add(new Polynomial("0"));

        assertEquals("The result of dividing the polynomials 6x+6 and 3 has the quotient 2x+2 and remainder 0.", result, divideOperation.dividePolynomials(p1, p2));
    }

    @Test
    public void test_dividePolynomials_nonZeroRemainder() throws BadInputException {
        Polynomial p1 = new Polynomial("3x^2+6x+9");
        Polynomial p2 = new Polynomial("3x+3");

        List<Polynomial> result = new ArrayList<>();
        result.add(new Polynomial("x+1"));
        result.add(new Polynomial("6"));

        assertEquals("The result of dividing the polynomials 3x^2+6x+9 and 3x+3 has the quotient x+1 and remainder 6.", result, divideOperation.dividePolynomials(p1, p2));
    }
}