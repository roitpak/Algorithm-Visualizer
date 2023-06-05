package com.example.rohit.algorithmvisualiser.Tree;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;




import com.example.rohit.algorithmvisualiser.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class BinaryTreeVisualisation extends AppCompatActivity {


    public int xToDraw;                                 //points to draw to canvas in runtime
    public int yTodraw;
    List<BTNode> list = new ArrayList<>();              //list that holds all the nodes when print
    int toExitLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BSTVisualisation BST = new BSTVisualisation(this);
        setContentView(BST);


    }
    public class BSTVisualisation extends View {                                  //class to visualise BSTree

        Paint paint;
        Paint paintOutline;
        Paint paintShowText;
        BTNode treeNode;
        Paint paintNode;



        Bitmap button_insert;

        Bitmap background;

        int iconWidth;
        int iconHeight;

        int height;
        int width;

        Binarytree b;

        Canvas mCanvas;


        String showText = "50 Added to the root of the Tree";
        List<Integer> toCheck = new ArrayList<>();

        public BSTVisualisation(Context context) {

            super(context);

            button_insert = BitmapFactory.decodeResource(getResources(), R.drawable.button_insert);
            background = BitmapFactory.decodeResource(getResources(),R.drawable.main);

            iconWidth = button_insert.getWidth();
            iconHeight = button_insert.getHeight();

            paint = new Paint();
            paint.setColor(Color.parseColor("#d3fff8"));
            paint.setStyle(Paint.Style.FILL);

            paintOutline = new Paint();
            paintOutline.setColor(Color.parseColor("#d3fff8"));
            paintOutline.setStyle(Paint.Style.STROKE);
            paintOutline.setStrokeWidth(10);

            paintShowText = new Paint();
            paintShowText.setTextSize(25);
            paintShowText.setFakeBoldText(true);
            paintShowText.setColor(Color.parseColor("#d3fff8"));

            paintNode = new Paint();
            paintNode.setTextSize(25);
            paintNode.setFakeBoldText(true);


            b = new Binarytree(50);             //tree created first and root is treeNode
            treeNode = b.root;
            toCheck.add(50);



        }

        @Override
        protected void onDraw(Canvas canvas) {


            super.onDraw(canvas);
            mCanvas = canvas;



            canvas.drawBitmap(background,0,0,paint);


            height = canvas.getHeight();
            width = canvas.getWidth();

            xToDraw = width/2;
            yTodraw = 2*height/30;



            canvas.drawText(""+showText,2*width/20,(2*height/3)+(height/6),paintShowText);

            canvas.drawBitmap(button_insert,(2*width/5),(2*height/3),paint);


            for (int i = 0;i<list.size();i++){
                BTNode tempNode = list.get(i);
                int pos = tempNode.getPosition();
                toDraw(tempNode.getData(),pos);
            }
            toExitLoop = 0;

        }
        public void toDraw(int data,int pos){                            //funciton to draw node as per position
            if(pos==0){
                mCanvas.drawCircle(8*width/17,height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),8*width/17-13,height/12+8,paintNode);          //8*width/17,height/12

            }else if(pos == 1){
                mCanvas.drawLine(8*width/17-20,height/12+20,4*width/17,3*height/12,paintOutline);
                mCanvas.drawCircle(4*width/17,3*height/12,30,paint);

                mCanvas.drawText(String.valueOf(data),4*width/17-13,3*height/12+8,paintNode);
            }
            else if(pos == 2){
                mCanvas.drawLine(8*width/17+20,height/12+20,12*width/17,3*height/12,paintOutline);
                mCanvas.drawCircle(12*width/17,3*height/12,30,paint);

                mCanvas.drawText(String.valueOf(data),12*width/17-13,3*height/12+8,paintNode);
            }
            else if(pos == 3){
                mCanvas.drawLine(4*width/17-20,3*height/12+20,2*width/17,5*height/12,paintOutline);
                mCanvas.drawCircle(2*width/17,5*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),2*width/17-13,5*height/12+8,paintNode);
            }
            else if(pos == 4){
                mCanvas.drawLine(4*width/17+20,3*height/12+20,6*width/17,5*height/12,paintOutline);
                mCanvas.drawCircle(6*width/17,5*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),6*width/17-13,5*height/12+8,paintNode);
            }
            else if(pos == 5){
                mCanvas.drawLine(12*width/17-20,3*height/12+20,10*width/17,5*height/12,paintOutline);
                mCanvas.drawCircle(10*width/17,5*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),10*width/17-13,5*height/12+8,paintNode);
            }
            else if(pos == 6){
                mCanvas.drawLine(12*width/17+20,3*height/12+20,14*width/17,5*height/12,paintOutline);
                mCanvas.drawCircle(14*width/17,5*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),14*width/17-13,5*height/12+8,paintNode);
            }
            else if(pos == 7){
                mCanvas.drawLine(2*width/17-20,5*height/12+20,width/17,7*height/12,paintOutline);
                mCanvas.drawCircle(width/17,7*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 8){
                mCanvas.drawLine(2*width/17+20,5*height/12+20,3*width/17,7*height/12,paintOutline);
                mCanvas.drawCircle(3*width/17,7*height/12,30,paint);

                mCanvas.drawText(String.valueOf(data),3*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 9){
                mCanvas.drawLine(6*width/17-20,5*height/12+20,5*width/17,7*height/12,paintOutline);
                mCanvas.drawCircle(5*width/17,7*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),5*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 10){
                mCanvas.drawLine(6*width/17+20,5*height/12+20,7*width/17,7*height/12,paintOutline);
                mCanvas.drawCircle(7*width/17,7*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),7*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 11){
                mCanvas.drawLine(10*width/17-20,5*height/12+20,9*width/17,7*height/12,paintOutline);
                mCanvas.drawCircle(9*width/17,7*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),9*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 12){
                mCanvas.drawLine(10*width/17+20,5*height/12+20,11*width/17,7*height/12,paintOutline);
                mCanvas.drawCircle(11*width/17,7*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),11*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 13){
                mCanvas.drawLine(14*width/17-20,5*height/12+20,13*width/17,7*height/12,paintOutline);
                mCanvas.drawCircle(13*width/17,7*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),13*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 14){
                mCanvas.drawLine(14*width/17+20,5*height/12+20,15*width/17,7*height/12,paintOutline);
                mCanvas.drawCircle(15*width/17,7*height/12,30,paint);
                mCanvas.drawText(String.valueOf(data),15*width/17-13,7*height/12+8,paintNode);
            }


        }


        @Override
        public boolean onTouchEvent(MotionEvent event){                         //touch listener

            int xPoint = (int) event.getX();
            int  yPoint = (int) event.getY();



            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    if(xPoint>(2*width/5)&&yPoint>(2*height/3)&&xPoint<((2*width/5)+iconWidth)&&yPoint<((2*height/3)+iconHeight)){

                        Random randomnum = new Random();
                        int range = 99 - 10 + 1;
                        int randomNum =  randomnum.nextInt(range) + 10;
                        for(int i = 0;i < toCheck.size();i++){

                            if(randomNum==toCheck.get(i)){
                                showText = "Obtained random number :"  +randomNum+ " is present in tree";

                                invalidate();
                                toExitLoop = 1;

                            }

                        }
                        if(toExitLoop==1){
                            break;
                        }

                        BTNode tempNode = new BTNode(randomNum);                               //random value added to tempNode
                        b.add(b.root,tempNode);                                              //b tree will add tempNode
                        //new node added to list

                        if(tempNode.getPosition()>14){                                  //to check wether new node is beyond 14
                            showText = "Random number :" +randomNum + " cannot be added there";

                            invalidate();
                            break;
                        }

                        toCheck.add(randomNum);
                        list.add(tempNode);
                        showText = "Random number :" + randomNum + " added to the tree as node";
                        invalidate();
                        toExitLoop = 0;

                    }

                    break;
            }

            return true;

        }

    }
    class BTNode                                //Node class

    {
        int data;
        BTNode left, right;
        int position;


        /* Constructor */
        public BTNode()

        {
            left = null;
            right = null;
            data = 0;
            position = 0;
        }

        /* Constructor */
        public BTNode(int n)

        {
            left = null;
            right = null;
            data = n;
            position = 0;
        }
        public BTNode(int n , int p){
            left = null;
            right = null;
            data = n;
            position = p;
        }
        /* Function to set left node */
        public void setPosition(int n ){position = n;  }
        public int getPosition(){ return  position;       }

        public void setLeft(BTNode n) {
            left = n;
        }

        /* Function to set right node */
        public void setRight(BTNode n) {
            right = n;
        }

        /* Function to get left node */
        public BTNode getLeft() {
            return left;
        }

        /* Function to get right node */
        public BTNode getRight()

        {
            return right;
        }

        /* Function to set data to node */
        public void setData(int d) {
            data = d;
        }

        /* Function to get data from node */
        public int getData() {
            return data;
        }
    }

    public class Binarytree {                               //tree to hold values of all nodes and add nodes
        private BTNode root;



        public Binarytree(int data) {
            root = new BTNode(data);

            root.setPosition(0);
            list.add(root);

        }

        public void add(BTNode current, BTNode child) {    //alwyas currnet node should be root and it will then check to childs


            if (child.getData() > current.getData()) {

                if (current.getRight() == null) {

                    int n = current.getPosition()*2 + 2;
                    child.setPosition(n);
                    current.setRight(child);

                } else {

                    int n = current.getPosition()*2 + 2;
                    child.setPosition(n);
                    add(current.getRight(), child);
                }
            } else if (child.getData() < current.getData()) {
                if (current.getLeft() == null) {

                    int n = current.getPosition()*2 + 1;
                    child.setPosition(n);
                    current.setLeft(child);


                } else {

                    int n = current.getPosition()*2 + 1;
                    child.setPosition(n);
                    add(current.getLeft(), child);

                }
            }
        }
    }
}
