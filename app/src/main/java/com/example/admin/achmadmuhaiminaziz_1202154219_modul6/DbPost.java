package com.example.admin.achmadmuhaiminaziz_1202154219_modul6;

/**
 * Created by Admin on 4/1/2018.
 */

public class DbPost {
    public String image, title, caption, user, key;

    //Dibutuhkan kosong untuk membaca data
    public DbPost() {
    }

    //Constructor dari class ini
    public DbPost(String caption, String image, String title, String user) {
        this.image = image;
        this.title = title;
        this.caption = caption;
        this.user = user;
    }

    //Mendapatkan key dari Firebase
    public String getKey() {
        return key;
    }

    //Menentukan key dari Firebase
    public void setKey(String key) {
        this.key = key;
    }

    //membuat getter variabel dari class ini
    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getUser() {
        return user;
    }
}
