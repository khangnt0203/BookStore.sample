/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import sample.connection.MyConnection;

/**
 *
 * @author ACER
 */
public class CategoryDAO implements Serializable {

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

    public ArrayList<CategoryDTO> getAllCategory() throws NamingException, SQLException {
        ArrayList<CategoryDTO> result = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select categoryID, categoryName from tblBookCategory";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                String categoryID;
                String categoryName;

                while (rs.next()) {
                    categoryID = rs.getString("categoryID");
                    categoryName = rs.getString("categoryName");

                    if (result == null) {
                        result = new ArrayList<>();
                    }

                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
