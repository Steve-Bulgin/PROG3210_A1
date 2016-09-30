/* FileName: TempConverterActivity.java
 * Purpose: Logic for temp converter
 * Revision History
 * 		Steven Bulgin, 	2016.09.19: Created
 *      Steven Bulgin, 2016.09.28: Got farenheit to celcius working on change
 *      Steven Bulgin, 2016.09.28: Checks for overflows work
 *      Steven Bulgin, 2016.09.30: Tried OnFocusChange Listener, no go :-(
 */

/****** Bugs ******/

// Check for focus doesn't work
// Negative values crash app

package tempconverter.bulgin.com.tempconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class TempConverterActivity extends AppCompatActivity {

    //Variables
    private EditText txtFarenheit;
    private EditText txtCelcius;
    private float farenheit = 0f;
    private float celsius = 0f;
    private boolean focusChk = false; // t = farenheit, f = celcius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);
        txtFarenheit = (EditText) findViewById(R.id.txtFarenheit);
        txtCelcius = (EditText) findViewById(R.id.txtCelcius);
        txtCelcius.requestFocus();

        txtFarenheit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                focusChk = true;
                return false;
            }
        });

        txtCelcius.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                focusChk = false;
                return false;
            }
        });

        //Create text watcher to check if text input changes
        if (focusChk == true) {
            txtFarenheit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                //When input in txtFarenheit changes this is fired to calculate celcius
                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length()>0) {
                        farenheit = Float.parseFloat(s.toString());
                        celsius = (farenheit - 32) * 0.5556f;

                        //Check overflows
                        if (celsius < Float.MAX_VALUE && celsius != Float.NEGATIVE_INFINITY) {
                            txtCelcius.setText(String.format("%.2f", celsius));
                        }
                        else if (celsius > Float.MAX_VALUE) {
                            txtCelcius.setText("Value is too high");
                        }
                        else if (celsius == Float.NEGATIVE_INFINITY) {
                            txtCelcius.setText("Value is too low");
                        }
                    }
                    else {
                        txtCelcius.setText("Enter a value");
                    }
                }
            });
        }
        else if (focusChk == false) {

            txtCelcius.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0) {
                        celsius = Float.parseFloat(s.toString());
                        farenheit = (celsius * 1.8f) + 32f;

                        //Check overflows
                        if (farenheit < Float.MAX_VALUE && farenheit != Float.NEGATIVE_INFINITY) {
                            txtFarenheit.setText(String.format("%.2f", farenheit));
                        }
                        else if (farenheit > Float.MAX_VALUE) {
                            txtFarenheit.setText("Value is too high");
                        }
                        else if (farenheit == Float.NEGATIVE_INFINITY) {
                            txtFarenheit.setText("Value is too low");
                        }
                    }
                    else {
                        txtFarenheit.setText("Enter a value");
                    }
                }
            });
        }
    }




}
