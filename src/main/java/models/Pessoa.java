package models;

import java.time.LocalDate;

/**
 *
 * @author FQA
 */
abstract class Pessoa {

    private int cod;
    private String nome;
    private String documento;
    private LocalDate dataNascimento;

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
