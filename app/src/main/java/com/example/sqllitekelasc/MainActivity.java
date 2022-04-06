package com.example.sqllitekelasc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqllitekelasc.adapter.TemanAdapter;
import com.example.sqllitekelasc.database.DBController;
import com.example.sqllitekelasc.model.Teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private FloatingActionButton fab;
    private TemanAdapter adapter;
    private ArrayList<Teman> temanArrayList;
    DBController kendaliDB=new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.recyclerV);
        fab = (FloatingActionButton) findViewById(R.id.floatingBtn);
        BacaData();
        adapter= new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataBaru.class);
                startActivity(intent);
            }
        });
    }

    public void BacaData(){
        ArrayList<HashMap<String,String>> daftarTeman = kendaliDB.getAllTeman();
        temanArrayList = new ArrayList<>();

        for(int i=0; i<daftarTeman.size();i++){
            Teman tm = new Teman();
            tm.setId(daftarTeman.get(i).get("id").toString());
            tm.setNama(daftarTeman.get(i).get("nama").toString());
            tm.setTelepon(daftarTeman.get(i).get("telepon").toString());
            temanArrayList.add(tm);
        }
    }
}