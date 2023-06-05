package com.example.rohit.algorithmvisualiser.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.rohit.algorithmvisualiser.PopUp;
import com.example.rohit.algorithmvisualiser.R;

import java.util.LinkedList;
import java.util.Random;

import static android.R.attr.value;
import static android.R.attr.x;
import static android.R.attr.y;

public class PriorityQueue extends AppCompatActivity {

    Canvas canvas;
    int index;
    public String showText = "Value 21 added with priority 4";

    android.graphics.Canvas mCanvas; //used to draw out of onDraw
    LinkedList<PriorityQ> s; //this is the list that holds the queue
    int highestPriority;

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
        String position = sharedPref.getString("value", "Not Available");
        int a= Integer.parseInt(position);
        SharedPreferences sharedPref1 = PreferenceManager.getDefaultSharedPreferences(this);
        String position1 = sharedPref1.getString("priority", "Not Available");
        int b= Integer.parseInt(position1);

//as per value acquired , the value is processed and canvas is updated again
        PriorityQ temp = new PriorityQ(a,b);
        s.add(temp);

        showText = "Value " +a+ " added with priority " + b;


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
        editor.putString("value", "5");
        editor.commit();

        SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putString("priority", "5");
        editor2.commit();



        index = -1;


    }
    public class Canvas extends View {

        Paint paint;
        Paint paintOutline;
        Paint paintText;
        Paint paintShowText;

        Bitmap button_push;
        Bitmap button_pop;
        Bitmap button_peek;

        Bitmap background;


        int iconWidth;
        int iconHeight;

        int height;
        int width;

        int decrementY;



        public Canvas(Context context) {
            super(context);

            button_push = BitmapFactory.decodeResource(getResources(), R.drawable.button_enqueue); //dont mind the variable names
            button_pop = BitmapFactory.decodeResource(getResources(), R.drawable.button_dequeue);
            button_peek = BitmapFactory.decodeResource(getResources(), R.drawable.button_peek);
            background = BitmapFactory.decodeResource(getResources(),R.drawable.main);

            s = new LinkedList<>();
            PriorityQ a = new PriorityQ(21,4);
            s.add(a);


            iconWidth = button_push.getWidth();
            iconHeight = button_push.getHeight();

            paint = new Paint();
            paint.setColor(Color.parseColor("#d3fff8"));
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(25);

            paintOutline = new Paint();
            paintOutline.setColor(Color.BLACK);
            paintOutline.setStyle(Paint.Style.STROKE);
            paintOutline.setStrokeWidth(10);

            paintText = new Paint();
            paintText.setTextSize(30);
            paintText.setFakeBoldText(true);

            paintShowText = new Paint();
            paintShowText.setTextSize(25);
            paintShowText.setFakeBoldText(true);
            paintShowText.setColor(Color.parseColor("#d3fff8"));

        }


        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            super.onDraw(canvas);
            mCanvas = canvas;


            height = canvas.getHeight();
            width = canvas.getWidth();

            canvas.drawBitmap(background,0,0,paint);
            canvas.drawBitmap(button_push,(1*width/20),(2*height/3),paint);
            canvas.drawBitmap(button_pop,(8*width/20),(2*height/3),paint);
            canvas.drawBitmap(button_peek,(15*width/20),(2*height/3),paint);



            canvas.drawText(""+showText,2*width/20,(2*height/3)+(height/6),paintShowText);

            decrementY = 0;
            highestPriority = 0; //to check and store in drawing
            int n = s.size();
            if(s.isEmpty()){
                return;
            }
            for(int i = 0;i<n;i++){

                int priority  = s.get(i).getP();
                if(highestPriority<priority){
                    highestPriority = priority;
                }
                paintQueuePriority(decrementY,priority);

                canvas.drawText(String.valueOf(priority),width/2+110,17*height/30-decrementY+8,paint);
                canvas.drawRect((width/2-100),8*height/15-decrementY,(width/2+100),9*height/15-decrementY,paintOutline);

                String item = String.valueOf(s.get(i).getV());
                canvas.drawText(item,width/2-13,17*height/30-decrementY+8,paintText);

                decrementY = decrementY + (height/15);

            }


        }
        public void paintQueuePriority(int dec, int p){
            mCanvas.drawRect((width/2-100),8*height/15-dec,(width/2+100),9*height/15-dec,paint);
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


                    if(x>(2*width/20) && y>(2*height/3) && x<((2*width/20)+iconWidth) && y<((2*height/3)+iconHeight)){
                        if(s.size()==8){
                            showText = "Queue is full. Cannot push item.";
                            invalidate();
                            Toast.makeText(PriorityQueue.this, "queue full!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        Intent i = new Intent(getContext(),PopUp.class);
                        i.putExtra("insert",10);
                        getContext().startActivity(i);



                        invalidate();
                    }
                    if(x>(8*width/20) && y>(2*height/3) && x<((8*width/20)+iconWidth) && y<((2*height/3)+iconHeight)){
                        if(s.size()==0){
                            showText= "Queue is empty. No item to delete";

                            Toast.makeText(PriorityQueue.this, "Queue Empty", Toast.LENGTH_SHORT).show();
                            break;
                        }


                        int i= 0;
                        while(i<s.size()){
                            int p = s.get(i).getP();
                            int v = s.get(i).getV();
                            if(p==highestPriority){
                                s.remove(i);
                                showText = "Removing item: " + v + " with priority:" +p;
                                break;
                            }
                            i++;
                        }


                        invalidate();
                    }
                    if(x>(14*width/20) && y>(2*height/3) && x<((14*width/20)+iconWidth) && y<((2*height/3)+iconHeight)){
                        if(s.size()==0){
                            showText= "Queue is empty. No item to Peek";
                            invalidate();
                            Toast.makeText(PriorityQueue.this, "Queue Empty", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        int a = s.getFirst().getV();
                        int b = s.getFirst().getP();
                        showText = "Queue first  is "+a+" and priority " + b;


                        invalidate();
                    }


                    break;
            }

            return true;
        }

    }
    public  class PriorityQ{
        int value;
        int priority;
        PriorityQ(int v){
            value = v;
            priority = 0;
        }
        PriorityQ(int v , int p){
            value = v;
            priority = p;
        }
        public int getP(){
            return priority;
        }
        public int getV(){
            return value;
        }
    }
}
