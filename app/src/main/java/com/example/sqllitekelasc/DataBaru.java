package com.example.sqllitekelasc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqllitekelasc.database.DBController;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class DataBaru extends AppCompatActivity {

    private TextInputEditText ednama, edTelepon;
    private Button Simpanbtn;
    String nm, tl;

    DBController kendaliDatabase = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_baru);

        ednama = (TextInputEditText) findViewById(R.id.tietNama);
        edTelepon = (TextInputEditText) findViewById(R.id.tietTelepon);
        Simpanbtn = (Button) findViewById(R.id.buttonSave);

        Simpanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ednama.getText().toString().equals("")||edTelepon.getText().toString().equals("")){
                    Snackbar.make(view, "Data belum lengkap", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    nm = ednama.getText().toString();
                    tl = edTelepon.getText().toString();

                    HashMap<String,String> dataKirim = new HashMap<>();
                    dataKirim.put("nama", nm);
                    dataKirim.put("telepon", tl);

                    kendaliDatabase.tambahData(dataKirim);
                    callHome();


                }
            }
        });
    }

    public void callHome(){
        Intent in = new Intent(DataBaru.this,MainActivity.class);
        startActivity(in);
        finish();
    }
}
