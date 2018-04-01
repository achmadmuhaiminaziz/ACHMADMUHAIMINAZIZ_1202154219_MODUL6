package com.example.admin.achmadmuhaiminaziz_1202154219_modul6;

/**
 * Created by Admin on 4/1/2018.
 */

public class DbComment {
    String yangkomen, komennya, fotoyangdikomen;

    //Dibutuhkan method kosong untuk membaca data
    public DbComment(){

    }

    //Constructor dari class ini
    public DbComment(String yangkomen, String komennya, String fotoyangdikomen) {
        this.yangkomen = yangkomen;
        this.komennya = komennya;
        this.fotoyangdikomen = fotoyangdikomen;
    }

    //membuat getter untuk variabel dari class ini
    public String getFotoyangdikomen() {
        return fotoyangdikomen;
    }

    public String getYangkomen() {
        return yangkomen;
    }

    public String getKomennya() {
        return komennya;
    }
}
