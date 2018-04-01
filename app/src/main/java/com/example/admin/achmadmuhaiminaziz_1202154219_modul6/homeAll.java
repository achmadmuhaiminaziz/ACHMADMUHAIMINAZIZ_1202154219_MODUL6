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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homeAll extends Fragment {
    //membuat inisialisasi variabel
    RecyclerView rc; DatabaseReference dataref;
    ArrayList<DbPost> list;  AdapterPost adapterPost;
    //membuat method homeAll
    public homeAll() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inisialisasi semua objek
        View v = inflater.inflate(R.layout.activity_home_all, container, false);
        rc = v.findViewById(R.id.rchomeall);
        list = new ArrayList<>();
        dataref = FirebaseDatabase.getInstance().getReference().child("post");
        adapterPost = new AdapterPost(list, this.getContext());

        //Menampilkan recyclerview

        rc.setHasFixedSize(true);
        //membuat tampilan recycleview menjadi 2 dengan gridlayout
        rc.setLayoutManager(new GridLayoutManager(getContext(),2));
        rc.setAdapter(adapterPost);

        //Event listener ketika value pada Firebase berubah
        dataref.addValueEventListener(new ValueEventListener() {
            //Digunakan untuk membaca seluruh postingan dari firebase
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    DbPost post = data.getValue(DbPost.class);
                    post.key = data.getKey();
                    list.add(post);
                    adapterPost.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }
}
