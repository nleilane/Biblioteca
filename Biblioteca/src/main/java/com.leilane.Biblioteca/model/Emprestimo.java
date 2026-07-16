package com.leilane.Biblioteca.model;

public class Emprestimo {
    private int id;
    private String book;
    private String user;
    private int loanDate;
    private int dueDate;
    private int returnDate;
    private boolean returned;
    private boolean fine;
    private int renewalCount;
    private String observations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(int loanDate) {
        this.loanDate = loanDate;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public int getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(int returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public boolean isFine() {
        return fine;
    }

    public void setFine(boolean fine) {
        this.fine = fine;
    }

    public int getRenewalCount() {
        return renewalCount;
    }

    public void setRenewalCount(int renewalCount) {
        this.renewalCount = renewalCount;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
