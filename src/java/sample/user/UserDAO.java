/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.connection.MyConnection;

/**
 *
 * @author ACER
 */
public class UserDAO implements Serializable {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }

        if (stm != null) {
            stm.close();
        }

        if (con != null) {
            con.close();
        }
    }

    public UserDTO checkLogin(String email, String password) 
            throws SQLException, NamingException {
        UserDTO user = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select name, roleID, phone, address from tblUser where email = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                
                rs = stm.executeQuery();
                if(rs.next()){
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    int roleID = rs.getInt("roleID");
                    
                    user = new UserDTO(email, roleID, address, name, phone);
                }
            }
        } finally {
            closeConnection();
        }
        return user;
    }
}
