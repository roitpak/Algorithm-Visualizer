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

public class List_lists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_list);

        ArrayList<Algorithms> algorithms = new ArrayList<>();

        algorithms.add(new Algorithms("Linked List","-Collection of linear data"));
        algorithms.add(new Algorithms("Stack","-Last in first out data structure"));
        algorithms.add(new Algorithms("Queue","-First in first out data structure"));
        algorithms.add(new Algorithms("Priority Queue","-First in first out with priority"));




        WordAdapter itemsAdapter = new WordAdapter(this,algorithms);

        ListView listView = (ListView) findViewById(R.id.activity_algorithm_list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int p = position;

                if(p==0){
                    Intent i = new Intent(List_lists.this,AlgorithmBrief.class);
                    i.putExtra("pos", 21);
                    startActivity(i);

                }
                if(p==1){
                    Intent i = new Intent(List_lists.this,AlgorithmBrief.class);
                    i.putExtra("pos", 22);
                    startActivity(i);

                }
                if(p==2){
                    Intent i = new Intent(List_lists.this,AlgorithmBrief.class);
                    i.putExtra("pos", 23);
                    startActivity(i);

                }
                if(p==3){
                    Intent i = new Intent(List_lists.this,AlgorithmBrief.class);
                    i.putExtra("pos", 24);
                    startActivity(i);

                }

            }
        });

    }
}
