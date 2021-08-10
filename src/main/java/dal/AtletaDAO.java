package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Atleta;
import models.Sexo;

/**
 *
 * @author FQA
 */
public class AtletaDAO {

    private int getNextId() throws SQLException {
        Connection conn = Conexao.getInstance().getConnection();
        PreparedStatement nextIdQ = conn.prepareStatement("SELECT nextval('cod_pessoa_seq');");
        ResultSet rs = nextIdQ.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        throw new SQLException("Erro ao recuperar o próximo ID de pessoas");
    }

    public void create(Atleta atleta) throws SQLException {
        Connection conn = Conexao.getInstance().getConnection();

        PreparedStatement insertPessoas, insertAtleta;

        int nextId = this.getNextId();

        insertPessoas = conn.prepareStatement("INSERT INTO Pessoas ("
                + "cod_pessoa, "
                + "nome, "
                + "documento, "
                + "data_nascimento) "
                + "VALUES (?, ?, ?, ?);");

        insertPessoas.setInt(1, nextId);
        insertPessoas.setString(2, atleta.getNome());
        insertPessoas.setString(3, atleta.getDocumento());
        insertPessoas.setObject(4, atleta.getDataNascimento());
        insertPessoas.execute();
        insertPessoas.close();

        insertAtleta = conn.prepareStatement(
                "INSERT INTO atletas ("
                + "cod_atleta, "
                + "peso, "
                + "altura, "
                + "sexo, "
                + "cidade, "
                + "estado, "
                + "pais, "
                + "funcao, "
                + "titular, "
                + "cod_equipe"
                + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        insertAtleta.setInt(1, nextId);
        insertAtleta.setFloat(2, atleta.getPeso());
        insertAtleta.setFloat(3, atleta.getAltura());
        insertAtleta.setInt(4, atleta.getSexo().getSexo());
        insertAtleta.setString(5, atleta.getCidade());
        insertAtleta.setString(6, atleta.getEstado());
        insertAtleta.setString(7, atleta.getPais());
        insertAtleta.setString(8, atleta.getFuncao());
        insertAtleta.setBoolean(9, atleta.isTitular());
        insertAtleta.setInt(10, atleta.getCodEquipe());
        insertAtleta.execute();
        insertAtleta.close();
    }

    public List<Atleta> listAll() throws SQLException {
        Connection conn = Conexao.getInstance().getConnection();
        Statement st = conn.createStatement();
        List<Atleta> list = new ArrayList<>();
        String sql = "SELECT "
                + "cod_atleta, "
                + "nome, "
                + "data_nascimento, "
                + "documento, "
                + "peso, "
                + "sexo, "
                + "cidade, "
                + "estado, "
                + "pais, "
                + "funcao, "
                + "titular, "
                + "cod_equipe "
                + "FROM atletas "
                + "INNER JOIN Pessoas "
                + "ON Pessoas.cod_pessoa = Atletas.cod_atleta";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            Atleta atleta = new Atleta();
            atleta.setCod(result.getInt(1));
            atleta.setNome(result.getString(2));
            atleta.setDataNascimento(result.getObject(3, LocalDate.class));
            atleta.setDocumento(result.getString(4));
            atleta.setPeso(result.getFloat(5));
            atleta.setSexo(
                    result.getInt(6) == Sexo.Masculino.getSexo()
                    ? Sexo.Masculino
                    : Sexo.Feminino);
            atleta.setCidade(result.getString(7));
            atleta.setEstado(result.getString(8));
            atleta.setPais(result.getString(9));
            atleta.setFuncao(result.getString(10));
            atleta.setTitular(result.getBoolean(11));
            atleta.setCodEquipe(result.getInt(12));
            list.add(atleta);
        }
        return list;
    }

    public List<Atleta> listAtletasEquipe(int codEquipe) throws SQLException {
        Connection conn = Conexao.getInstance().getConnection();
        String sql = "SELECT "
                + "cod_atleta, "
                + "nome, "
                + "data_nascimento, "
                + "documento, "
                + "peso, "
                + "sexo, "
                + "cidade"
                + "estado, "
                + "pais, "
                + "funcao, "
                + "titular, "
                + "cod_equipe, "
                + "altura "
                + "FROM atletas "
                + "INNER JOIN Pessoas "
                + "ON Pessoas.cod_pessoa = Atletas.cod_atleta"
                + "WHERE cod_equipe = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, codEquipe);
        List<Atleta> list = new ArrayList<>();

        ResultSet result = st.executeQuery();
        while (result.next()) {
            Atleta atleta = new Atleta();
            atleta.setCod(result.getInt(1));
            atleta.setNome(result.getString(2));
            atleta.setDataNascimento(result.getObject(3, LocalDate.class));
            atleta.setDocumento(result.getString(4));
            atleta.setPeso(result.getFloat(5));
            atleta.setSexo(
                    result.getInt(6) == Sexo.Masculino.getSexo()
                    ? Sexo.Masculino
                    : Sexo.Feminino);
            atleta.setCidade(result.getString(7));
            atleta.setEstado(result.getString(8));
            atleta.setPais(result.getString(9));
            atleta.setFuncao(result.getString(10));
            atleta.setTitular(result.getBoolean(11));
            atleta.setCodEquipe(result.getInt(12));
            atleta.setAltura(result.getInt(13));
            list.add(atleta);
        }
        return list;
    }
}
