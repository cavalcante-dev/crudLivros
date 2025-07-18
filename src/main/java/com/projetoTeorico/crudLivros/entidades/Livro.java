package com.projetoTeorico.crudLivros.entidades;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_User")
public class Livro {

    @Id
    @Column(name = "livroID")
    private UUID livroID;

    @Column(name = "livroNome")
    private String livroNome;

    @Column(name = "livroAutor")
    private String livroAutor;

    @Column(name = "livroAnoPublicacao")
    private Integer livroAnoPublicacao;

    @Column(name = "livroGenero")
    private String livroGenero;

    public Livro(UUID livroID, String livroNome, String livroAutor, Integer livroAnoPublicacao, String livroGenero) {
        this.livroID = livroID;
        this.livroNome = livroNome;
        this.livroAutor = livroAutor;
        this.livroAnoPublicacao = livroAnoPublicacao;
        this.livroGenero = livroGenero;
    }

    public Livro() {
    }

    public UUID getLivroID() {
        return livroID;
    }

    public void setLivroID(UUID livroID) {
        this.livroID = livroID;
    }

    public String getLivroAutor() {
        return livroAutor;
    }

    public void setLivroAutor(String livroAutor) {
        this.livroAutor = livroAutor;
    }

    public Integer getLivroAnoPublicacao() {
        return livroAnoPublicacao;
    }

    public void setLivroAnoPublicacao(Integer livroAnoPublicacao) {
        this.livroAnoPublicacao = livroAnoPublicacao;
    }

    public String getLivroGenero() {
        return livroGenero;
    }

    public void setLivroGenero(String livroGenero) {
        this.livroGenero = livroGenero;
    }

}
