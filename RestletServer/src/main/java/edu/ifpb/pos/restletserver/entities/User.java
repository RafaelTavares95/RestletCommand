/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ifpb.pos.restletserver.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Rafael
 */
@NamedQueries({
    @NamedQuery(name = "u.list",query = "SELECT u FROM User u")    
})

@Entity
@Table(name = "tb_user")
public class User implements Serializable{
   
    @Id
    @Column(length = 10)
    private String person_code;
    @Column(length = 10, nullable = false)
    private String name;
    @Column(length = 10, nullable = false)
    private String password;

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
