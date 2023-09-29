package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends Activity {

    private Button btnExit;
    private EditText txtColor;
    private ConstraintLayout myScreen;
    private TextView txtSpy;
    private final String PREFNAME="myFile1";

    int originalOrientation;
    private int getOrientation() {
        Display display=((WindowManager)getApplication().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        display.getRotation();
        return display.getRotation();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT ).show();

        btnExit=findViewById(R.id.btnExit);
        txtColor =findViewById(R.id.txtMsg);
        myScreen=findViewById(R.id.myScreen);
        txtSpy=findViewById(R.id.txtSpy);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String chosenColor= s.toString().toLowerCase(Locale.US);
                setBackgroundColor(chosenColor, myScreen);
                txtSpy.setText(chosenColor);
            }
        });

    }

    private void setBackgroundColor(String chosenColor, ConstraintLayout myScreen) {
        if (chosenColor.contains("hường")) {
            myScreen.setBackgroundColor(0xfffab4f5);
        }
        if (chosenColor.contains("huệ")) {
            myScreen.setBackgroundColor(0xfff7ec68);
        }
        if (chosenColor.contains("nhân")) {
            myScreen.setBackgroundColor(0xffe3bc9a);
        }
        if (chosenColor.contains("thư")) {
            myScreen.setBackgroundColor(0xff93c5f5);
        }
        if (chosenColor.contains("nam")) {
            myScreen.setBackgroundColor(0xffdf46f3);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT ).show();
        updateMeUsingSavedStateData();
    }

    private void updateMeUsingSavedStateData() {
        SharedPreferences myPrefContainer=getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        String key="ChosenBackgroundColor", defaultValue="white";
        if (myPrefContainer!=null && myPrefContainer.contains(key)) {
            String color=myPrefContainer.getString(key,defaultValue);
            setBackgroundColor(color, myScreen);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT ).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT ).show();
        String chosenColor= txtSpy.getText().toString();
        saveStateData(chosenColor);

        if(getOrientation()!=originalOrientation) {
            
        }

    }

    private void saveStateData(String chosenColor) {
        SharedPreferences myPrefContainer=getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor myPrefEditor=myPrefContainer.edit();
        String key="ChosenBackgroundColor", value=txtSpy.getText().toString();
        myPrefEditor.putString(key,value);
        myPrefEditor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT ).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT ).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT ).show();

    }
}