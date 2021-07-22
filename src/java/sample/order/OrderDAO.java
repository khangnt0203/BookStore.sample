/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import sample.book.BookDTO;
import sample.connection.MyConnection;

/**
 *
 * @author ACER
 */
public class OrderDAO implements Serializable {

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

    public boolean orderBook(String customer, ArrayList<BookDTO> listBook) throws NamingException, SQLException {
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "{call orderBook(?, ?, ?, ?)}";
                stm = con.prepareStatement(sql);
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
