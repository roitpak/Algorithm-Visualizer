package com.example.rohit.algorithmvisualiser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rohit.algorithmvisualiser.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class PopUp extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    int position;

    EditText positionOF;
    String valueOFposition;
    TextView txt;

    String valueOf;
    EditText value;
    int highestPriority = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Dialog);
        this.setFinishOnTouchOutside(false);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);


        Intent intent = getIntent();
        position = intent.getIntExtra("insert",0);
        Log.v(TAG, ""+position);

        txt = (TextView) findViewById(R.id.displayTextView);

        if(position == 3){
            txt.setText("Enter Value to Search");
        }

        if(position ==1){
            LinearLayout a = (LinearLayout)findViewById(R.id.goneLayout);
            a.setVisibility(View.VISIBLE);
        }
        if(position ==10){
            LinearLayout a = (LinearLayout)findViewById(R.id.goneLayout);
            a.setVisibility(View.VISIBLE);
            txt.setText("Enter priority ");
        }


//shared preferences to store some value which will be acquired on resume of previous intent on Resume
//        previous position is taken where 1 is insert and 2 is delete
    }
    public void ok(View view){

        if(position==2){
            //also for linked list pop
            SharedPreferences sharedPrefx = PreferenceManager.getDefaultSharedPreferences(this); //shared pref x used if back is pressed
            SharedPreferences.Editor editorx = sharedPrefx.edit();
            editorx.putString("backPressed", "2");
            editorx.commit();

            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            //now get Editor
            SharedPreferences.Editor editor = sharedPref.edit();
            positionOF = (EditText) findViewById(R.id.editPos);
            String sUsername = positionOF.getText().toString();
            if (sUsername.matches("")) {
                Toast.makeText(this, "Please enter position", Toast.LENGTH_SHORT).show();
                return;
            }
            valueOFposition = positionOF.getText().toString();

            //put your value
            editor.putString("position", valueOFposition);

            //commits your edits
            editor.commit();

            SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);
            //now get Editor
            SharedPreferences.Editor editor2 = sharedPref2.edit();
            Integer a = 2;

            String type = a.toString();
            //put your value
            editor2.putString("type", type);

            //commits your edits
            editor2.commit();


            finish();
        }else if(position == 1){

            SharedPreferences sharedPrefx = PreferenceManager.getDefaultSharedPreferences(this); //shared pref x used if back is pressed
            SharedPreferences.Editor editorx = sharedPrefx.edit();
            editorx.putString("backPressed", "2");
            editorx.commit();

//            this for linked list
            SharedPreferences sharedPref3 = PreferenceManager.getDefaultSharedPreferences(this);
            //now get Editor
            SharedPreferences.Editor editor3 = sharedPref3.edit();

            value = (EditText) findViewById(R.id.editValue);
            String sUsername = value.getText().toString();
            if (sUsername.matches("")) {
                Toast.makeText(this, "Please enter value", Toast.LENGTH_SHORT).show();
                return;
            }
            valueOf = value.getText().toString();
            //put your value
            editor3.putString("value", valueOf);


            //commits your edits
            editor3.commit();
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            //now get Editor
            SharedPreferences.Editor editor = sharedPref.edit();

            positionOF = (EditText) findViewById(R.id.editPos);
            String sUsername1 = positionOF.getText().toString();
            if (sUsername1.matches("")) {
                Toast.makeText(this, "Please enter position", Toast.LENGTH_SHORT).show();
                return;
            }
            valueOFposition = positionOF.getText().toString();
            //put your value
            editor.putString("position", valueOFposition);
            //commits your edits
            editor.commit();

            SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);
            //now get Editor
            SharedPreferences.Editor editor2 = sharedPref2.edit();
            Integer a = 1;

            String type = a.toString();
            //put your value
            editor2.putString("type", type);

            //commits your edits
            editor2.commit();
            finish();
        }else if (position == 3){



//this section for binary search tree
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            //now get Editor
            SharedPreferences.Editor editor = sharedPref.edit();
            positionOF = (EditText) findViewById(R.id.editPos);
            String sUsername = positionOF.getText().toString();
            if (sUsername.matches("")) {
                Toast.makeText(this, "Please enter Value to search", Toast.LENGTH_SHORT).show();
                return;
            }
            String valueTosearch = positionOF.getText().toString();
            //put your value
            editor.putString("position", valueTosearch);

            //commits your edits
            editor.commit();
            finish();


        }
        else if(position == 10){

            SharedPreferences sharedPrefx = PreferenceManager.getDefaultSharedPreferences(this); //shared pref x used if back is pressed
            SharedPreferences.Editor editorx = sharedPrefx.edit();
            editorx.putString("backPressed", "2");
            editorx.commit();

            //for priority queue insert
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            //now get Editor
            SharedPreferences.Editor editor = sharedPref.edit();
            positionOF = (EditText) findViewById(R.id.editValue);
            String sUsername = positionOF.getText().toString();
            if (sUsername.matches("")) {
                Toast.makeText(this, "Please enter value", Toast.LENGTH_SHORT).show();
                return;
            }
            valueOFposition = positionOF.getText().toString();

            //put your value
            editor.putString("value", valueOFposition);

            //commits your edits
            editor.commit();
            SharedPreferences sharedPref1 = PreferenceManager.getDefaultSharedPreferences(this);
            //now get Editor
            SharedPreferences.Editor editor1 = sharedPref1.edit();

            positionOF = (EditText) findViewById(R.id.editPos);
            String sUsername1 = positionOF.getText().toString();
            if (sUsername1.matches("")) {
                Toast.makeText(this, "Please enter priority", Toast.LENGTH_SHORT).show();
                return;
            }

            valueOFposition = positionOF.getText().toString();
            //put your value
            editor1.putString("priority", valueOFposition);
            //commits your edits
            editor1.commit();



            finish();
        }


    }
    @Override
    public void onBackPressed() {
                                                                                        //this solves and have small delay
        super.onBackPressed();
        SharedPreferences sharedPrefx = PreferenceManager.getDefaultSharedPreferences(this); //shared pref x used if back is pressed
        //now get Editor
        SharedPreferences.Editor editorx = sharedPrefx.edit();



        //put your value
        editorx.putString("backPressed", "1");

        //commits your edits
        editorx.commit();
        Log.v("MyActivity","back pressed and valye 1");
        // Do extra stuff here
    }
}
