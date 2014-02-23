/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cpcalculator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Luke
 */
public class SimpleCalculationModel {
    
    // constants
    private static final Set<String> VALID_OPERATORS = new HashSet<String>(Arrays.asList(
       new String[] {"cos","acos","sin","asin","tan", "atan", "null", "reciprocal", "reverse", "factorial"}
    ));
   
    
    // state
    private Boolean isValid = false;
    
    private Boolean invalidOperator = false;
    private boolean invalidFactorial = false;
    private boolean invalidReciprocal = false;
    private boolean isError = false;
    
    // finally
    private String errorText;
    
    private double number;
    private String numberStr;
    private double result = 0.0;
    
    private final String operator;
    private final String extMode;
    private final int rounding;
    
    SimpleCalculationModel(String operator, String extMode, int round) {
        this.operator = operator;
        this.extMode = extMode;
        this.rounding = round;
    }
    
    public void setNumber(String number) {
        this.numberStr = number;
    }


    void checkInputs() {
        try {
            number = Double.valueOf(numberStr);
        } catch (NumberFormatException nfe) {
            isValid = false;
            isError = true;
            errorText = nfe.toString(); 
            return;
        } catch (Exception e) {
            isValid = false;
            isError = true;
            errorText = e.toString();
            return;
        }
        if (!VALID_OPERATORS.contains(operator)) {
            invalidOperator = true;
            isValid = false;
        } else if (operator == "factorial" && ((number > 20 || number < 0) || (number != Math.round(number)))) {
            invalidFactorial = true;
            isValid = false;
        } else if (operator == "reciprocal" && number == 0){
            invalidReciprocal = true;
            isValid = false;
        }
        else {
            isValid = true;
        } 
    }

    void calculate() {
        if (isValid) {
            try {
                switch (operator) {
                    case "null":
                        result = number;
                        break;
                    case "reciprocal":
                        result = 1 / number;
                        break;
                    case "reverse":
                        result = number * -1;
                        break;
                    case "factorial":
                        if (number == Math.round(number)) {
                            
                        }
                        number = (int) number;
                        result = 1;
                        for (int i = 1; i <= number; i++) {
                            result *= i;
                        }
                        break;
                    case "sin":
                        switch (extMode) {
                            case "degrees":
                                result = Math.sin(Math.toRadians(number));
                                break;
                            case "radians":
                                result = Math.sin(number);
                                break;
                        }
                        break;
                    case "asin":
                        switch (extMode) {
                            case "degrees":
                                result = Math.toDegrees(Math.asin(number));
                                break;
                            case "radians":
                                result = Math.asin(number);
                                break;
                        }
                        break;
                    case "cos":
                        switch (extMode) {
                            case "degrees":
                                result = Math.cos(Math.toRadians(number));
                                break;
                            case "radians":
                                result = Math.cos(number);
                                break;
                        }
                        break;
                    case "acos":
                        switch (extMode) {
                            case "degrees":
                                result = Math.toDegrees(Math.acos(number));
                                break;
                            case "radians":
                                result = Math.acos(number);
                                break;
                        }
                        break;
                    case "tan":
                        switch (extMode) {
                            case "degrees":
                                result = Math.tan(Math.toRadians(number));
                                break;
                            case "radians":
                                result = Math.tan(number);
                                break;
                        }
                        break;
                    case "atan":
                        switch (extMode) {
                            case "degrees":
                                result = Math.toDegrees(Math.atan(number));
                                break;
                            case "radians":
                                result = Math.atan(number);
                                break;
                        }
                        break;
                }
            } catch (NumberFormatException nfe) {
                isValid = false;
                isError = true;
                errorText = nfe.toString();
                
            } catch (Exception e) {
                isValid = false;
                isError = true;
                errorText = e.toString();
                
            }
        } else {
            result = 0.0;
        }
    }
    
    @Override
    public String toString() {
        if (isValid) {
           return String.format("%." + rounding + "f", result);
        } else {;
            if (invalidOperator) {
                return "Error: Invalid Operator";
            } else if (invalidFactorial) {
                return "Error: Must be a positive integer between 0 and 20";
            } else if (invalidReciprocal) {
                return "Error: Inverse of zero is infinity";
            } else if (isError) {
                return "Error:  " + errorText;
            } else {
                return "Error: Unknown";
            }
        }
    }
    
}
