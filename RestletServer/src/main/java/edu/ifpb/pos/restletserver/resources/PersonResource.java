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
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 *
 * @author Rafael
 */
public class PersonResource extends ServerResource{
    private final DAO<Person> dao = DAOFactory.createPersonDao();
    
    @Post()
    public StringRepresentation save(Representation r) {
        try {
            String p = r.getText();
            Person person = ConverterJson.convertToPerson(p);
            dao.salvar(person);
            return new StringRepresentation("Success: Dados inseridos com sucesso!");
        } catch (Exception ex) {
            return new StringRepresentation("Error: " + ex.getMessage());
        }
    }
    
    @Get
    public StringRepresentation list() {        
        try {
            String result = ConverterJson.convertToJson(dao.consultaLista("p.list", null));
            return new StringRepresentation(result);
        } catch (Exception ex) {
            return new StringRepresentation("Error: " + ex.getMessage());
        }
    }
}
