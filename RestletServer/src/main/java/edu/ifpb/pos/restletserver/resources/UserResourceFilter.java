/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ifpb.pos.restletserver.resources;

import edu.ifpb.pos.restletserver.ConverterJson;
import edu.ifpb.pos.restletserver.dao.DAO;
import edu.ifpb.pos.restletserver.dao.DAOFactory;
import edu.ifpb.pos.restletserver.entities.Person;
import edu.ifpb.pos.restletserver.entities.User;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Rafael
 */
public class UserResourceFilter extends ServerResource{
    private final DAO<User> dao = DAOFactory.createUserDao();

    @Delete
    public StringRepresentation delete() {
        try {
            String u = (String) getRequest().getAttributes().get("code");
            User user = dao.buscar(u, User.class);
            dao.excluir(user);
            return new StringRepresentation("Dados deletados com sucesso.");
        } catch (Exception ex) {
            return new StringRepresentation("ERROR: " + ex.getMessage());
        }
    }

    @Put
    public StringRepresentation update(Representation representation) {
        try {
            String u = representation.getText();
            String key = (String) getRequest().getAttributes().get("code");
            User user1 = ConverterJson.convertToUser(u);
            User user2 = dao.buscar(key, User.class);
            user2.setPassword(user1.getPassword());
            user2.setName(user2.getName());
            dao.atualizar(user2);
            return new StringRepresentation("Dados atualizados com sucesso.");
        } catch (Exception ex) {
            return new StringRepresentation("ERROR: " + ex.getMessage());
        }
    }

    @Get()
    public StringRepresentation get() {
        String id = (String) getRequest().getAttributes().get("code");
        User user = dao.buscar(id, User.class);
        String result = ConverterJson.convertToJson(user);
        if (result.equals("null")) {
            result = "{}";
        }
        return new StringRepresentation(result);
    }
}
