/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import models.Equipe;
import models.Sexo;

/**
 *
 * @author FQA
 */
public class EquipeDAO {

    private int getNextId() throws SQLException {
        Connection conn = Conexao.getInstance().getConnection();
        PreparedStatement nextIdQ = conn.prepareStatement("SELECT nextval('cod_equipe_seq');");
        ResultSet rs = nextIdQ.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        throw new SQLException("Erro ao recuperar o próximo ID de equipes");
    }

    public void create(Equipe equipe) throws SQLException {
        Connection conn = Conexao.getInstance().getConnection();
        int nextId = this.getNextId();
        PreparedStatement st;
        st = conn.prepareStatement(
                "INSERT INTO equipes ("
                + "cod_equipe, "
                + "nome "
                + ") "
                + "VALUES (?,?)");
        st.setInt(1, nextId);
        st.setString(2, equipe.getNome());
        st.execute();
        st.close();
    }

    public List<Equipe> listAll() throws SQLException {
        Connection conn = Conexao.getInstance().getConnection();
        Statement st = conn.createStatement();
        List<Equipe> list = new ArrayList<>();
        String sql = "SELECT "
                + "cod_equipe, "
                + "nome "
                + "FROM equipes";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            Equipe equipe = new Equipe();
            equipe.setCod(result.getInt(1));
            equipe.setNome(result.getString(2));
            list.add(equipe);
        }
        return list;
    }

    public List<Equipe> listAllComAtletas() throws SQLException {
        Connection conn = Conexao.getInstance().getConnection();
        String sql = "SELECT "
                + "equipes.cod_equipe, "
                + "equipes.nome, "
                + "atletas.cod_atleta, "
                + "pessoas.nome, "
                + "pessoas.data_nascimento, "
                + "pessoas.documento, "
                + "atletas.peso, "
                + "atletas.altura, "
                + "atletas.sexo, "
                + "atletas.cidade, "
                + "atletas.estado, "
                + "atletas.pais, "
                + "atletas.funcao, "
                + "atletas.titular "
                + "FROM Equipes "
                + "INNER JOIN Atletas "
                + "ON Atletas.cod_equipe = Equipes.cod_equipe "
                + "INNER JOIN Pessoas "
                + "ON Pessoas.cod_pessoa = Atletas.cod_atleta";
        PreparedStatement st = conn.prepareStatement(sql);
        List<Equipe> equipes = new ArrayList<>();

        ResultSet result = st.executeQuery();
        Equipe equipe;
        while (result.next()) {
            int codEquipe = result.getInt(1);
            equipe = new Equipe();
            equipe.setCod(codEquipe);
            if (!equipes.stream().anyMatch(equipeAux -> equipeAux.getCod() == codEquipe)) {
                equipe.setNome(result.getString(2));
                equipes.add(equipe);
            }
            int indexEquipe = equipes.indexOf(equipe);
            Atleta atleta = new Atleta();
            atleta.setCod(result.getInt(3));
            atleta.setNome(result.getString(4));
            atleta.setDataNascimento(result.getObject(5, LocalDate.class));
            atleta.setDocumento(result.getString(6));
            atleta.setPeso(result.getFloat(7));
            atleta.setAltura(result.getFloat(8));
            atleta.setSexo(
                    result.getInt(9) == Sexo.Masculino.getSexo()
                    ? Sexo.Masculino
                    : Sexo.Feminino
            );
            atleta.setCidade(result.getString(10));
            atleta.setEstado(result.getString(11));
            atleta.setPais(result.getString(12));
            atleta.setFuncao(result.getString(13));
            atleta.setTitular(result.getBoolean(14));
            atleta.setCodEquipe(codEquipe);
            equipes.get(indexEquipe).addAtleta(atleta);
        }
        return equipes;
    }

    public List<Equipe> listAllComAtletaMaisVelho() throws SQLException {
        Connection conn = Conexao.getInstance().getConnection();
        String sql = "SELECT "
                + "equipes.cod_equipe, "
                + "equipes.nome, "
                + "atletas.cod_atleta, "
                + "pessoas.nome, "
                + "pessoas.data_nascimento, "
                + "pessoas.documento, "
                + "atletas.peso, "
                + "atletas.altura, "
                + "atletas.sexo, "
                + "atletas.cidade, "
                + "atletas.estado, "
                + "atletas.pais,"
                + "atletas.funcao, "
                + "atletas.titular "
                + "FROM Equipes "
                + "INNER JOIN Atletas "
                + "ON Atletas.cod_equipe = Equipes.cod_equipe "
                + "INNER JOIN Pessoas "
                + "ON Pessoas.cod_pessoa = Atletas.cod_atleta "
                + "WHERE EXISTS ( "
                + "SELECT * "
                + "FROM Atletas AS a1 "
                + "INNER JOIN Pessoas AS p1 "
                + "ON p1.cod_pessoa = a1.cod_atleta "
                + "WHERE p1.data_nascimento = "
                + "(SELECT MIN(p2.data_nascimento) FROM  Pessoas AS p2) "
                + "AND Equipes.cod_equipe = a1.cod_equipe "
                + ");";

        PreparedStatement st = conn.prepareStatement(sql);
        List<Equipe> equipes = new ArrayList<>();

        ResultSet result = st.executeQuery();
        Equipe equipe;
        while (result.next()) {
            int codEquipe = result.getInt(1);
            equipe = new Equipe();
            equipe.setCod(codEquipe);
            if (!equipes.stream().anyMatch(equipeAux -> equipeAux.getCod() == codEquipe)) {
                equipe.setNome(result.getString(2));
                equipes.add(equipe);
            }
            int indexEquipe = equipes.indexOf(equipe);
            Atleta atleta = new Atleta();
            atleta.setCod(result.getInt(3));
            atleta.setNome(result.getString(4));
            atleta.setDataNascimento(result.getObject(5, LocalDate.class));
            atleta.setDocumento(result.getString(6));
            atleta.setPeso(result.getFloat(7));
            atleta.setAltura(result.getFloat(8));
            atleta.setSexo(
                    result.getInt(9) == Sexo.Masculino.getSexo()
                    ? Sexo.Masculino
                    : Sexo.Feminino
            );
            atleta.setCidade(result.getString(10));
            atleta.setEstado(result.getString(11));
            atleta.setPais(result.getString(12));
            atleta.setFuncao(result.getString(13));
            atleta.setTitular(result.getBoolean(14));
            atleta.setCodEquipe(codEquipe);
            equipes.get(indexEquipe).addAtleta(atleta);
        }
        return equipes;
    }
}
