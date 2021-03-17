package Model.operations;

import Model.Monomial;
import Model.exceptions.BadInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationParseString {

    /**
     * regex (?!$)([+-]?+(?=\w)\d*)?+\*?+((?<=\d|[+-]|\*)[a-zA-Z]?+|^[a-zA-Z])(?:(?<=[A-Za-z])\^)?+((?<=\^)\d*)?+ - splits my input into three groups: (coefficient)(variable)(exponent). Since each of these
     * 3 group can be omitted, depending on the given monomial, the beginning (?!$) is a negative look-ahead and determines the regex
     * to ignore empty strings as matches.
     *
     * @param string - the string containing the monomial that has to be parsed
     * @return the resulted monomial after parsing the string
     */
    public Monomial parseStringToMonomial(String string) throws BadInputException {
        int coeff = 1, exponent = 1;
        char variable = 'x';
        Pattern p = Pattern.compile("(?!$)([+-]?+(?=\\w)\\d*)?+\\*?+((?<=\\d|[+-]|\\*)[a-zA-Z]?+|^[a-zA-Z])(?:(?<=[A-Za-z])\\^)?+((?<=\\^)\\d*)?+");
        Matcher m = p.matcher(string);
        if (!m.find() || !m.group().equals(string)) {
            throw new BadInputException(string);
        } else {
            if (m.group(1) != null && !m.group(1).isEmpty()) {
                if (m.group(1).equals("-")) {
                    coeff = -1;
                } else if (!m.group(1).equals("+")) {
                    coeff = Integer.parseInt(m.group(1));
                }
            }
            if (m.group(2) != null && !m.group(2).isEmpty()) {
                variable = m.group(2).charAt(0);
            } else exponent = 0;
            if (m.group(3) != null && !m.group(3).isEmpty()) {
                exponent = Integer.parseInt(m.group(3));
            }
        }
        if (!m.hitEnd() || m.end() != string.length()) {
            throw new BadInputException(string);
        } else {
            return new Monomial(coeff, exponent, variable);
        }
    }

    /**
     * @param string containing polynomial, that needs to be parsed
     * @return the resulting polynomial after parsing the string parameter
     */
    public List<Monomial> parseStringToPolynomial(String string) throws BadInputException {
        List<Monomial> monomials = new ArrayList<>();
        string = string.replaceAll("\\s", "");
        String[] words = string.split("(?=[^A-Za-z0-9^*]|^[A-Za-z0-9])");
        for (String i : words) {
            monomials.add(parseStringToMonomial(i));
        }
        return monomials;
    }
}
