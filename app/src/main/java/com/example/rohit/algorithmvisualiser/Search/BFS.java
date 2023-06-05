package com.example.rohit.algorithmvisualiser.Search;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BFS extends AppCompatActivity {
    Canvas mCanvas;                                     //to access canvas out of on draw
    BSTVisualisation abc;                               //to invalidate on resume after pop up
    public int xToDraw;                                 //points to draw to canvas in runtime
    public int yTodraw;
    List<BTNode> list = new ArrayList<>();              //list that holds all the nodes when print
    List<BTNode> listToDisplay = new ArrayList<>();   //List to hold one node at a time to print
    List<BTNode> listToDisplayTemp = new ArrayList<>(); //List to hold all the nodes to highlight

    Integer[] presentOR = {50,30,70,15,40,58,80,3,16,35,48,54,63,78,90};

    BTNode treeNode;

    int valueTosearch;
    int a;          //used to check in resume for not to error
    int count = 0;  //for toDraw method to update drawing list

    @Override
    protected void onResume() {
        super.onResume();

        listToDisplayTemp.clear();          //clearing list before searching again after popup
        listToDisplay.clear();
        count = 0;
        if(a==0){           //this is to make sure programme doesnot crashes on create a value changes after draw
            return;
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String position = sharedPref.getString("position", "Not Available");
        valueTosearch= Integer.parseInt(position);
        if(Arrays.asList(presentOR).contains(valueTosearch)){
            BFSearch(valueTosearch);
            Toast.makeText(this, "Click Next Step to Start!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "The value is not present in the tree", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BSTVisualisation BST = new BSTVisualisation(this);
        setContentView(BST);




        //this is here so that on Resume the shared preference is not accessed
//        50,30,70,15,40,58,80,3,16,35,48,54,63,78,90


    }
    public class BSTVisualisation extends View {                                  //class to visualise BSTree

        Paint paint;
        Paint paintOutline;
        Paint paintShowText;

        Paint paintNode;

        Paint paintHightlight;
        Paint paintOutlineHighlight;



        Bitmap button_search;
        Bitmap button_next;
        Bitmap background;

        int iconWidth;
        int iconHeight;

        int height;
        int width;


        Binarytree b;




        String showText = "Demo Tree Created for search";




        public BSTVisualisation(Context context) {

            super(context);

            button_search = BitmapFactory.decodeResource(getResources(), R.drawable.button_search);
            background = BitmapFactory.decodeResource(getResources(),R.drawable.main);
            button_next = BitmapFactory.decodeResource(getResources(), R.drawable.button_next);

            iconWidth = button_search.getWidth();
            iconHeight = button_search.getHeight();

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

            paintHightlight = new Paint();
            paintHightlight.setColor(Color.parseColor("#c56bf9"));
            paintHightlight.setStyle(Paint.Style.FILL);

            paintOutlineHighlight = new Paint();
            paintOutlineHighlight.setColor(Color.parseColor("#c56bf9"));
            paintOutlineHighlight.setStyle(Paint.Style.STROKE);
            paintOutlineHighlight.setStrokeWidth(10);


            b = new Binarytree(50);             //tree created first and root is treeNode to display
            treeNode = b.root;
            b.add(b.root,30);
            b.add(b.root,70);
            b.add(b.root,15);
            b.add(b.root,40);
            b.add(b.root,58);
            b.add(b.root,80);
            b.add(b.root,3);
            b.add(b.root,16);
            b.add(b.root,35);
            b.add(b.root,48);
            b.add(b.root,54);
            b.add(b.root,63);
            b.add(b.root,78);
            b.add(b.root,90);



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

            canvas.drawBitmap(button_search,(width/7),(2*height/3),paint);
            canvas.drawBitmap(button_next,(4*width/8),(2*height/3),paint);


            for (int i = 0;i<list.size();i++){
                BTNode tempNode = list.get(i);
                int pos = tempNode.getPosition();
                toDraw(tempNode.getData(),pos);
            }
            if(listToDisplay != null){
                for(int i = 0;i<listToDisplay.size();i++){

                    BTNode tempNode = listToDisplay.get(i);
                    int pos = tempNode.getPosition();
                    toDrawHighlight(tempNode.getData(),pos);
                }
            }

            a = 1; //this will make on resume to not to skip code

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
                    if(xPoint>(width/7)&&yPoint>(2*height/3)&&xPoint<((width/7)+iconWidth)&&yPoint<((2*height/3)+iconHeight)){

                        Intent i = new Intent(getContext(),PopUp.class);
                        i.putExtra("insert",3);
//                        3 for binary search tree to know in the pop up
                        getContext().startActivity(i);
                    }
                    if(xPoint>(4*width/8)&&yPoint>(2*height/3)&&xPoint<((4*width/8)+button_next.getWidth())&&yPoint<((2*height/3)+button_next.getHeight())){
                        if(listToDisplayTemp.isEmpty()){
                            showText = "No data to search";
                            Toast.makeText(BFS.this, "No Data to find", Toast.LENGTH_SHORT).show();
                            invalidate();
                            break;
                        }

                        if(count==listToDisplayTemp.size()){

                            showText = "No more steps";
                            Toast.makeText(BFS.this, "No more Steps", Toast.LENGTH_SHORT).show();
                            invalidate();
                            break;
                        }


                        listToDisplay.add(listToDisplayTemp.get(count));
                        if(listToDisplayTemp.get(count).getData()==valueTosearch){

                            Toast.makeText(BFS.this, "Found", Toast.LENGTH_SHORT).show();

                        }
                        showText = "Going through node :" + listToDisplayTemp.get(count).getData();
                        count++;
                        invalidate();
                    }

                    break;
            }

            return true;

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
        public void toDrawHighlight(int data,int pos){                            //funciton to draw node as per position
            if(pos==0){
                mCanvas.drawCircle(8*width/17,height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),8*width/17-13,height/12+8,paintNode);          //8*width/17,height/12

            }else if(pos == 1){
                mCanvas.drawLine(8*width/17-20,height/12+20,4*width/17,3*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(4*width/17,3*height/12,30,paintHightlight);

                mCanvas.drawText(String.valueOf(data),4*width/17-13,3*height/12+8,paintNode);
            }
            else if(pos == 2){
                mCanvas.drawLine(8*width/17+20,height/12+20,12*width/17,3*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(12*width/17,3*height/12,30,paintHightlight);

                mCanvas.drawText(String.valueOf(data),12*width/17-13,3*height/12+8,paintNode);
            }
            else if(pos == 3){
                mCanvas.drawLine(4*width/17-20,3*height/12+20,2*width/17,5*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(2*width/17,5*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),2*width/17-13,5*height/12+8,paintNode);
            }
            else if(pos == 4){
                mCanvas.drawLine(4*width/17+20,3*height/12+20,6*width/17,5*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(6*width/17,5*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),6*width/17-13,5*height/12+8,paintNode);
            }
            else if(pos == 5){
                mCanvas.drawLine(12*width/17-20,3*height/12+20,10*width/17,5*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(10*width/17,5*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),10*width/17-13,5*height/12+8,paintNode);
            }
            else if(pos == 6){
                mCanvas.drawLine(12*width/17+20,3*height/12+20,14*width/17,5*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(14*width/17,5*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),14*width/17-13,5*height/12+8,paintNode);
            }
            else if(pos == 7){
                mCanvas.drawLine(2*width/17-20,5*height/12+20,width/17,7*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(width/17,7*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 8){
                mCanvas.drawLine(2*width/17+20,5*height/12+20,3*width/17,7*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(3*width/17,7*height/12,30,paintHightlight);

                mCanvas.drawText(String.valueOf(data),3*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 9){
                mCanvas.drawLine(6*width/17-20,5*height/12+20,5*width/17,7*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(5*width/17,7*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),5*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 10){
                mCanvas.drawLine(6*width/17+20,5*height/12+20,7*width/17,7*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(7*width/17,7*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),7*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 11){
                mCanvas.drawLine(10*width/17-20,5*height/12+20,9*width/17,7*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(9*width/17,7*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),9*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 12){
                mCanvas.drawLine(10*width/17+20,5*height/12+20,11*width/17,7*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(11*width/17,7*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),11*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 13){
                mCanvas.drawLine(14*width/17-20,5*height/12+20,13*width/17,7*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(13*width/17,7*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),13*width/17-13,7*height/12+8,paintNode);
            }
            else if(pos == 14){
                mCanvas.drawLine(14*width/17+20,5*height/12+20,15*width/17,7*height/12,paintOutlineHighlight);
                mCanvas.drawCircle(15*width/17,7*height/12,30,paintHightlight);
                mCanvas.drawText(String.valueOf(data),15*width/17-13,7*height/12+8,paintNode);
            }


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


//        this fucntion to add and compare node as per the value node has


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
        public void add(BTNode current, int value) {    //alwyas currnet node should be root and it will then check to childs
            BTNode child = new BTNode(value);
//        this fucntion to add and compare node as per the value node has


            if (child.getData() > current.getData()) {

                if (current.getRight() == null) {

                    int n = current.getPosition()*2 + 2;
                    child.setPosition(n);
                    current.setRight(child);
                    list.add(child);

                } else {

                    add(current.getRight(), value);
                }
            } else if (child.getData() < current.getData()) {
                if (current.getLeft() == null) {

                    int n = current.getPosition()*2 + 1;
                    child.setPosition(n);
                    current.setLeft(child);
                    list.add(child);


                } else {

                    add(current.getLeft(), value);

                }
            }
        }

    }
    public void BFSearch(int value){
        for(int i=0; i<list.size();i++){
            listToDisplayTemp.add(list.get(i));
            if(value==list.get(i).getData()){
               return;
            }
        }

    }
}
