package com.example.calculatorprojectyates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button clear, clearAll, plusMinus, divide, seven, eight, nine, plus, four, five, six, minus, one, two, three, multiply, zero, period, evaluate;

    TextView calcText;

    float operand1, operand2;

    String op1, op2, opc;

    private enum operation{
        none,
        add,
        subtract,
        multiply,
        divide
    }

    private enum stage{
        operand1,
        operator,
        operand2
    }

    operation nextOperation;
    stage currentStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clear = findViewById(R.id.button_clear);
        clearAll = findViewById(R.id.button_clearAll);
        plusMinus = findViewById(R.id.button_plusMinus);
        zero = findViewById(R.id.button_0);
        one = findViewById(R.id.button_1);
        two = findViewById(R.id.button_2);
        three = findViewById(R.id.button_3);
        four = findViewById(R.id.button_4);
        five = findViewById(R.id.button_5);
        six = findViewById(R.id.button_6);
        seven = findViewById(R.id.button_7);
        eight = findViewById(R.id.button_8);
        nine = findViewById(R.id.button_9);
        plus = findViewById(R.id.button_plus);
        minus = findViewById(R.id.button_minus);
        divide = findViewById(R.id.button_divide);
        multiply = findViewById(R.id.button_multiply);
        period = findViewById(R.id.button_period);
        evaluate = findViewById(R.id.button_equals);

        calcText = findViewById(R.id.calcText);

        System.out.println(Float.parseFloat("1."));

        operation nextOperation = operation.none;

        currentStage = stage.operand1;

        //0 by default, this is so I don't have to worry about the user pressing an operator first
        operand1 = 0.0f;
        operand2 = 0.0f;

        op1 = "";
        op2 = "";
        opc = "";

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('0'));
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('1'));
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('2'));
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('3'));
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('4'));
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('5'));
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('6'));
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('7'));
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('8'));
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('9'));
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('+'));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('-'));
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('*'));
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('/'));
            }
        });
        period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcText.setText(updateCalcText('.'));
            }
        });
        evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluate();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });
    }


    private void evaluate(){
        //if operand 2 is empty, defaults to 0
        if (op2.length() < 1){
            op2 = "0";
        }

        //return if operator not selected
        if (nextOperation == operation.none){
            return;
        }

        //attempt to parse operand 1
        try {
            operand1 = Float.parseFloat(op1);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            Log.e("Operand Exception:", "Operand 1 couldn't parse to float");
            return;
        }

        //attempt to parse operand 2
        try {
            operand2 = Float.parseFloat(op2);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            Log.e("Operand Exception:", "Operand 2 couldn't parse to float");
            return;
        }

        float answer = 0.0f;

        switch (nextOperation) {
            case add:
                answer = operand1 + operand2;
                break;
            case subtract:
                answer = operand1 - operand2;
                break;
            case multiply:
                answer = operand1 * operand2;
                break;
            case divide:
                answer = operand1 / operand2;
                break;
            default:
                break;

        }

        calcText.setText(String.valueOf(answer));

        nextOperation = operation.none;
        currentStage = stage.operand1;
        op2 = "";

        if (answer == 0.0f){
            op1 = "";
            return;
        }
        op1 = String.valueOf(answer);

    }

    private void clearAll(){
        nextOperation = operation.none;
        currentStage = stage.operand1;
        op1 = "";
        opc = "";
        op2 = "";
        calcText.setText(op1);
    }

    private void clear(){

        switch (currentStage) {
            case operand1:
                clearAll();
                break;
            case operator:
                calcText.setText(op1);
                currentStage = stage.operand1;
                opc = "";
                nextOperation = operation.none;
                break;
            case operand2:
                calcText.setText(op1+opc);
                op2 = "";
                currentStage = stage.operator;
                break;
            default:
                break;
        }
    }

    private void setOperator(char c){
        if (c == '+'){
            nextOperation = operation.add;
        }
        else if (c == '-'){
            nextOperation = operation.subtract;
        }
        else if (c == '*') {
            nextOperation = operation.multiply;
        }
        else if (c == '/') {
            nextOperation = operation.divide;
        }
        //default value, does nothing
        else {
            nextOperation = operation.none;
        }
    }

    //accepts a char as a parameter, only valid chars accepted (0-9, +, -, *, /, and .)
    private String updateCalcText(char c){

        String out = String.valueOf(calcText.getText());

        //if out is null set it to empty
        if (out == null){
            out = "";
        }

        //operator added
        if (c == '+' || c == '*' || c == '-' || c == '/') {

            //add operator to string
            if (currentStage == stage.operand1) {
                currentStage = stage.operator;
                setOperator(c);
                opc = "" + c;
                out = op1 + opc;
                return out;
            }

            //replace operator
            if (currentStage == stage.operator){
                setOperator(c);
                opc = "" + c;
                out = op1 + opc;
                return out;
            }
            evaluate();
        }

        if (currentStage == stage.operand1){

            if (c == '.'){

                //ignore decimals if there's a decimal in the string
                if (op1.length() > 1 && op1.indexOf(".") != -1){
                    return out;
                }

                //no decimal in string
                if (op1.indexOf(".") == -1){

                    //empty string
                    if (op1.length() == 0) {
                        op1 = "0.";
                        out = op1;
                        return out;
                    }
                }
            }

            //default
            op1 = op1 + c;
            out = op1;
            return out;
        }

        //stage.operator is only used for checking replacing existing operator, otherwise its treated like operand2
        if (currentStage == stage.operator || currentStage == stage.operand2){
            currentStage = stage.operand2;

            if (c == '.'){

                //ignore decimals if there's a decimal in the string
                if (op2.length() > 1 && op2.indexOf(".") != -1){
                    return out;
                }

                //no decimal in string
                if (op2.indexOf(".") == -1){

                    //empty string
                    if (op2.length() == 0) {
                        op2 = "0.";
                        out = out + op2;
                        return out;
                    }
                }
            }

            //default
            op2 = op2 + c;
            out = op1 + opc + op2;
            return out;

        }

        return out;
    }

}
