package controllers;

import dal.AtletaDAO;
import java.sql.SQLException;
import java.util.List;
import models.Atleta;

/**
 *
 * @author FQA
 */
public class AtletasController {

    AtletaDAO atletaDAO = new AtletaDAO();

    public void create(Atleta atleta) throws SQLException {
        atletaDAO.create(atleta);
    }

    public List<Atleta> listar() throws SQLException {
        return atletaDAO.listAll();
    }

    public List<Atleta> listarCodEquipe(int codEquipe) throws SQLException {
        return atletaDAO.listAtletasEquipe(codEquipe);
    }
}
