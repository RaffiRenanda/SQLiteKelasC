package com.example.sqllitekelasc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqllitekelasc.MainActivity;
import com.example.sqllitekelasc.R;
import com.example.sqllitekelasc.database.DBController;
import com.example.sqllitekelasc.model.Teman;
import com.example.sqllitekelasc.model.edit_teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context control;

    public TemanAdapter(ArrayList<Teman> listD){

        this.listData = listD;
    }
    @Override
    public TemanAdapter.TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());

        View v = layoutInf.inflate(R.layout.barisdata_teman,parent,false);
        control = parent.getContext();
        return new TemanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TemanAdapter.TemanViewHolder holder, int position) {
        String nm, tlp, id;
        id = listData.get(position).getId();
        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelepon();
        DBController db = new DBController(control);


        holder.namaTxt.setText(nm);
        holder.teleponTxt.setText(tlp);

        holder.kartu.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(control, holder.kartu);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnEdit:
                                Intent i = new Intent(control, edit_teman.class);
                                i.putExtra("id",id);
                                i.putExtra("nama",nm);
                                i.putExtra("telepon",tlp);
                                control.startActivity(i);
                                break;
                            case R.id.mnHapus:
                                HashMap<String,String> values = new HashMap<>();
                                values.put("id", id);
                                db.DeleteData(values);
                                Intent j = new Intent (control, MainActivity.class);
                                control.startActivity(j);
                                break;

                        }
                        return true;
                    }
                });
                popupMenu.show();
                return false;
            }
        });




    }

    @Override
    public int getItemCount() {

        return (listData != null)? listData.size() : 0;
    }

    public class TemanViewHolder extends  RecyclerView.ViewHolder{
        private CardView kartu;
        private TextView namaTxt, teleponTxt;
        public TemanViewHolder(View v){
            super(v);
            kartu = (CardView) v.findViewById(R.id.cardv);
                    namaTxt = (TextView) v.findViewById(R.id.textNama);
            teleponTxt = (TextView) v.findViewById(R.id.textTelepon);

        }
    }
}
