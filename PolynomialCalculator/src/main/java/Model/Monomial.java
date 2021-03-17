package Model;

import java.text.DecimalFormat;
import java.util.Objects;

import lombok.*;
import Model.exceptions.BadInputException;

@Getter
@Setter
@NoArgsConstructor
public class Monomial {
    private double coefficient;
    private Integer exponent;
    private char variable; //the unknown of the monomial - e.g. x

    public Monomial(double coefficient, int exponent) throws BadInputException {
        this.coefficient = coefficient;
        if (exponent >= 0) {
            this.exponent = exponent;
        } else {
            throw new BadInputException("The exponent of the monomial MUST be positive.");
        }
    }

    public Monomial(int coefficient, int exponent, char variable) throws BadInputException {
        this.coefficient = coefficient;
        if (Character.isAlphabetic(variable) && exponent >= 0) {
            this.variable = variable;
            this.exponent = exponent;
        } else {
            throw new BadInputException("The variable of the polynomial MUST be a letter and the exponent MUST be positive.");
        }
    }

    public Monomial(double coefficient, int exponent, char variable) throws BadInputException {
        this.coefficient = coefficient;
        if (Character.isAlphabetic(variable) && exponent >= 0) {
            this.variable = variable;
            this.exponent = exponent;
        } else {
            throw new BadInputException("The variable of the polynomial MUST be a letter and the exponent MUST be positive.");
        }
    }

    public int getIntCoefficient() {
        return (int) coefficient;
    }

    public int decrementExponent() {
        if (exponent > 0) {
            return --exponent;
        }
        return 0;
    }

    public int incrementExponent() {
        return ++exponent;
    }

    public boolean isZero() {
        return this.coefficient == 0 && this.exponent == 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        DecimalFormat truncatedCoeff = new DecimalFormat("#.##");
        if (coefficient == 0) {
            return "";
        }
        if(coefficient == -1.0) {
            result.append("-");
        } else if (coefficient == 1.0) {
            result.append("+");
        } else if (coefficient > 0){
            result.append("+").append(truncatedCoeff.format(coefficient));
        } else {
            result.append(truncatedCoeff.format(coefficient));
        }
        if (exponent > 0) {
            result.append(variable);
        } else if (coefficient == 1 || coefficient == -1) {
            result.append(1);
        }
        if (exponent > 1) {
            result.append("^").append(exponent);
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monomial)) return false;
        Monomial monomial = (Monomial) o;
        return Double.compare(monomial.getCoefficient(), getCoefficient()) == 0 &&
                getVariable() == monomial.getVariable() &&
                getExponent().equals(monomial.getExponent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoefficient(), getExponent(), getVariable());
    }
}

