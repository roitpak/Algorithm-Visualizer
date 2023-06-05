package com.example.rohit.algorithmvisualiser.Sort;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.example.rohit.algorithmvisualiser.R;

import java.util.ArrayList;

public class VisualisationOfInsertionSort extends AppCompatActivity {
//insertion sort
    int[] inputArray = {30,440,380,50,500,150,360,260,270,20};
    int[] inputArray1 = {30,440,380,50,500,150,360,260,270,20};

    int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        SortBars sortBars = new SortBars(this, inputArray);
        setContentView(sortBars);
    }

    public class SortBars extends View {

        private Paint paint;
        private int[] displayArray;
        private ArrayList<int[]> sortHistory = new ArrayList<>();
        private int[] historyItem;
        private int count = 0;

        Bitmap button_insert,background;

        int iconWidth = 0;
        int iconHeight = 0;

        int height =1;
        int width = 1;

        String showText1 = " Demo Array Created! ";

        public SortBars(VisualisationOfInsertionSort sortActivity, int[] displayArray) {
            super(sortActivity);


            button_insert = BitmapFactory.decodeResource(getResources(), R.drawable.button_start);

            iconWidth = button_insert.getWidth();
            iconHeight = button_insert.getHeight();

            background = BitmapFactory.decodeResource(getResources(),R.drawable.main);

            paint = new Paint();
            paint.setColor(Color.parseColor("#d3fff8"));
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(25);


            this.displayArray = displayArray;
            // Sort and update sort history
            int n = displayArray.length;

            int key = 0;
            for (int i = 1; i < n; i++)
            {
                key = displayArray[i];

                int j = i;
                while (j > 0 &&key< displayArray[j-1] )
                {
                    displayArray[j]=displayArray[j-1];

                    j = j-1;


                }
                displayArray[j] = key;
                sortHistory.add(displayArray.clone());



            }


        }
        @Override
        protected void onDraw(Canvas canvas) {

            update();

            canvas.drawBitmap(background,0,0,paint);

            height = canvas.getHeight();
            width = canvas.getWidth();

            int gaps = canvas.getWidth()/32;
            int xOffset = gaps*2;
            int yOffset = canvas.getHeight()-(canvas.getHeight()/3);
            int barWidth = gaps*5/4;
            int barGap = gaps;

            canvas.drawBitmap(button_insert,(2*width/5),(6*height/8),paint);

            canvas.drawText(""+showText1,2*width/20,(5*height/7)+(height/6),paint);

            if(start ==0){                                                                          //this shows unsorted
                for (int aDisplayArray : inputArray1 ) {
                    canvas.drawRect(xOffset, yOffset-aDisplayArray, xOffset + barWidth, yOffset, paint);
                    canvas.drawText(String.valueOf(aDisplayArray),xOffset,yOffset+40,paint);

                    xOffset += barGap + gaps*2;                                                     //gaps*2 kept instead

                }
            }


            if(start==1){                                                                           //this shows sorting process
                update();
                for (int aDisplayArray : historyItem) {
                    canvas.drawRect(xOffset, yOffset-aDisplayArray, xOffset + barWidth, yOffset, paint);
                    canvas.drawText(String.valueOf(aDisplayArray),xOffset,yOffset+40,paint);

                    xOffset += barGap + gaps*2;

                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
                count = count+1;
                invalidate();
            }
            if(start==2){                                                                           //this shows sorted
                for (int aDisplayArray : displayArray ) {
                    canvas.drawRect(xOffset, yOffset-aDisplayArray, xOffset + barWidth, yOffset, paint);
                    canvas.drawText(String.valueOf(aDisplayArray),xOffset,yOffset+40,paint);

                    xOffset += barGap + gaps*2;

                }
            }
        }


        protected void update(){
            try {
                historyItem = sortHistory.get(count);
            }catch(IndexOutOfBoundsException a){
                showText1 = "sorting complete!";
                invalidate();
                start = 2;
                onPause();
            }
        }
        public boolean onTouchEvent(MotionEvent event){

            int x = (int) event.getX();
            int y = (int) event.getY();



            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:


                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_UP:
                    if(x>(2*width/5) && y>(6*height/8) && x<((2*width/5)+iconWidth) && y<((6*height/8)+iconHeight)){
                        start = 1;                                                                  //make sort start on next invalidate
                        showText1 = "sorting";
                        invalidate();
                    }
                    break;
            }

            return true;
        }

    }
    @Override
    public void onBackPressed() {
        start = 0; //this solves and have small delay
        onPause();
        super.onBackPressed();
        // Do extra stuff here
    }
}
