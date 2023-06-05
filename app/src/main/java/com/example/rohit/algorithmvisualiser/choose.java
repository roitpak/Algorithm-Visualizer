package com.example.rohit.algorithmvisualiser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.rohit.algorithmvisualiser.Algorithm.TreeList;
import com.example.rohit.algorithmvisualiser.Algorithm.List_lists;
import com.example.rohit.algorithmvisualiser.Algorithm.SearchList;
import com.example.rohit.algorithmvisualiser.Algorithm.SortList;

public class choose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }
    public void goToSearchList(View view){
        Intent i = new Intent(this,SearchList.class);
        startActivity(i);
    }
    public void goToSortList(View view){
        Intent i = new Intent(this,SortList.class);
        startActivity(i);
    }
    public void goToGraphList(View view){
        Intent i = new Intent(this,TreeList.class);
        startActivity(i);
    }
    public void goToListlist(View view){
        Intent i = new Intent(this,List_lists.class);
        startActivity(i);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item:
                Intent i = new Intent(this, About.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
