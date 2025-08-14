package com.emakers.Biblioteca.data.entity;

public enum PessoaRole {

    ADMIN(0),

    USER(1);

    private int role;

    PessoaRole(int role){
        this.role = role;
    }

    public int getRole(){
        return role;
    }
}
