package br.ufms.facom.mariocarvalho.bookkeeping.model;

import java.io.Serializable;

/**
 * Created by Mário de Araújo Carvalho on 30/10/17.
 */

public class Item implements Serializable{
    //Atributos
    private int id;
    private String nome;
    private String descricao;
    private int favorito;

    //Construtor Vazio
    public Item() {
    }
    //Construtor Carregado:
    public Item(int id, String nome, String descricao, int favorito) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.favorito = favorito;
    }

    //Construtor para com ID
    public Item(int id) {
        this.id = id;
    }

    //Gets and Seters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }
}
