package Model.operations;

import Model.Monomial;
import Model.Polynomial;
import Model.exceptions.BadInputException;

import java.util.ArrayList;
import java.util.List;

public class OperationDivide {
    private final OperationWithTwoParameters opMultiply = new OperationMultiply();
    private final OperationWithTwoParameters opSubtract = new OperationSubtract();

    /**
     * @param m1 - first monomial
     * @param m2 - second monomial
     * @return the resulted monomial after the division between m1 and m2
     */
    public Monomial divideMonomials(Monomial m1, Monomial m2) throws BadInputException {
        if (m2.isZero()) {
            throw new ArithmeticException("You can't divide by 0!");
        } else if (m1.getExponent() >= m2.getExponent()) {
            int resultedPower = m1.getExponent() - m2.getExponent();
            double resultedCoefficient = m1.getCoefficient()/m2.getCoefficient();
            return new Monomial(resultedCoefficient, resultedPower, m1.getVariable());
        } else if (m1.isZero()) {
            return new Monomial(0, 0, m1.getVariable());
        }
        return null;
    }

    /**
     *      function n / d:
     *       require d ≠ 0
     *       (q, r) ← (0, n)            # At each step n = d × q + r
     *       while r ≠ 0 AND degree(r) ≥ degree(d):
     *          t ← lead(r)/lead(d)     # Divide the leading terms
     *          (q, r) ← (q + t, r - (t * d))
     *       return (q, r)

     * @param p1 - numerator polynomial
     * @param p2 - denominator polynomial
     * @return a list of polynomials - The quotient at index 0, and the rest at index 1
     */
    public List<Polynomial> dividePolynomials(Polynomial p1, Polynomial p2) throws BadInputException {
        Polynomial quotient = new Polynomial();
        Polynomial rest = p1.deepCopyPolynomial();
        Polynomial copyD = p2.deepCopyPolynomial();

        if (p2.getMonomials().isEmpty() || p2.getMonomials().get(0).getCoefficient() == 0) {
            throw new ArithmeticException("The denominator polynomial must NOT be 0.");
        }
        while(!rest.getMonomials().isEmpty() && (rest.getDegree() >= copyD.getDegree())) {
            Monomial divi = divideMonomials(rest.getMonomials().get(0), copyD.getMonomials().get(0));

            quotient.getMonomials().add(divi);
            quotient.addMonomialsWithEqualExponents(new OperationAdd());

            Polynomial term = new Polynomial(divi);

            Polynomial mul = opMultiply.doOperationOnPolynomial(term, copyD);
            rest = opSubtract.doOperationOnPolynomial(rest, mul);
        }

        List<Polynomial> result = new ArrayList<>();
        result.add(quotient);
        result.add(rest);
        return result;
    }
}
