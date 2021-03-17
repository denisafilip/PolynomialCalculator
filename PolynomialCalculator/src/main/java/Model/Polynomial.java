package Model;

import lombok.Getter;
import lombok.Setter;
import Model.comparators.MonomialComparator;
import Model.exceptions.BadInputException;
import Model.operations.OperationAdd;
import Model.operations.OperationParseString;
import Model.operations.OperationWithTwoParameters;

import java.util.*;

@Getter
@Setter
public class Polynomial {
    private int degree;
    private List<Monomial> monomials;

    public Polynomial() {
        this.monomials = new ArrayList<>();
        this.computeDegree();
    }

    public Polynomial(Monomial m) {
        this.monomials = new ArrayList<>();
        monomials.add(m);
        this.computeDegree();
    }

    public Polynomial(List<Monomial> monomials) throws BadInputException {
        this.monomials = monomials;
        this.addMonomialsWithEqualExponents(new OperationAdd());
    }

    public Polynomial(String string) throws BadInputException {
        this.monomials = new OperationParseString().parseStringToPolynomial(string);
        this.addMonomialsWithEqualExponents(new OperationAdd());
    }

    /**
     * @param comparator user defined comparator, that orders the monomials of a polynomial in descending order according to the exponent
     *                   the terms
     */
    public void sortMonomials(Comparator<Monomial> comparator) {
        this.monomials.sort(comparator);
    }

    public void addMonomialsWithEqualExponents(OperationWithTwoParameters op) throws BadInputException {
        sortMonomials(new MonomialComparator());
        Monomial m1 = new Monomial();

        ListIterator<Monomial> itr = monomials.listIterator();
        if (itr.hasNext()) m1 = itr.next();
        while(itr.hasNext()) {
            Monomial m2 = itr.next();
            while(m1.getExponent().equals(m2.getExponent())) {
                int previousIndex = monomials.indexOf(m1);
                m1 = op.doOperation(m1, m2);
                monomials.set(previousIndex, m1);

                itr.remove();
                if (itr.hasNext()) {
                    m2 = itr.next();
                } else break;
            }
            m1 = m2;
        }
        this.removeZeroElements();
        this.computeDegree();
    }

    public void computeDegree() {
        int maxDegree = 0;
        for (Monomial m : monomials) {
            if (m.getExponent() > maxDegree && m.getCoefficient() != 0) {
                maxDegree = m.getExponent();
            }
        }
        this.degree = maxDegree;
    }

    public Monomial findMonomial(List<Monomial> monomials, int exponent) {
        for (Monomial m : monomials) {
            if (m.getExponent() == exponent) {
                return m;
            }
        }
        return null;
    }

    public Polynomial deepCopyPolynomial() throws BadInputException {
        Polynomial newPolynomial = new Polynomial();
        for (Monomial m : this.monomials) {
            Monomial newMonomial = new Monomial(m.getCoefficient(), m.getExponent(), m.getVariable());
            newPolynomial.getMonomials().add(newMonomial);
        }
        newPolynomial.computeDegree();
        newPolynomial.sortMonomials(new MonomialComparator());
        return newPolynomial;
    }

    public void removeZeroElements() {
        monomials.removeIf(Monomial::isZero);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Monomial m : this.monomials) {
            result.append(m);
        }
        if (!result.toString().isEmpty()) {
            if (result.charAt(0) == '+') {
                result.deleteCharAt(0);
            }
        } else {
            result.append("0");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polynomial)) return false;
        Polynomial that = (Polynomial) o;
        return getDegree() == that.getDegree() &&
                getMonomials().equals(that.getMonomials());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDegree(), getMonomials());
    }
}
