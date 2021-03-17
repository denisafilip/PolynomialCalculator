package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OperationDifferentiateTest {
    
    private final OperationWithOneParameter differentiateOperation = new OperationDifferentiate();

    @Test
    public void test_doOperation_positiveMonomial() throws BadInputException {
        Monomial m = new Monomial(3, 5, 'x');
        Monomial result = new Monomial(15, 4, 'x');

        assertEquals(differentiateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperation_negativeMonomial() throws BadInputException {
        Monomial m = new Monomial(-3, 5, 'x');
        Monomial result = new Monomial(-15, 4, 'x');

        assertEquals(differentiateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperation_constantPositiveMonomial() throws BadInputException {
        Monomial m = new Monomial(3, 0, 'x');
        Monomial result = new Monomial(0, 0, 'x');

        assertEquals(differentiateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperation_constantNegativeMonomial() throws BadInputException {
        Monomial m = new Monomial(-3, 0, 'x');
        Monomial result = new Monomial(0, 0, 'x');

        assertEquals(differentiateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperation_zeroMonomial() throws BadInputException {
        Monomial m = new Monomial(0, 0, 'x');
        Monomial result = new Monomial(0, 0, 'x');

        assertEquals(differentiateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperation_simpleMonomial() throws BadInputException {
        Monomial m = new Monomial(1, 1, 'x');
        Monomial result = new Monomial(1, 0, 'x');

        assertEquals(differentiateOperation.doOperation(m), result);
    }

    @Test
    public void test_doOperationOnPolynomial_positivePolynomial() throws BadInputException {
        Polynomial p = new Polynomial("2x^3+x");

        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(6, 2, 'x'));
        monomials.add(new Monomial(1, 0, 'x'));
        Polynomial result = new Polynomial(monomials);

        assertEquals(differentiateOperation.doOperationOnPolynomial(p), result);
    }

    @Test
    public void test_doOperationOnPolynomial_negativePolynomial() throws BadInputException {
        Polynomial p = new Polynomial("-x^3-2x");

        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(-3, 2, 'x'));
        monomials.add(new Monomial(-2, 0, 'x'));
        Polynomial result = new Polynomial(monomials);

        assertEquals(differentiateOperation.doOperationOnPolynomial(p), result);
    }
    

}