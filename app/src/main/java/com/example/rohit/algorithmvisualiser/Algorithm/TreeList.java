package com.example.rohit.algorithmvisualiser.Algorithm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rohit.algorithmvisualiser.AlgorithmBrief;
import com.example.rohit.algorithmvisualiser.Algorithms;
import com.example.rohit.algorithmvisualiser.R;
import com.example.rohit.algorithmvisualiser.WordAdapter;

import java.util.ArrayList;

public class TreeList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_list);

        ArrayList<Algorithms> algorithms = new ArrayList<>();
//        algorithms.add(new Algorithms("Selection Sort","-sorting algorithm"));
//        algorithms.add(new Algorithms("Quick sortt","-sorting algorithm"));
//        algorithms.add(new Algorithms("Heap","-sorting algorithm"));
//        algorithms.add(new Algorithms("Bubble sort","-sorting algorithm"));
//        algorithms.add(new Algorithms("Insertion sort","-sorting algorithm"));
//        algorithms.add(new Algorithms("Mergesort","-sorting algorithm"));
        algorithms.add(new Algorithms("Binary Tree","-Tree with two nodes"));





        WordAdapter itemsAdapter = new WordAdapter(this,algorithms);

        ListView listView = (ListView) findViewById(R.id.activity_algorithm_list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int p = position;

                if(p==0){
                    Intent i = new Intent(TreeList.this,AlgorithmBrief.class);
                    i.putExtra("pos", 31);
                    startActivity(i);

                }


            }
        });

    }
}
