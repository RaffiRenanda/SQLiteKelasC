package com.example.sqllitekelasc.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.sqllitekelasc.MainActivity;
import com.example.sqllitekelasc.R;
import com.example.sqllitekelasc.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class edit_teman extends Activity {
    TextInputEditText Nama, Telepon;
    Button Save;
    String nma, tlp, id;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_teman);

        Nama = findViewById(R.id.edNama);
        Telepon = findViewById(R.id.edTelp);
        Save = findViewById(R.id.SimpanEdt);

        id = getIntent().getStringExtra("id");
        nma = getIntent().getStringExtra("nama");
        tlp = getIntent().getStringExtra("telepon");

        setTitle("Edit Data");
        Nama.setText(nma);
        Telepon.setText(tlp);
        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Nama.getText().toString().equals("")||Telepon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Mohon isi terlebih dahulu", Toast.LENGTH_LONG).show();
                }else{
                    nma=Nama.getText().toString();
                    tlp=Telepon.getText().toString();
                    HashMap<String,String> values = new HashMap<>();
                    values.put("id",id);
                    values.put("nama",nma);
                    values.put("telepon",tlp);
                    controller.UpdateData(values);
                    callHome();
                }
            }
        });

    }

    public void callHome(){
        Intent i = new Intent(edit_teman.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
