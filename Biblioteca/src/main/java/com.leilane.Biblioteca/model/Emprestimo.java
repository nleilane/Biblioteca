package com.leilane.Biblioteca.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Livro livro;
    private Date dateLoan;
    private Date dateExpectedReturn;
    @Nullable
    private Date dateReturn ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Date getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(Date dateLoan) {
        this.dateLoan = dateLoan;
    }

    public Date getDateExpectedReturn() {
        return dateExpectedReturn;
    }

    public void setDateExpectedReturn(Date dateExpectedReturn) {
        this.dateExpectedReturn = dateExpectedReturn;
    }

    @Nullable
    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(@Nullable Date dateReturn) {
        this.dateReturn = dateReturn;
    }
}
