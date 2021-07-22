/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class UserDTO implements Serializable{
    private String email;
    private int roleID;
    private String address;
    private String name;
    private String phone;

    public UserDTO(String email, int roleID, String address, String name, String phone) {
        this.email = email;
        this.roleID = roleID;
        this.address = address;
        this.name = name;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
    
    
}
