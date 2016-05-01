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
    @NamedQuery(name = "p.list",query = "SELECT p FROM Person p")    
})

@Entity
@Table(name="tb_pessoa")
public class Person implements Serializable{
    
    @Id
    @Column(length = 10,nullable = false)
    private String code;
    @Column(length = 100,nullable = false)
    private String name;
    @Column(length = 254)
    private String address;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
