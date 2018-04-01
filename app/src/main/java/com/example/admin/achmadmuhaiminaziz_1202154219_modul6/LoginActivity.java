package com.example.admin.achmadmuhaiminaziz_1202154219_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //membuat inisialisasi variabel
    EditText user, pass;
    ProgressDialog dlg;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inisialisasi semua objek yang digunakan pada class ini
        user = findViewById(R.id.email); pass = findViewById(R.id.password);
        dlg = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if(user!=null){
                    Intent move = new Intent(LoginActivity.this, home.class);
                    move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(move);
                    finish();
                }
            }
        };
    }

    //Method ketika activity berakhir
    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(listener);
    }

    //Method ketika activity dimulai
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }
    //jika button signin di sentuh
    public void masuk(View view) {
        dlg.setMessage("Loging in");

        //Membaca user input
        String inuser = user.getText().toString();
        String inpass = pass.getText().toString();

        //Apakah user input kosong?

        //Jika tidak :
        if(!TextUtils.isEmpty(inuser)||!TextUtils.isEmpty(inpass)){
            //Tampilkan dialog
            dlg.show();

            //Login dengan email dan password yang diinputkan user
            auth.signInWithEmailAndPassword(inuser, inpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Ketika login berhasil
                    if(task.isSuccessful()){
                        Intent move = new Intent(LoginActivity.this, home.class);
                        move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(move);
                        finish();

                        //Ketika login gagal
                    }else{
                        Toast.makeText(LoginActivity.this, "Pastikan Email dan Password sudah benar!", Toast.LENGTH_SHORT).show();
                    }

                    //Tutup dialog ketika login berhasil atau gagal
                    dlg.dismiss();
                }
            });

            //Jika user input kosong
        }else{
            user.setError("Required!");
            pass.setError("Password Minimal 6 Karakter!");
        }
    }
    //jika button signup disentuh
    public void daftar(View view) {
        startActivity(new Intent(LoginActivity.this, signup.class));
    }
}
