package com.example.sqllitekelasc.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sqllitekelasc.DataBaru;
import com.example.sqllitekelasc.MainActivity;
import com.example.sqllitekelasc.R;
import com.example.sqllitekelasc.app.AppController;
import com.example.sqllitekelasc.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class edit_teman extends Activity {
    TextView idText;
    EditText Nama, Telepon;
    Button Save;
    String nma, tlp, id, namaEd, teleponEd;
    DBController controller = new DBController(this);
    int sukses;

    private static  String url_update = "http://10.0.2.2/umyTI/updatetm.php";
    public static final String TAG = edit_teman.class.getSimpleName();
    public static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_teman);

        idText = findViewById(R.id.textView);
        Nama = findViewById(R.id.edNama);
        Telepon = findViewById(R.id.edTelp);
        Save = findViewById(R.id.SimpanEdt);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("kunci1");
        nma = bundle.getString("kunci2");
        tlp = bundle.getString("kunci3");

        setTitle("Edit Data");
        idText.setText("id:"+id);
        Nama.setText(nma);
        Telepon.setText(tlp);
        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               EditData();

            }
        });

    }

    public void EditData() {
        namaEd = Nama.getText().toString();
        teleponEd = Telepon.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest strReq = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response : " + response.toString());
                try {
                    JSONObject jobj = new JSONObject(response);
                    sukses = jobj.getInt(TAG_SUCCES);
                    if (sukses == 1) {
                        Toast.makeText(edit_teman.this, "Sukses mengedit data", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(edit_teman.this, "Gagal ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error : " + error.getMessage());
                Toast.makeText(edit_teman.this, "Gagal edit data", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("nama", namaEd);
                params.put("telepon", teleponEd);
                return params;
            }
        };
        requestQueue.add(strReq);
        callHome();
    }
    public void callHome(){
        Intent i = new Intent(edit_teman.this, MainActivity.class);
        startActivity(i);
        finish();


    }


}
