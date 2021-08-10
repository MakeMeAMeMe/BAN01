package models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author FQA
 */
public class AtletaTest {

    @Test
    public void AtletaComNomeJoaoTemNomeJoao() {
        String nomeAtleta = "João";

        Atleta atleta = new Atleta();
        atleta.setNome(nomeAtleta);

        assertThat(atleta.getNome(), is(equalTo(nomeAtleta)));
    }

    @Test
    public void AtletaFeminioTemSexoFeminino() {
        Sexo sexo = Sexo.Feminino;

        Atleta atleta = new Atleta();
        atleta.setSexo(Sexo.Feminino);
        System.out.println(String.valueOf(atleta.getSexo()));
        assertThat(atleta.getSexo(), is(equalTo(sexo)));
    }

    @Test
    public void AtletaFemininoTemSexoDisplayFeminino() {
        String sexoText = "Feminino";

        Atleta atleta = new Atleta();
        atleta.setSexo(Sexo.Feminino);

        assertThat(atleta.getSexo().getDisplay(), is(equalTo(sexoText)));
    }

    @Test
    public void AtletaMasculinoTemSexoMasculino() {
        Sexo sexo = Sexo.Masculino;

        Atleta atleta = new Atleta();
        atleta.setSexo(Sexo.Masculino);

        assertThat(atleta.getSexo(), is(equalTo(sexo)));
    }

    @Test
    public void AtletaMasculinoTemSexoDisplayMasculino() {
        String sexoText = "Masculino";

        Atleta atleta = new Atleta();
        atleta.setSexo(Sexo.Masculino);

        assertThat(atleta.getSexo().getDisplay(), is(equalTo(sexoText)));
    }
}
