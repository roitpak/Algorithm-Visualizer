package com.example.rohit.algorithmvisualiser.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import java.util.LinkedList;

import com.example.rohit.algorithmvisualiser.PopUp;
import com.example.rohit.algorithmvisualiser.R;



public class LinkedListVisualisation extends AppCompatActivity {

    Canvas canvas;
    int index;
    public String showText = "Demo Linked List Created";

    public LinkedList<Integer> linkedlist;


    @Override
    protected void onPause() {
        super.onPause();
        index =1;

    }

    @Override
    protected void onResume() {
        super.onResume();



        if(index == -1){
            return;
        }
        SharedPreferences backPressed = PreferenceManager.getDefaultSharedPreferences(this);
        String check = backPressed.getString("backPressed","Not Available");
        Log.v("MyActivity","checking back pressed value " +check);
        if(check == "1"){
            return;
        }
//        now acquiring values if saved in database
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String position = sharedPref.getString("position", "Not Available");
        int a= Integer.parseInt(position);

        SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);
        String type = sharedPref2.getString("type", "Not Available");
        int b= Integer.parseInt(type);

        SharedPreferences sharedPref3 = PreferenceManager.getDefaultSharedPreferences(this);
        String value = sharedPref3.getString("value", "Not Available");
        int c= Integer.parseInt(value);

//as per value acquired , the value is processed and canvas is updated again

        if(b==1){
            if(a>=linkedlist.size()+1){
                showText = "Please select maximum index of : "+ linkedlist.size() +" to add";
                Toast.makeText(this, "index out of range", Toast.LENGTH_SHORT).show();
                return;
            }

            showText = "value " +value+ " added to the position " + a;
            linkedlist.add(a,c);
            canvas.invalidate();
        }
        if(b==2){
            if(a>=linkedlist.size()){
                showText = "Please select maximum index of : "+ (linkedlist.size()-1) + " to delete" ;
                Toast.makeText(this, "index out of range", Toast.LENGTH_SHORT).show();
                return;
            }
            showText = "Removed item " + linkedlist.get(a) + " at the index " + a;
            linkedlist.remove(a);

        }else {
            return;
        }

//after value added canvas is updated
        canvas.invalidate();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        canvas = new Canvas(this);
        setContentView(canvas);

//        shared preferences created first because if not on resume will not get any thing and error

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("position", "5");
        editor.commit();

        SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putString("type", "5");
        editor2.commit();

        SharedPreferences sharedPref3 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor3 = sharedPref3.edit();
        editor2.putString("value", "5");
        editor2.commit();

        index = -1;


    }
    public class Canvas extends View {

        Paint paint,paintLine,paintCirlce,paintShowText;

        Bitmap button_insert;
        Bitmap button_delete;
        Bitmap background;


        int height =1;
        int width = 1;

        int iconWidth = 0;
        int iconHeight = 0;




        int xa = 60, ya;
        int incrementx;

        public Canvas(Context context) {
            super(context);

            button_insert = BitmapFactory.decodeResource(getResources(), R.drawable.button_insert);
            button_delete = BitmapFactory.decodeResource(getResources(), R.drawable.button_delete);
            background = BitmapFactory.decodeResource(getResources(),R.drawable.main);

            iconWidth = button_insert.getWidth();
            iconHeight = button_delete.getHeight();

            linkedlist = new LinkedList<>();
            linkedlist.add(10);
            linkedlist.add(50);
            linkedlist.add(15);
            linkedlist.add(30);

            paint = new Paint();
            paint.setTextSize(20);
            paint.setFakeBoldText(true);


            paintLine = new Paint();
            paintLine.setColor(Color.parseColor("#d3fff8"));
            paintLine.setStyle(Paint.Style.STROKE);
            paintLine.setStrokeWidth(10);

            paintCirlce = new Paint();
            paintCirlce.setColor(Color.parseColor("#7d8e8b"));
            paintCirlce.setStyle(Paint.Style.FILL);

            paintShowText = new Paint();
            paintShowText.setTextSize(25);
            paintShowText.setFakeBoldText(true);
            paintShowText.setColor(Color.parseColor("#d3fff8"));

        }


        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            super.onDraw(canvas);



            height = canvas.getHeight();
            width = canvas.getWidth();


            ya=4*height/14;
            incrementx = 0;

            canvas.drawBitmap(background,0,0,paint);
            canvas.drawBitmap(button_insert,(2*width/12),(2*height/3),paint);
            canvas.drawBitmap(button_delete,(7*width/12),(2*height/3),paint);

            canvas.drawText(""+showText,2*width/20,(2*height/3)+(height/6),paintShowText);

            int n = linkedlist.size();

            for(int i = 0;i<n;i++){
                if(i!=n-1){                                             //becuase last element doesnot need line
                    canvas.drawLine(xa+incrementx,ya,xa+incrementx+(width/7),ya,paintLine);
                    canvas.drawCircle(xa+incrementx,ya,30,paintCirlce);
                    String item = String.valueOf(linkedlist.get(i));
                    canvas.drawText(item,xa+incrementx-11,ya+7,paint);
                    incrementx = incrementx + width/7;

                }
                else{


                    canvas.drawCircle(xa+incrementx,ya,30,paintCirlce);
                    String item = String.valueOf(linkedlist.get(n-1));
                    canvas.drawText(item,xa+incrementx-11,ya+7,paint);

                }
            }


        }
        @Override
        public boolean onTouchEvent(MotionEvent event){

            int x = (int) event.getX();
            int y = (int) event.getY();



            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:


                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_UP:

                    if(x>(2*width/12) && y>(2*height/3) && x<((2*width/12)+iconWidth) && y<((2*height/3)+iconHeight)){
                        if(linkedlist.size()==7){
                            showText = "Can you see any room for more items?";
                            Toast.makeText(LinkedListVisualisation.this, "No room!", Toast.LENGTH_SHORT).show();

                            invalidate();

                            break;
                        }
                        Intent i = new Intent(getContext(),PopUp.class);
                        i.putExtra("insert",1);
                        getContext().startActivity(i);

                    }
                    if(x>(7*width/12) && y>(2*height/3) && x<((7*width/12)+iconWidth) && y<((2*height/3)+iconHeight)){
                        if(linkedlist.size()==0){
                            showText = "Where is item to remove? Logic?";
                            Toast.makeText(LinkedListVisualisation.this, "Empty", Toast.LENGTH_SHORT).show();
//
                            invalidate();
                            break;
                        }

                        Intent i = new Intent(getContext(),PopUp.class);
                        i.putExtra("insert",2);
                        getContext().startActivity(i);

                        break;

                    }


                    break;
            }

            return true;
        }

    }
}
