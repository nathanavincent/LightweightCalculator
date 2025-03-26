package simplecalculator.example.com.simplecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private double firstValue = 0;
    private double secondValue = 0;
    private String currentOperator = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = (TextView) findViewById(R.id.tvDisplay);

        // Number buttons
        findViewById(R.id.btn0).setOnClickListener(numListener);
        findViewById(R.id.btn1).setOnClickListener(numListener);
        findViewById(R.id.btn2).setOnClickListener(numListener);
        findViewById(R.id.btn3).setOnClickListener(numListener);
        findViewById(R.id.btn4).setOnClickListener(numListener);
        findViewById(R.id.btn5).setOnClickListener(numListener);
        findViewById(R.id.btn6).setOnClickListener(numListener);
        findViewById(R.id.btn7).setOnClickListener(numListener);
        findViewById(R.id.btn8).setOnClickListener(numListener);
        findViewById(R.id.btn9).setOnClickListener(numListener);

        // Operation buttons
        findViewById(R.id.btnAdd).setOnClickListener(opListener);
        findViewById(R.id.btnSubtract).setOnClickListener(opListener);
        findViewById(R.id.btnMultiply).setOnClickListener(opListener);
        findViewById(R.id.btnDivide).setOnClickListener(opListener);

        // Clear
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDisplay.setText("0");
                firstValue = 0;
                secondValue = 0;
                currentOperator = "";
                isNewOperation = true;
            }
        });

        // Equals
        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentOperator.equals("")) {
                    try {
                        secondValue = Double.parseDouble(tvDisplay.getText().toString());
                        double result = 0;
                        switch (currentOperator) {
                            case "+":
                                result = firstValue + secondValue;
                                break;
                            case "−":
                                result = firstValue - secondValue;
                                break;
                            case "×":
                                result = firstValue * secondValue;
                                break;
                            case "÷":
                                if (secondValue != 0) {
                                    result = firstValue / secondValue;
                                } else {
                                    tvDisplay.setText("Error");
                                    currentOperator = "";
                                    isNewOperation = true;
                                    return;
                                }
                                break;
                        }
                        tvDisplay.setText(String.valueOf(result));
                        currentOperator = "";
                        isNewOperation = true;
                    } catch (NumberFormatException e) {
                        tvDisplay.setText("Error");
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // Listener for numeric buttons
    private View.OnClickListener numListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            String digit = b.getText().toString();

            if (isNewOperation) {
                // If we just performed an operation or started
                tvDisplay.setText(digit);
                isNewOperation = false;
            } else {
                // Append digit to existing text
                tvDisplay.setText(tvDisplay.getText().toString() + digit);
            }
        }
    };

    // Listener for operation buttons (+, −, ×, ÷)
    private View.OnClickListener opListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                firstValue = Double.parseDouble(tvDisplay.getText().toString());
            } catch (NumberFormatException e) {
                firstValue = 0;
            }
            Button b = (Button) v;
            currentOperator = b.getText().toString();
            isNewOperation = true;
        }
    };
}
