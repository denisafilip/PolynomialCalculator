package Model.exceptions;

public class BadInputException extends Exception{

    public BadInputException(String monomialString) {
        super("The monomial " + monomialString + " you have entered is wrong! Please enter the polynomial again.");
    }

}
