package Model;

import Model.exceptions.BadInputException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class PolynomialTest {

    @Test
    public void test_computeDegree_degreeZero() throws BadInputException {
        Polynomial p = new Polynomial("0");

        assertEquals("The polynomial 0 is of degree 0.", p.getDegree(), 0);
    }

    @Test
    public void test_computeDegree_normalDegree() throws BadInputException {
        Polynomial p = new Polynomial("x^6+x^5");
        assertEquals("The polynomial x^6+x^5 is of degree 6.", p.getDegree(), 6);
    }

    @Test
    public void test_computeDegree_degreeOne() throws BadInputException {
        Polynomial p = new Polynomial("x");
        assertEquals("The polynomial x is of degree 1.", p.getDegree(), 1);
    }

    @Test
    public void test_sortMonomials_orderedMonomials() throws BadInputException {
        Polynomial p = new Polynomial("x^5+5x^3+2x^2+x"); //the sort function is called implicitly in the constructor

        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(1, 5, 'x'));
        monomials.add(new Monomial(5, 3, 'x'));
        monomials.add(new Monomial(2, 2, 'x'));
        monomials.add(new Monomial(1, 1, 'x'));
        assertEquals("The polynomial x^5+5x^3+2x^2+x is composed of a list of monomials sorted in descending order by exponent.", p.getMonomials(), monomials);
    }

    @Test
    public void test_sortMonomials_unorderedMonomials() throws BadInputException {
        Polynomial p = new Polynomial("x+x^5+2x^2+5x^3"); //the sort function is called implicitly in the constructor

        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(1, 5, 'x'));
        monomials.add(new Monomial(5, 3, 'x'));
        monomials.add(new Monomial(2, 2, 'x'));
        monomials.add(new Monomial(1, 1, 'x'));
        assertEquals("The polynomial x+x^5+2x^2+5x^3 is composed of a list of monomials sorted in descending order by exponent.", p.getMonomials(), monomials);
    }

    @Test
    public void test_addMonomialsWithEqualExponents_noEqualExponents() throws BadInputException {
        Polynomial p = new Polynomial("x+x^5+2x^2+5x^3");

        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(1, 5, 'x'));
        monomials.add(new Monomial(5, 3, 'x'));
        monomials.add(new Monomial(2, 2, 'x'));
        monomials.add(new Monomial(1, 1, 'x'));
        assertEquals("The polynomial x+x^5+2x^2+5x^3 is composed of a list of monomials sorted in descending order by exponent.", p.getMonomials(), monomials);

    }

    @Test
    public void test_addMonomialsWithEqualExponents_equalExponentsWithTheSameSign() throws BadInputException {
        Polynomial p = new Polynomial("x+x+x+x^2");
        Polynomial correctPolynomial = new Polynomial("3x+x^2");

        assertEquals("The polynomial x+x+x+x^2 is equal to 3x+x^2.", p, correctPolynomial);
    }

    @Test
    public void test_addMonomialsWithEqualExponents_equalExponentsWithDifferentSign() throws BadInputException {
        Polynomial p = new Polynomial("x-x+x^2");
        Polynomial correctPolynomial = new Polynomial("x^2");

        assertEquals("The polynomial x-x+x^2 equals to x^2.", p, correctPolynomial);
    }

    @Test
    public void test_findMonomial_existentExponent() throws BadInputException {
        Polynomial p = new Polynomial("x^3+3x");
        Monomial m = p.findMonomial(p.getMonomials(), 1);
        Monomial searchedMonomial = new Monomial(3, 1, 'x');

        assertEquals("The monomial of exponent 1 from the polynomial x^3+3x is equal to 3x.", m, searchedMonomial);
    }

    @Test
    public void test_findMonomial_nonexistentExponent() throws BadInputException {
        Polynomial p = new Polynomial("x^3+3x");
        Monomial m = p.findMonomial(p.getMonomials(), 2);

        assertNull("There is no monomial of exponent 2 in the polynomial x^3+3x, so the returned value should be null.", m);
    }

    @Test
    public void test_deepCopyPolynomial_checkIfEqual() throws BadInputException {
        Polynomial p1 = new Polynomial("x^3+3x");
        Polynomial p2 = p1.deepCopyPolynomial();

        assertEquals("The polynomials p1 and p2 are equal.", p1, p2);
    }

    @Test
    public void test_removeZeroElements_existentZeroElements() throws BadInputException {
        Polynomial p1 = new Polynomial("x^3+3x+0+0");
        p1.removeZeroElements();
        Polynomial p2 = new Polynomial("x^3+3x");

        assertEquals("The polynomials p1 and p2 are equal.", p1, p2);
    }

    @Test
    public void test_removeZeroElements_nonexistentZeroElements() throws BadInputException {
        Polynomial p1 = new Polynomial("x^3+3x");
        p1.removeZeroElements();
        Polynomial p2 = new Polynomial("x^3+3x");

        assertEquals("The polynomials p1 and p2 are equal.", p1, p2);
    }

    @Test
    public void test_toString_positivePolynomial() throws BadInputException {
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(5, 3, 'x'));
        monomials.add(new Monomial(2, 2, 'x'));
        monomials.add(new Monomial(1, 1, 'x'));
        Polynomial p1 = new Polynomial(monomials);

        assertEquals(p1.toString(), "5x^3+2x^2+x");
    }

    @Test
    public void test_toString_zeroPolynomial() throws BadInputException {
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(0, 0, 'x'));
        Polynomial p1 = new Polynomial(monomials);

        assertEquals(p1.toString(), "0");
    }

    @Test
    public void test_toString_negativePolynomial() throws BadInputException {
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(-2, 3, 'x'));
        monomials.add(new Monomial(-4, 2, 'x'));
        monomials.add(new Monomial(-1, 1, 'x'));
        Polynomial p1 = new Polynomial(monomials);

        assertEquals(p1.toString(), "-2x^3-4x^2-x");
    }

    @Test
    public void test_toString_constantPolynomial() throws BadInputException {
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(5, 0, 'x'));
        Polynomial p1 = new Polynomial(monomials);

        assertEquals(p1.toString(), "5");
    }

    @Test
    public void test_toString_onlyVariablePolynomial() throws BadInputException {
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(1, 5, 'x'));
        Polynomial p1 = new Polynomial(monomials);

        assertEquals(p1.toString(), "x^5");
    }



}