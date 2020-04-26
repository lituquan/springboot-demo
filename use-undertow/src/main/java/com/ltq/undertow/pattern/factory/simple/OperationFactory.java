package com.ltq.undertow.pattern.factory.simple;

public class OperationFactory {
    public static Operation getOperation(String operator) {
        Operation result = null;
        switch (operator) {
        case "*":
            result = new Mul();
            break;
        case "+":
            result = new Add();
            break;
        case "-":
            result = new Sub();
            break;
        case "/":
            result = new Div();
            break;
        // case "^":
        // result = new Pow();
        // break;
        }
        return result;
    }
}