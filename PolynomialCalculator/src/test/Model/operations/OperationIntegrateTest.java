package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OperationIntegrateTest {

    private final OperationWithOneParameter integrateOperation = new OperationIntegrate();

    @Test
    public void test_doOperation_positiveMonomial() throws BadInputException {
        Monomial m = new Monomial(3, 5, 'x');
        Monomial result = new Monomial(0.5, 6, 'x');

        assertEquals(integrateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperation_negativeMonomial() throws BadInputException {
        Monomial m = new Monomial(-3, 5, 'x');
        Monomial result = new Monomial(-0.5, 6, 'x');

        assertEquals(integrateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperation_constantPositiveMonomial() throws BadInputException {
        Monomial m = new Monomial(3, 0, 'x');
        Monomial result = new Monomial(3, 1, 'x');

        assertEquals(integrateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperation_constantNegativeMonomial() throws BadInputException {
        Monomial m = new Monomial(-3, 0, 'x');
        Monomial result = new Monomial(-3, 1, 'x');

        assertEquals(integrateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperation_zeroMonomial() throws BadInputException {
        Monomial m = new Monomial(0, 0, 'x');
        Monomial result = new Monomial(0, 1, 'x');

        assertEquals(integrateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperationOnPolynomial_positivePolynomial() throws BadInputException {
        Polynomial p = new Polynomial("2x^3+x");

        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(0.5, 4, 'x'));
        monomials.add(new Monomial(0.5, 2, 'x'));
        Polynomial result = new Polynomial(monomials);

        assertEquals(integrateOperation.doOperationOnPolynomial(p), result);
    }

    @Test
    public void test_doOperationOnPolynomial_negativePolynomial() throws BadInputException {
        Polynomial p = new Polynomial("-x^3-2x");

        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(-0.25, 4, 'x'));
        monomials.add(new Monomial(-1, 2, 'x'));
        Polynomial result = new Polynomial(monomials);

        assertEquals(integrateOperation.doOperationOnPolynomial(p), result);
    }

}