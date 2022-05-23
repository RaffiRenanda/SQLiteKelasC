package com.example.sqllitekelasc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sqllitekelasc.database.DBController;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataBaru extends AppCompatActivity {

    private TextInputEditText ednama, edTelepon;
    private Button Simpanbtn;
    String nm, tl;
    int success;
    private static  String url_insert = "https://20200140020.praktikumtiumy.com/tambahth.php";
    public static final String TAG = DataBaru.class.getSimpleName();
    public static final String TAG_SUCCES = "success";

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
                SimpanData();



            }
        });
    }
    public void SimpanData()
    {
        if(ednama.getText().toString().equals("") || edTelepon.getText().toString().equals("")){
            Toast.makeText(DataBaru.this, "Semua data harus diisi", Toast.LENGTH_SHORT).show();

        }else{
            nm=ednama.getText().toString();
            tl=edTelepon.getText().toString();

            RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());
            StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response : " + response.toString());
                    try {
                        JSONObject jobj = new JSONObject(response);
                        success = jobj.getInt(TAG_SUCCES);
                        if (success == 1) {
                            Toast.makeText(DataBaru.this, "SUkses simpan data", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(DataBaru.this, "Gagal ", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG,"Error : "+error.getMessage());
                    Toast.makeText(DataBaru.this, "Gagal simpan data", Toast.LENGTH_SHORT).show();
                }

        }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<>();
                    params.put("nama", nm);
                    params.put("telepon", tl);
                    return params;
                }
            }; requestQueue.add(strReq);
        }
    }

}
