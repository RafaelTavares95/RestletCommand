/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ifpb.pos.restletserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ifpb.pos.restletserver.entities.Person;
import edu.ifpb.pos.restletserver.entities.User;

/**
 *
 * @author Rafael
 */
public class ConverterJson {
    public static Person convertToPerson(String json) {
        Gson gson = new GsonBuilder().create();
        Person person = gson.fromJson(json, Person.class);
        return person;
    }

    public static User convertToUser(String json) {
        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(json, User.class);
        return user;
    }
        
    public static String convertToJson(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }
}
