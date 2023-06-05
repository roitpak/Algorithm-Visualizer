package com.example.rohit.algorithmvisualiser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rohit.algorithmvisualiser.List.LinkedListVisualisation;
import com.example.rohit.algorithmvisualiser.List.PriorityQueue;
import com.example.rohit.algorithmvisualiser.List.QueueVisualisation;
import com.example.rohit.algorithmvisualiser.List.StackVisualisation;
import com.example.rohit.algorithmvisualiser.Search.BFS;
import com.example.rohit.algorithmvisualiser.Search.BinarySearchTree;
import com.example.rohit.algorithmvisualiser.Search.DFS;
import com.example.rohit.algorithmvisualiser.Tree.BinaryTreeVisualisation;
import com.example.rohit.algorithmvisualiser.Sort.VisualisationOfBubbleSort;
import com.example.rohit.algorithmvisualiser.Sort.VisualisationOfInsertionSort;
import com.example.rohit.algorithmvisualiser.Sort.VisualisationOfSelectionSort;

import static android.R.attr.button;


public class AlgorithmBrief extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_algorithm_brief);
        Intent intent = getIntent();
        int position = intent.getIntExtra("pos",0);
        Button button= (Button) findViewById(R.id.go_to_viz);
        button.getBackground().setAlpha(200);
        if(position == 0){
            TextView head = (TextView)findViewById(R.id.brief_head);
            head.setText("Selection Sort");

            TextView brief = (TextView)findViewById(R.id.brief);
            brief.setText(getString(R.string.selection_sort_brief));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent toVisualisation = new Intent(AlgorithmBrief.this,VisualisationOfSelectionSort.class);
                    startActivity(toVisualisation);


                }
            });
        }
        if(position == 1){
            TextView head = (TextView)findViewById(R.id.brief_head);
            head.setText("Bubble Sort");

            TextView brief = (TextView)findViewById(R.id.brief);
            brief.setText(getString(R.string.bubble_sort_brief));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent toVisualisation = new Intent(AlgorithmBrief.this,VisualisationOfBubbleSort.class);
                    startActivity(toVisualisation);

                }
            });
        }
        if(position == 2){
            TextView head = (TextView)findViewById(R.id.brief_head);
            head.setText("InsertionSort");

            TextView brief = (TextView)findViewById(R.id.brief);
            brief.setText(getString(R.string.insetion_sort_brief));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent toVisualisation = new Intent(AlgorithmBrief.this,VisualisationOfInsertionSort.class);
                    startActivity(toVisualisation);

                }
            });
        }

        if(position == 21) {
            TextView head = (TextView) findViewById(R.id.brief_head);
            head.setText("Linked List");

            TextView brief = (TextView) findViewById(R.id.brief);
            brief.setText(getString(R.string.linked_list_brief));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent toVisualisation = new Intent(AlgorithmBrief.this, LinkedListVisualisation.class);
                    startActivity(toVisualisation);

                }
            });
        }
        if(position == 22) {
            TextView head = (TextView) findViewById(R.id.brief_head);
            head.setText("Stack");

            TextView brief = (TextView) findViewById(R.id.brief);
            brief.setText(getString(R.string.stack_brief));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent toVisualisation = new Intent(AlgorithmBrief.this, StackVisualisation.class);
                    startActivity(toVisualisation);

                }
            });
        }
        if(position == 23) {
            TextView head = (TextView) findViewById(R.id.brief_head);
            head.setText("Queue");

            TextView brief = (TextView) findViewById(R.id.brief);
            brief.setText(getString(R.string.queue_brief));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent toVisualisation = new Intent(AlgorithmBrief.this, QueueVisualisation.class);
                    startActivity(toVisualisation);

                }
            });
        }
        if(position == 24) {
            TextView head = (TextView) findViewById(R.id.brief_head);
            head.setText("Priority Queue");

            TextView brief = (TextView) findViewById(R.id.brief);
            brief.setText(getString(R.string.queue_brief));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent toVisualisation = new Intent(AlgorithmBrief.this, PriorityQueue.class);
                    startActivity(toVisualisation);

                }
            });
        }
        if(position == 31) {
            TextView head = (TextView) findViewById(R.id.brief_head);
            head.setText("Binary search Tree");

            TextView brief = (TextView) findViewById(R.id.brief);
            brief.setText(getString(R.string.binary_tree_brief));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent toVisualisation = new Intent(AlgorithmBrief.this, BinaryTreeVisualisation.class);
                    startActivity(toVisualisation);

                }
            });
        }
        if(position == 41) {
            TextView head = (TextView) findViewById(R.id.brief_head);
            head.setText("Binary Search");

            TextView brief = (TextView) findViewById(R.id.brief);
            brief.setText(getString(R.string.binary_search));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent toVisualisation = new Intent(AlgorithmBrief.this, BinarySearchTree.class);
                    startActivity(toVisualisation);

                }
            });
        }
        if(position == 42) {
            TextView head = (TextView) findViewById(R.id.brief_head);
            head.setText("Depth first search");

            TextView brief = (TextView) findViewById(R.id.brief);
            brief.setText(getString(R.string.DFS));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent toVisualisation = new Intent(AlgorithmBrief.this, DFS.class);
                    startActivity(toVisualisation);

                }
            });
        }
        if(position == 43) {
            TextView head = (TextView) findViewById(R.id.brief_head);
            head.setText("Breadth first search");

            TextView brief = (TextView) findViewById(R.id.brief);
            brief.setText(getString(R.string.BFS));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent toVisualisation = new Intent(AlgorithmBrief.this, BFS.class);
                    startActivity(toVisualisation);

                }
            });
        }

    }
}
