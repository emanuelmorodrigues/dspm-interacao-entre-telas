package br.ufc.quixada.interacaoentretelas;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Pessoa implements Serializable {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id = 0;
    private boolean isFilled;
    private String nome;
    private String sobrenome;

    public Pessoa() {
        isFilled = false;
        id = count.incrementAndGet();
    }

    public Pessoa(String nome, String sobrenome){
        isFilled = false;
        id = count.incrementAndGet();
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public void setNome(String nome){  this.nome = nome; }
    public void setSobrenome(String sobrenome){ this.sobrenome = sobrenome; }
    public int getId() { return this.id; }
    public String getNome() { return this.nome; }
    public String getSobrenome() { return this.sobrenome; }

    public void editPessoa(String nome, String sobrenome){
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public static class ChildClass implements Serializable {
        public ChildClass() {}
    }

    @Override
    public String toString(){
        return "[" + this.id + "]" + " " + this.nome + " " + this.sobrenome + "\n";
    }
}