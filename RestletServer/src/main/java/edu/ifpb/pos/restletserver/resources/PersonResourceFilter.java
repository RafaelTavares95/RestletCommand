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
public class PersonResourceFilter extends ServerResource{
    private final DAO<Person> dao = DAOFactory.createPersonDao();

    @Delete
    public StringRepresentation delete() {
        try {
            String p = (String) getRequest().getAttributes().get("code");
            Person person = dao.buscar(p, Person.class);
            dao.excluir(person);
            return new StringRepresentation("Dados deletados com sucesso.");
        } catch (Exception ex) {
            return new StringRepresentation("ERROR: " + ex.getMessage());
        }
    }

    @Put
    public StringRepresentation update(Representation representation) {
        try {
            String p = representation.getText();
            String key = (String) getRequest().getAttributes().get("code");
            Person person1 = ConverterJson.convertToPerson(p);
            Person person2 = dao.buscar(key, Person.class);
            person2.setAddress(person1.getAddress());
            person2.setName(person1.getName());
            dao.atualizar(person2);
            return new StringRepresentation("Dados atualizados com sucesso.");
        } catch (Exception ex) {
            return new StringRepresentation("ERROR: " + ex.getMessage());
        }
    }

    @Get()
    public StringRepresentation get() {
        String id = (String) getRequest().getAttributes().get("code");
        Person person = dao.buscar(id, Person.class);
        String result = ConverterJson.convertToJson(person);
        if (result.equals("null")) {
            result = "{}";
        }
        return new StringRepresentation(result);
    }
}
