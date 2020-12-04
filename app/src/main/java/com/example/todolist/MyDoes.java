package com.example.todolist;

public class MyDoes {

    private String titledoes, keydoes, descdoes;
    private String confirmdoes;

    public MyDoes() {
    }

    public MyDoes(String titledoes, String confirmdoes, String descdoes, String keydoes) {
        this.titledoes = titledoes;
        this.keydoes = keydoes;
        this.confirmdoes = confirmdoes;
        this.descdoes = descdoes;
    }



    public String getKeydoes() {
        return keydoes;
    }

    public void setKeydoes(String keydoes) {
        this.keydoes = keydoes;
    }

    public String getTitledoes() {
        return titledoes;
    }

    public void setTitledoes(String titledoes) {
        this.titledoes = titledoes;
    }

    public String getConfirmdoes() {
        return confirmdoes;
    }
    public void setConfirmdoes(String confirmdoes) {
        this.confirmdoes = confirmdoes;
    }

    public String getDescdoes() {
        return descdoes;
    }

    public void setDescdoes(String descdoes) {
        this.descdoes = descdoes;
    }
}
