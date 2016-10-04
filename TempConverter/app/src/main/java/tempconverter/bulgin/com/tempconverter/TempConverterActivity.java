/* FileName: TempConverterActivity.java
 * Purpose: Logic for temp converter
 * Revision History
 * 		Steven Bulgin, 	2016.09.19: Created
 *      Steven Bulgin, 2016.09.28: Got farenheit to celcius working on change
 *      Steven Bulgin, 2016.09.28: Checks for overflows work
 *      Steven Bulgin, 2016.09.30: Tried OnFocusChange Listener, no go :-(
 *      Steven Bulgin, 2016.10.03: Used try/catch around the Float parse to solve negatives
 *      Steven Bulgin, 2016.10.04: It works! It Works! Now clean up the mess
 */



package tempconverter.bulgin.com.tempconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;


public class TempConverterActivity extends AppCompatActivity {

    //Variables
    private EditText txtFahrenheit;
    private EditText txtCelsius;
    private float fahrenheit = 0f;
    private float celsius = 0f;
    private TextWatcher celsiusListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                    try {
                        celsius = Float.parseFloat(s.toString());
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    fahrenheit = (celsius * 1.8f) + 32f;

                    //Check overflows
                    if (fahrenheit < Float.MAX_VALUE && fahrenheit != Float.NEGATIVE_INFINITY) {
                        txtFahrenheit.removeTextChangedListener(fahrenheitListener);
                        txtFahrenheit.setText(String.format("%.2f", fahrenheit));
                        txtFahrenheit.addTextChangedListener(fahrenheitListener);
                    }
                    else if (fahrenheit > Float.MAX_VALUE) {
                        txtFahrenheit.removeTextChangedListener(fahrenheitListener);
                        txtFahrenheit.setText("Value is too high");
                        txtFahrenheit.addTextChangedListener(fahrenheitListener);
                    }
                    else if (fahrenheit == Float.NEGATIVE_INFINITY) {
                        txtFahrenheit.removeTextChangedListener(fahrenheitListener);
                        txtFahrenheit.setText("Value is too low");
                        txtFahrenheit.addTextChangedListener(fahrenheitListener);
                    }
                }
                else {
                    txtFahrenheit.removeTextChangedListener(fahrenheitListener);
                    txtFahrenheit.setText("Enter a value");
                    txtFahrenheit.addTextChangedListener(fahrenheitListener);
                }
            }



    };
    private TextWatcher fahrenheitListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s.length() > 0) {
                try {
                    fahrenheit = Float.parseFloat(s.toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

                celsius = (fahrenheit - 32) * 0.5556f;

                //Check overflows
                if (celsius < Float.MAX_VALUE && celsius != Float.NEGATIVE_INFINITY) {
                    txtCelsius.removeTextChangedListener(celsiusListener);
                    txtCelsius.setText(String.format("%.2f", celsius));
                    txtCelsius.addTextChangedListener(celsiusListener);
                } else if (celsius > Float.MAX_VALUE) {
                    txtCelsius.removeTextChangedListener(celsiusListener);
                    txtCelsius.setText("Value is too high");
                    txtCelsius.addTextChangedListener(celsiusListener);
                } else if (celsius == Float.NEGATIVE_INFINITY) {
                    txtCelsius.removeTextChangedListener(celsiusListener);
                    txtCelsius.setText("Value is too low");
                    txtCelsius.addTextChangedListener(celsiusListener);
                }
            } else {
                txtCelsius.removeTextChangedListener(celsiusListener);
                txtCelsius.setText("Enter a value");
                txtCelsius.addTextChangedListener(celsiusListener);
            }
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);
        txtFahrenheit = (EditText) findViewById(R.id.txtFarenheit);
        txtCelsius = (EditText) findViewById(R.id.txtCelcius);
        txtCelsius.addTextChangedListener(celsiusListener);
        txtFahrenheit.addTextChangedListener(fahrenheitListener);

        txtFahrenheit.requestFocus();
    }
}
