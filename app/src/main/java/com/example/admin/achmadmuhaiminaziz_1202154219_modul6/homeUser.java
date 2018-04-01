package com.example.admin.achmadmuhaiminaziz_1202154219_modul6;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homeUser extends Fragment {
    //membuat inisialisasi variabel
    DatabaseReference ref; AdapterPost adapter; ArrayList<DbPost> list;
    RecyclerView rc;
    //membuat method homeUser
    public homeUser() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inisialisasi semua objek pada database
        View v = inflater.inflate(R.layout.activity_home_user, container, false);
        ref = FirebaseDatabase.getInstance().getReference().child("post");
        list = new ArrayList<>();
        adapter = new AdapterPost(list, this.getContext());
        rc = v.findViewById(R.id.rchomeuser);

        //Menampilkan recyclerview
        rc.setHasFixedSize(true);
        //membuat tampilan recycleview menjadi 2 dengan gridlayout
        rc.setLayoutManager(new GridLayoutManager(getContext(),2));
        rc.setAdapter(adapter);

        //Event listener ketika data pada Firebase berubah
        ref.addValueEventListener(new ValueEventListener() {
            //Digunakan untuk membaca postingan user dari Firebase
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    DbPost cur = data.getValue(DbPost.class);
                    if(cur.getUser().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        cur.setKey(data.getKey());
                        list.add(cur);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }
}
