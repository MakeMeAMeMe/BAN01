package models;

/**
 *
 * @author FQA
 */
public class Atleta extends Pessoa {

    private float peso;
    private float altura;
    private Sexo sexo;
    private String cidade;
    private String estado;
    private String pais;
    private boolean titular;
    private String funcao;
    private int codEquipe;

    public float getPeso() {
        return peso;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getAltura() {
        return altura;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public boolean isTitular() {
        return titular;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        if (estado.length() > 2) {
            estado = estado.substring(0, 2);
        }
        this.estado = estado;
    }

    public void setPais(String pais) {
        if (pais.length() > 3) {
            pais = pais.substring(0, 3);
        }
        this.pais = pais;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getCodEquipe() {
        return codEquipe;
    }

    public void setCodEquipe(int codEquipe) {
        this.codEquipe = codEquipe;
    }

    @Override
    public String toString() {
        return String.format(
                "Atleta [Código %d, Nome %s,  %s %s %s]",
                this.getCod(),
                this.getNome(),
                this.getDocumento(),
                this.getSexo().getDisplay(),
                this.getDataNascimento()
        );
    }

}
