package controllers;

import dal.EquipeDAO;
import java.sql.SQLException;
import java.util.List;
import models.Equipe;

/**
 *
 * @author FQA
 */
public class EquipesController {

    EquipeDAO equipeDAO = new EquipeDAO();

    public void create(Equipe equipe) throws SQLException {
        equipeDAO.create(equipe);
    }

    public List<Equipe> listar() throws SQLException {
        return equipeDAO.listAll();
    }

    public List<Equipe> listarComAtletas() throws SQLException {
        return equipeDAO.listAllComAtletas();
    }

    public List<Equipe> listarComAtletaMaisVelho() throws SQLException {
        return equipeDAO.listAllComAtletaMaisVelho();
    }

}
