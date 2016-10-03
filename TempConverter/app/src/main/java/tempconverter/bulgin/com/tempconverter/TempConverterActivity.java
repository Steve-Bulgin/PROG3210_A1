/* FileName: TempConverterActivity.java
 * Purpose: Logic for temp converter
 * Revision History
 * 		Steven Bulgin, 	2016.09.19: Created
 *      Steven Bulgin, 2016.09.28: Got farenheit to celcius working on change
 *      Steven Bulgin, 2016.09.28: Checks for overflows work
 *      Steven Bulgin, 2016.09.30: Tried OnFocusChange Listener, no go :-(
 *      Steven Bulgin, 2016.10.03: Used try/catch around the Float parse to solve negatives
 *      Steven Bulgin, 2016.10.03: Closer to right.
 */

/****** Bugs ******/

// Check for focus doesn't work
// Negative values crash app

package tempconverter.bulgin.com.tempconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class TempConverterActivity extends AppCompatActivity  {

    //Variables
    private EditText txtFarenheit;
    private EditText txtCelcius;
    private float farenheit = 0f;
    private float celsius = 0f;
    //private boolean focusChk = false; // t = farenheit, f = celcius;
    private String celsiusstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);
        txtFarenheit = (EditText) findViewById(R.id.txtFarenheit);
        //txtFarenheit.setOnFocusChangeListener(FahrenheitchangeChk);
        //txtFarenheit.setOnTouchListener(TouchFarenheit);
        txtCelcius = (EditText) findViewById(R.id.txtCelcius);
        //txtCelcius.setOnFocusChangeListener(CelsiuschangeChk);
        //txtFarenheit.setOnTouchListener(TouchCelsius);

        txtFarenheit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int ActionId, KeyEvent event){

                String s = txtFarenheit.getText().toString();

                if (s.length()>0) {
                    try {
                        farenheit = Float.parseFloat(s.toString());
                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_SHORT).show();
                    }

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
                farenheit = 0f;
                s = null;
                return true;
            }
        });

        txtCelcius.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int ActionId, KeyEvent event){

                String s = txtCelcius.getText().toString();

                if (s.length() > 0) {
                    try {
                        celsius = Float.parseFloat(s.toString());
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_SHORT).show();
                    }

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

                s = null;
                celsius = 0f;
                return false;
            }
        });



        txtCelcius.requestFocus();
    }



}
