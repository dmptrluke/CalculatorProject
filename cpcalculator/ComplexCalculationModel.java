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
public class ComplexCalculationModel {
    
    // constants
    private static final Set<String> VALID_OPERATORS = new HashSet<String>(Arrays.asList(
       new String[] {"add", "neg", "multiply", "divide", "exponent"}
    ));
    
    // state
    private Boolean isValid = false;
    
    private Boolean invalidOperator = false;
    private Boolean isDBZ = false;
    private boolean isError = false;
    
    // finally
    private String errorText;
    
    private String secondNumberStr;
    private String firstNumberStr;

    private double firstNumber;
    private double secondNumber;
    private double result = 0.0;
    
    private final String operator;
    private final int rounding;

    
    
    public ComplexCalculationModel(String operator, int rounding) {
        this.operator = operator;
        this.rounding = rounding;
    }
    
    public ComplexCalculationModel(String operator) {
        this.operator = operator;
        this.rounding = 2;
    }
    
    public void setFirstNumber(String number) {
        this.firstNumberStr = number;
    }
    
    public void setSecondNumber(String number) {
        this.secondNumberStr = number;
    }

    void checkInputs() {
        try {
            firstNumber = Double.valueOf(firstNumberStr);
            secondNumber = Double.valueOf(secondNumberStr);
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
        }
        else if ("divide".equals(operator) && secondNumber == 0.0) {
            isDBZ = true;
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
                    case "add":
                        result = firstNumber + secondNumber;
                        break;
                    case "neg":
                        result = firstNumber - secondNumber;
                        break;
                    case "multiply":
                        result = firstNumber * secondNumber;
                        break;
                    case "divide":
                        result = firstNumber / secondNumber;
                        break;
                    case "exponent":
                        result = Math.pow(firstNumber, secondNumber);
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
        } else {
            if (isDBZ) {
                return "Error: Divide by Zero";
            } else if (invalidOperator) {
                return "Error: Invalid Operator";
            } else if (isError) {
                return "Error:  " + errorText;
            } else {
                return "Error: Unknown";
            }
        }
    }
    
}
