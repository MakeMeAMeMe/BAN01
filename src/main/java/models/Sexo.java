package models;

/**
 *
 * @author FQA
 */
public enum Sexo {
    Masculino(1), Feminino(2);
    private final int value;
    private final String display;

    private Sexo(int sexo) {
        if (sexo == 1) {
            this.display = "Masculino";
        } else {
            this.display = "Feminino";
        }
        this.value = sexo;
    }

    public int getSexo() {
        return value;
    }

    public String getDisplay() {
        return display;
    }
}
