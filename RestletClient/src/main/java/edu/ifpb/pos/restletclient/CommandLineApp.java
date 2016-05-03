/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ifpb.pos.restletclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
/**
 *
 * @author Rafael
 */
public class CommandLineApp {
    
    private static final String JSON_PERSON = "json_person";
    private static final String JSON_USER = "json_user";
    private static final String HELP = "help";
    private static final String INSERT = "insert";
    private static final String UPDATE = "update";
    private static final String SELECT = "select";
    private static final String DELETE = "delete";
    private static final String TYPE = "type";
    
    private static Options getOp(){
        Options op = new Options();
        op.addOption(null, HELP, false, "Exibir ajuda");
        op.addOption(null, INSERT, true, "Inserir dados de um usuario ou pessoa");
        op.addOption(null, UPDATE, true, "Atualizar dados de um usuario ou pessoa");
        op.addOption(null, DELETE, true, "Deletar dados de um usuario ou pessoa");
        op.addOption(null, SELECT, true, "Obter dados de um ou todos os usuarios ou pessoas");
        op.addOption(null, TYPE, true, "Tipo de informação [json_person|json_user]");
        return op;
    }
    
    private static void getHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("--[insert | update | delete | select] {key:value,...} "
                + "--type [json_person | json_user]", getOp());
    }
    
    private static void execute(CommandLine line) throws ParseException, IOException{
        if (line.hasOption(HELP)) {
            getHelp();
        }
        if (line.hasOption(INSERT) && line.hasOption(TYPE)) {
            String url = getUrlType(line.getOptionValue(TYPE));
            insert(url, line.getOptionValue(INSERT));
        }
        if (line.hasOption(UPDATE) && line.hasOption(TYPE)) {

            String url = getUrlType(line.getOptionValue(TYPE));
            update(url, line.getOptionValue(UPDATE));

        }
        if (line.hasOption(DELETE) && line.hasOption(TYPE)) {

            String url = getUrlType(line.getOptionValue(TYPE));
            delete(url, line.getOptionValue(DELETE));

        }
        if (line.hasOption(SELECT) && line.hasOption(TYPE)) {

            String url = getUrlType(line.getOptionValue(TYPE));
            String optionValue = line.getOptionValue(SELECT);
            if (optionValue == null) {
                select(url);
            } else {
                select(url, optionValue);
            }
        }

        throw new ParseException("ERROR: Comando inválido");
    }
    
    private static String getUrlType(String type) throws ParseException {
        switch (type) {
            case JSON_PERSON:
                return "http://localhost:8080/app/person";
            case JSON_USER:
                return "http://localhost:8080/app/user";
            default:
                throw new ParseException("ERROR: Tipo inválido");
        }
    }
    
    private static Filter convert(String json){
        Gson gson = new GsonBuilder().create();
        Filter fil = gson.fromJson(json, Filter.class);
        return fil;
    }
  
    //operations
    private static void insert(String url, String json) throws IOException {
        ClientResource client = new ClientResource(url);
        StringRepresentation r = new StringRepresentation(json, MediaType.APPLICATION_ALL_JSON);
        client.post(r).write(System.out);
    }

    private static void update(String url, String json) throws IOException {
        ClientResource client = new ClientResource(url + "/" + convert(json).getValue());
        StringRepresentation r = new StringRepresentation(json, MediaType.APPLICATION_ALL_JSON);
        client.put(r).write(System.out);
    }

    private static void delete(String url, String json) throws IOException {
        ClientResource client = new ClientResource(url + "/" + convert(json).getValue());
        client.delete().write(System.out);
    }

    private static void select(String url) throws IOException {
        ClientResource client = new ClientResource(url);
        client.get().write(System.out);
    }

    private static void select(String url, String json) throws IOException {
        ClientResource client = new ClientResource(url + "/" + convert(json).getValue());
        client.get().write(System.out);
    }
    
    public static void main(String[] args) {
         try {
            CommandLineParser parser = new DefaultParser();
            CommandLine line = parser.parse(getOp(), args);
            execute(line);
        } catch (ParseException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(CommandLineApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
