package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Model.Polynomial;
import Model.exceptions.BadInputException;
import Model.operations.*;

import java.util.List;

public class PolynomialCalculatorController {
    @FXML
    private Button btnExit;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSubtract;
    @FXML
    private Button btnMultiply;
    @FXML
    private Button btnDivide;
    @FXML
    private Button btnDiff;
    @FXML
    private Button btnIntegrate;
    @FXML
    private TextField txtFirstPoly;
    @FXML
    private TextField txtSecondPoly;
    @FXML
    private TextField txtResult1;
    @FXML
    private TextField txtResult2;
    @FXML
    private Label lblResult;
    @FXML
    private Label lblResult2;

    public void createAlert(String errorMessage) {
        Alert alert =  new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error occured");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.show();
    }

    public void add(ActionEvent actionEvent) {
        try {
            txtResult2.setVisible(false);
            lblResult2.setVisible(false);

            displayPolynomialWithTwoParameters(new OperationAdd(), lblResult, "Result of addition:", txtFirstPoly, txtSecondPoly, txtResult1);
        } catch (BadInputException e) {
            createAlert("Wrong input! Please enter the polynomials again.");
        }
    }

    public void subtract(ActionEvent actionEvent) {
       try {
           txtResult2.setVisible(false);
           lblResult2.setVisible(false);

           displayPolynomialWithTwoParameters(new OperationSubtract(), lblResult, "Result of subtraction:", txtFirstPoly, txtSecondPoly, txtResult1);
       } catch (BadInputException e) {
           createAlert("Wrong input! Please enter the polynomials again.");
       }

    }

    public void multiply(ActionEvent actionEvent) {
        try {
            txtResult2.setVisible(false);
            lblResult2.setVisible(false);
            displayPolynomialWithTwoParameters(new OperationMultiply(), lblResult, "Result of multiplication:", txtFirstPoly, txtSecondPoly, txtResult1);
        } catch (BadInputException e) {
            createAlert("Wrong input! Please enter the polynomials again.");
        }
    }

    public void divide(ActionEvent actionEvent) {
       try {
           Polynomial firstPolynomial = new Polynomial(txtFirstPoly.getText());
           Polynomial secondPolynomial = new Polynomial(txtSecondPoly.getText());
           List<Polynomial> result = new OperationDivide().dividePolynomials(firstPolynomial, secondPolynomial);

           displayLabel(lblResult, "Quotient:");
           displayLabel(lblResult2, "Remainder:");
           displayTextField(txtResult2, result.get(1).toString());
           displayTextField(txtResult1, result.get(0).toString());

       } catch (BadInputException e) {
           createAlert("Wrong input! Please enter the polynomials again.");
       } catch (ArithmeticException e) {
           createAlert("The denominator polynomial must NOT be 0. Please enter the polynomial again.");
       }
    }

    public void integrate(ActionEvent actionEvent) {
        try {
            txtResult2.setVisible(false);
            lblResult2.setVisible(false);

            displayPolynomial(new OperationIntegrate(), lblResult, "Result - 1st polynomial:", txtFirstPoly, txtResult1);
            displayPolynomial(new OperationIntegrate(), lblResult2, "Result - 2nd polynomial:", txtSecondPoly, txtResult2);
        } catch (BadInputException e) {
            createAlert("Wrong input! Please enter the polynomials again.");
        }
    }

    public void differentiate(ActionEvent actionEvent) {
        try {
            txtResult2.setVisible(false);
            lblResult2.setVisible(false);

            displayPolynomial(new OperationDifferentiate(), lblResult, "Result - 1st polynomial:", txtFirstPoly, txtResult1);
            displayPolynomial(new OperationDifferentiate(), lblResult2, "Result - 2nd polynomial:", txtSecondPoly, txtResult2);
        } catch (BadInputException e) {
            createAlert("Wrong input! Please enter the polynomials again.");
        }
    }

    public void clearTextFields() {
        txtFirstPoly.clear();
        txtSecondPoly.clear();
        txtResult1.clear();
        displayLabel(lblResult, "Result:");
        lblResult2.setVisible(false);
        txtResult2.clear();
        txtResult2.setVisible(false);
    }

    public void displayLabel(Label lbl, String text) {
        lbl.setVisible(true);
        lbl.setText(text);
    }

    public void displayTextField(TextField txt, String text) {
        txt.setVisible(true);
        txt.setText(text);
    }

    public void displayPolynomial(OperationWithOneParameter operation, Label label, String textOfLabel, TextField txtField, TextField txtFieldResult) throws BadInputException {
        if (!txtField.getText().isEmpty()) {
            Polynomial p = new Polynomial(txtField.getText());
            Polynomial result = operation.doOperationOnPolynomial(p);
            displayLabel(label, textOfLabel);
            displayTextField(txtFieldResult, result.toString());
        }
    }

    public void displayPolynomialWithTwoParameters(OperationWithTwoParameters operation, Label label, String textOfLabel, TextField txtFieldFirst, TextField txtFieldSecond, TextField txtFieldResult) throws BadInputException {
            Polynomial p1 = new Polynomial(txtFieldFirst.getText());
            Polynomial p2 = new Polynomial(txtFieldSecond.getText());
            Polynomial result = operation.doOperationOnPolynomial(p1, p2);
            displayLabel(label, textOfLabel);
            displayTextField(txtFieldResult, result.toString());
    }

    public void closeWindow() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

}
