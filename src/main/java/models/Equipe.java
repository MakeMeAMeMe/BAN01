package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FQA
 */
public class Equipe {

    private int cod;
    private String nome;
    private final List<Atleta> atletas;

    public Equipe() {
        atletas = new ArrayList<>();
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addAtleta(Atleta atleta) {
        this.atletas.add(atleta);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Equipe [Código %d, Nome %s]\n", this.getCod(), this.getNome()));
        this.getAtletas().forEach(atleta -> {
            builder.append('\t').append(atleta).append('\n');
        });
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.cod;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipe other = (Equipe) obj;
        return this.cod == other.cod;
    }

}
