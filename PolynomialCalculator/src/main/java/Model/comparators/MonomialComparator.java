package Model.comparators;

import Model.Monomial;

import java.util.Comparator;

public class MonomialComparator implements Comparator<Monomial> {
    @Override
    public int compare(Monomial m1, Monomial m2) {
        return m2.getExponent().compareTo(m1.getExponent());
    }
}
