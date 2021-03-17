package Model;

import Model.exceptions.BadInputException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonomialTest {

    @Test
    public void test_decrementExponent_positiveExponent() throws BadInputException {
        Monomial m = new Monomial(1, 3);

        assertEquals(m.decrementExponent(), 2);
    }

    @Test
    public void test_incrementExponent_positiveExponent() throws BadInputException {
        Monomial m = new Monomial(1, 3);

        assertEquals(m.incrementExponent(), 4);
    }

    @Test
    public void test_isZero_nonZeroMonomial() throws BadInputException {
        Monomial m = new Monomial(1, 3);

        assertFalse("The monomial x^3 should is not equal to 0.", m.isZero());
    }

    @Test
    public void test_isZero_zeroMonomial() throws BadInputException {
        Monomial m = new Monomial(0, 0);

        assertTrue("The monomial 0 should is equal to 0.", m.isZero());
    }

    @Test
    public void test_getIntCoefficient_integerCoefficient() throws BadInputException {
        Monomial m = new Monomial(-2, 0, 'x');

        assertEquals(m.getIntCoefficient(), -2);
    }

    @Test
    public void test_getIntCoefficient_doubleCoefficient() throws BadInputException {
        Monomial m = new Monomial(2.75, 0, 'x');

        assertEquals(m.getIntCoefficient(), 2);
    }

    @Test
    public void test_toString_zeroMonomial() throws BadInputException {
        Monomial m = new Monomial(0, 0, 'x');

        assertEquals(m.toString(), "");
    }

    @Test
    public void test_toString_positiveMonomial() throws BadInputException {
        Monomial m = new Monomial(4, 5, 'x');

        assertEquals(m.toString(), "+4x^5");
    }

    @Test
    public void test_toString_negativeMonomial() throws BadInputException {
        Monomial m = new Monomial(-4, 5, 'x');

        assertEquals(m.toString(), "-4x^5");
    }

    @Test
    public void test_toString_positiveMonomialWithCoefficientOne() throws BadInputException {
        Monomial m = new Monomial(1, 5, 'x');

        assertEquals(m.toString(), "+x^5");
    }

    @Test
    public void test_toString_negativeMonomialWithCoefficientOne() throws BadInputException {
        Monomial m = new Monomial(-1, 5, 'x');

        assertEquals(m.toString(), "-x^5");
    }

    @Test
    public void test_toString_positiveMonomialWithDoubleCoefficient() throws BadInputException {
        Monomial m = new Monomial(0.45, 5, 'x');

        assertEquals(m.toString(), "+0.45x^5");
    }

    @Test
    public void test_toString_negativeMonomialWithDoubleCoefficient() throws BadInputException {
        Monomial m = new Monomial(-0.45, 5, 'x');

        assertEquals(m.toString(), "-0.45x^5");
    }

    @Test
    public void test_toString_positiveConstantMonomial() throws BadInputException {
        Monomial m = new Monomial(2, 0, 'x');

        assertEquals(m.toString(), "+2");
    }

    @Test
    public void test_toString_negativeConstantMonomial() throws BadInputException {
        Monomial m = new Monomial(-2, 0, 'x');

        assertEquals(m.toString(), "-2");
    }

}