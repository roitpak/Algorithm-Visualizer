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

public class SearchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_list);

        ArrayList<Algorithms> algorithms = new ArrayList<>();

        algorithms.add(new Algorithms("Binary Search","-searching algorithm"));
        algorithms.add(new Algorithms("Depth First Search","-Depth searching algorithm"));
        algorithms.add(new Algorithms("Breadth First Search","-Breadth searching algorithm"));




        WordAdapter itemsAdapter = new WordAdapter(this,algorithms);

        ListView listView = (ListView) findViewById(R.id.activity_algorithm_list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int p = position;

                if(p==0){
                    Intent i = new Intent(SearchList.this,AlgorithmBrief.class);
                    i.putExtra("pos", 41);
                    startActivity(i);

                }
                if(p==1){
                    Intent i = new Intent(SearchList.this,AlgorithmBrief.class);
                    i.putExtra("pos", 42);
                    startActivity(i);

                }
                if(p==2){
                    Intent i = new Intent(SearchList.this,AlgorithmBrief.class);
                    i.putExtra("pos", 43);
                    startActivity(i);

                }


            }
        });


    }

}
