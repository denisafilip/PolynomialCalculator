package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OperationParseStringTest {

    private final OperationParseString parseStringOperation = new OperationParseString();

    @Test
    public void test_stringToMonomial_plusOne() throws BadInputException {
        String testString = "1";
        Monomial m = new Monomial(1, 0, 'x');

        assertEquals("The string 12x^3 should correspond to the monomial of coefficient 1 and exponent 0.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_minusOne() throws BadInputException {
        String testString = "-1";
        Monomial m = new Monomial(-1, 0, 'x');

        assertEquals("The string -12x^3 should correspond to the monomial of coefficient -1 and exponent 0.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_positiveMonomial() throws BadInputException {
        String testString = "12x^3";
        Monomial m = new Monomial(12, 3, 'x');

        assertEquals("The string 12x^3 should correspond to the monomial of coefficient 12 and exponent 3.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_negativeMonomial() throws BadInputException {
        String testString = "-12x^3";
        Monomial m = new Monomial(-12, 3, 'x');

        assertEquals("The string -12x^3 should correspond to the monomial of coefficient -12 and exponent 3.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_positiveMonomialWithMultiplyingSymbol() throws BadInputException {
        String testString = "5*x^3";
        Monomial m = new Monomial(5, 3, 'x');

        assertEquals("The string 5*x^3 should correspond to the monomial of coefficient 5 and exponent 3.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_negativeMonomialWithMultiplyingSymbol() throws BadInputException {
        String testString = "-12*x^3";
        Monomial m = new Monomial(-12, 3, 'x');

        assertEquals("The string -12*x^3 should correspond to the monomial of coefficient -12 and exponent 3.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_positiveMonomialWithCoefficientOne() throws BadInputException {
        String testString = "x^5";
        Monomial m = new Monomial(1, 5, 'x');

        assertEquals("The string x^5 should correspond to the monomial of coefficient 1 and exponent 5.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_negativeMonomialWithCoefficientOne() throws BadInputException {
        String testString = "-x^5";
        Monomial m = new Monomial(-1, 5, 'x');

        assertEquals("The string -x^5 should correspond to the monomial of coefficient -1 and exponent 5.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_differentLetterForVariable() throws BadInputException {
        String testString = "6y";
        Monomial m = new Monomial(6, 1, 'y');

        assertEquals("The string 6y should correspond to the monomial of coefficient 6, exponent 1 and variable y", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_constantPositiveNumber() throws BadInputException {
        String testString = "12";
        Monomial m = new Monomial(12, 0, 'x');

        assertEquals("The string 12 should correspond to the monomial of coefficient 12 and exponent 0.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_constantNegativeNumber() throws BadInputException {
        String testString = "-12";
        Monomial m = new Monomial(-12, 0, 'x');

        assertEquals("The string -12 should correspond to the monomial of coefficient -12 and exponent 0.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test
    public void test_stringToMonomial_zeroMonomial() throws BadInputException {
        String testString = "0";
        Monomial m = new Monomial(0, 0, 'x');

        assertEquals("The string 0 should correspond to the monomial of coefficient 0 and exponent 0.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_constantPositiveDoubleNumber() throws BadInputException {
        String testString = "12.5";
        Monomial m = new Monomial(12.5, 0, 'x');

        assertEquals("The string 12.5 should correspond to the monomial of coefficient 12.5 and exponent 0.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_constantNegativeDoubleNumber() throws BadInputException {
        String testString = "-12.25";
        Monomial m = new Monomial(-12.25, 0, 'x');

        assertEquals("The string -12.25 should correspond to the monomial of coefficient -12.25 and exponent 0.", m, parseStringOperation.parseStringToMonomial(testString));
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_multipleVariables() throws BadInputException {
        String testString = "xxx";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_noInput() throws BadInputException {
        String testString = "";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_noVariable() throws BadInputException {
        String testString = "6^10";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_noSign() throws BadInputException {
        String testString = "x^35x";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_otherCharacters() throws BadInputException {
        String testString = "x%$5";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_onlyPlusSign() throws BadInputException {
        String testString = "+";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_onlyMinusSign() throws BadInputException {
        String testString = "-";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_noPowerSymbol() throws BadInputException {
        String testString = "x35";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_negativePower() throws BadInputException {
        String testString = "x^-35";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_noVariableOrPowerSign() throws BadInputException {
        String testString = "5*4";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToMonomial_minusAndPlusSign() throws BadInputException {
        String testString = "+-x^3";
        parseStringOperation.parseStringToMonomial(testString);
    }

    @Test
    public void test_stringToPolynomial_simplePositivePolynomial() throws BadInputException {
        String testString = "x+x^3";
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(1, 1, 'x'));
        monomials.add(new Monomial(1, 3, 'x'));
        Polynomial p = new Polynomial(monomials);

        Polynomial resultedPolynomial = new Polynomial(testString);

        assertEquals("The polynomial formed from the string x+x^3 is equal to the polynomial containing the monomials +x and +x^3", p, resultedPolynomial);
    }

    @Test
    public void test_stringToPolynomial_simpleNegativePolynomial() throws BadInputException {
        String testString = "-x-x^3";
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(-1, 1, 'x'));
        monomials.add(new Monomial(-1, 3, 'x'));
        Polynomial p = new Polynomial(monomials);

        Polynomial resultedPolynomial = new Polynomial(testString);

        assertEquals("The polynomial formed from the string -x-x^3 is equal to the polynomial containing the monomials -x and -x^3", p, resultedPolynomial);
    }

    @Test
    public void test_stringToPolynomial_simpleMixedSignsPolynomial() throws BadInputException {
        String testString = "x-x^3+x^4";
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(1, 1, 'x'));
        monomials.add(new Monomial(-1, 3, 'x'));
        monomials.add(new Monomial(1, 4, 'x'));
        Polynomial p = new Polynomial(monomials);

        Polynomial resultedPolynomial = new Polynomial(testString);

        assertEquals("The polynomial formed from the string +x-x^3+x^4 is equal to the polynomial containing the monomials x and -x^3 and x^4", p, resultedPolynomial);
    }

    @Test
    public void test_stringToPolynomial_justAMonomial() throws BadInputException {
        String testString = "5x^7";
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(5, 7, 'x'));
        Polynomial p = new Polynomial(monomials);

        Polynomial resultedPolynomial = new Polynomial(testString);

        assertEquals("The polynomial formed from the string 5x^7 is equal to the polynomial containing the monomial 5x^7", p, resultedPolynomial);
    }

    @Test
    public void test_stringToPolynomial_zeroPolynomial() throws BadInputException {
        String testString = "0";
        List<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(0, 0, 'x'));
        Polynomial p = new Polynomial(monomials);
        Polynomial resultedPolynomial = new Polynomial(testString);

        assertEquals("The polynomial formed from the string 0 is equal to the polynomial containing the monomial 0", p, resultedPolynomial);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToPolynomial_noSigns() throws BadInputException {
        String testString = "5x^75x^89x^2";
        parseStringOperation.parseStringToPolynomial(testString);
    }

    @Test(expected = BadInputException.class)
    public void test_stringToPolynomial_otherCharacters() throws BadInputException {
        String testString = "5x^7&5x^8";
        parseStringOperation.parseStringToPolynomial(testString);
    }








}