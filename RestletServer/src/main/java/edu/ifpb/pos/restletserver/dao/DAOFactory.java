/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ifpb.pos.restletserver.dao;

import edu.ifpb.pos.restletserver.entities.Person;
import edu.ifpb.pos.restletserver.entities.User;

/**
 *
 * @author Rafael
 */
public class DAOFactory {
    
    public static DAO<Person> createPersonDao(){
        return new DAOJPA<>();
    }
    
    public static DAO<User> createUserDao(){
        return new DAOJPA<>();
    }
    
}
