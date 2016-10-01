package com.example.gateway.testvalidationandregex;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText testField;

    String userName;

    // just one way to use validation code, not really a regex but it is using Pattern* and Matcher*
    InputFilter userNameFilter= new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                String checkMe = String.valueOf(source.charAt(i));

                Pattern pattern = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789_]*");
                Matcher matcher = pattern.matcher(checkMe);
                boolean valid = matcher.matches();
                if(!valid){
                    Log.d("", "invalid");
                    return "";
                }
            }
            return null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVariables();
    }

    private void setVariables() {
        testField = (EditText) findViewById(R.id.editText);
        // validates field to see if its empty when the user is done with it
        testField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if( testField.getText().toString().length() == 0 ){
                        testField.setError( "First name is required!" );
                    }else if(testField.getText().toString().length() < 4){
                        testField.setError(" Must be longer than 3 characters");
                    }

                }
            }
        });
        // pretty sweet because it prevents the user from even being able to enter anything but what i stated in the pattern object, so saves processing time to validate
        testField.setFilters(new InputFilter[]{userNameFilter});
    }
}
