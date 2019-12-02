package com.example.muslimapp;

class ModelNama {
    private String id;
    private String judul;

    public ModelNama(String s, String id, String judul) {
        this.id = id;
        this.judul = judul;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}