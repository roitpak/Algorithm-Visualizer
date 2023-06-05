package com.example.rohit.algorithmvisualiser.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.rohit.algorithmvisualiser.R;

import java.util.LinkedList;
import java.util.Random;

public class QueueVisualisation extends AppCompatActivity {
    QueueView queue;
    LinkedList<Integer> s;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = new QueueView(this);
        setContentView(queue);

    }
    public class QueueView extends View {

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

        String showText = "Demo Queue.";

        public QueueView(Context context) {
            super(context);

            button_push = BitmapFactory.decodeResource(getResources(), R.drawable.button_enqueue); //dont mind the names
            button_pop = BitmapFactory.decodeResource(getResources(), R.drawable.button_dequeue);
            button_peek = BitmapFactory.decodeResource(getResources(), R.drawable.button_peek);
            background = BitmapFactory.decodeResource(getResources(),R.drawable.main);

            s = new LinkedList<>();
            s.add(21);
            s.add(23);
            s.add(10);

            iconWidth = button_push.getWidth();
            iconHeight = button_push.getHeight();

            paint = new Paint();
            paint.setColor(Color.parseColor("#d3fff8"));
            paint.setStyle(Paint.Style.FILL);

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
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            height = canvas.getHeight();
            width = canvas.getWidth();

            canvas.drawBitmap(background,0,0,paint);
            canvas.drawBitmap(button_push,(1*width/20),(2*height/3),paint);
            canvas.drawBitmap(button_pop,(8*width/20),(2*height/3),paint);
            canvas.drawBitmap(button_peek,(15*width/20),(2*height/3),paint);



            canvas.drawText(""+showText,2*width/20,(2*height/3)+(height/6),paintShowText);

            decrementY = 0;

            int n = s.size();
            for(int i = 0;i<n;i++){

                canvas.drawRect((width/2-100),8*height/15-decrementY,(width/2+100),9*height/15-decrementY,paint);
                canvas.drawRect((width/2-100),8*height/15-decrementY,(width/2+100),9*height/15-decrementY,paintOutline);

                String item = String.valueOf(s.get(i));
                canvas.drawText(item,width/2-13,17*height/30-decrementY+8,paintText);

                decrementY = decrementY + (height/15);

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

                    if(x>(2*width/20) && y>(2*height/3) && x<((2*width/20)+iconWidth) && y<((2*height/3)+iconHeight)){
                        if(s.size()==8){
                            showText = "Queue is full. Cannot push item.";
                            invalidate();
                            Toast.makeText(QueueVisualisation.this, "queue full!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        Random randomnum = new Random();
                        int range = 100 - 10 + 1;
                        int randomNum =  randomnum.nextInt(range) + 10;
                        s.add(randomNum);
                        showText = "Adding random number " + randomNum + " to the Queue.";



                        invalidate();
                    }
                    if(x>(8*width/20) && y>(2*height/3) && x<((8*width/20)+iconWidth) && y<((2*height/3)+iconHeight)){
                        if(s.size()==0){
                            showText= "Queue is empty. No item to delete";
                            invalidate();
                            Toast.makeText(QueueVisualisation.this, "Queue Empty", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        showText = "Removing first item of the queue:" + s.getLast();

                        s.removeFirst();

                        invalidate();
                    }
                    if(x>(14*width/20) && y>(2*height/3) && x<((14*width/20)+iconWidth) && y<((2*height/3)+iconHeight)){
                        if(s.size()==0){
                            showText= "Queue is empty. No item to Peek";
                            invalidate();
                            Toast.makeText(QueueVisualisation.this, "Queue Empty", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        int a = s.getFirst();
                        showText = "Peeking the Queue and queue first  is "+a+".";


                        invalidate();
                    }


                    break;
            }

            return true;

        }

    }
}