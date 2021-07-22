/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.book;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import sample.connection.MyConnection;

/**
 *
 * @author ACER
 */
public class BookDAO implements Serializable {

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

    public boolean insertNewBook(BookDTO book) throws SQLException, NamingException {
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "insert into tblBook(bookID, categoryID, bookTitle,"
                        + " bookDescription, author, price, image, amount) "
                        + "values(?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);

                stm.setString(1, book.getBookID());
                stm.setString(2, book.getCategoryID());
                stm.setString(3, book.getBookTitle());
                stm.setString(4, book.getBookDescription());
                stm.setString(5, book.getAuthor());
                stm.setFloat(6, book.getPrice());
                stm.setString(7, book.getImage());
                stm.setInt(8, book.getAmount());
                return stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<BookDTO> getAllBook(boolean isAdmin) throws SQLException, NamingException {
        ArrayList<BookDTO> result = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "{call getAllBook(@isAdmin = ?)}";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, isAdmin);
                rs = stm.executeQuery();

                String bookID;
                String categoryName;
                String bookTitle;
                String bookDescription;
                String author;
                String image;
                float price;
                boolean status = false;
                int amout;

                while (rs.next()) {
                    bookID = rs.getString("bookID");
                    categoryName = rs.getString("categoryName");
                    bookTitle = rs.getString("bookTitle");
                    bookDescription = rs.getString("bookDescription");
                    author = rs.getString("author");
                    image = rs.getString("image");
                    price = rs.getFloat("price");
                    amout = rs.getInt("amount");
                    if (isAdmin) {
                        status = rs.getBoolean("status");
                    }

                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    BookDTO dto = new BookDTO(bookID, categoryName, bookTitle, bookDescription, author, image, price, status, amout);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<BookDTO> searchBookByRangeMoney(float minMoney, float maxMoney, boolean isAdmin) throws SQLException, NamingException {
        ArrayList<BookDTO> result = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "{call searchBookByRangeMoney(@minMoney = ?, @maxMoney = ?, @isAdmin = ?)}";
                stm = con.prepareStatement(sql);
                stm.setFloat(1, minMoney);
                stm.setFloat(2, maxMoney);
                stm.setBoolean(3, isAdmin);

                rs = stm.executeQuery();

                String bookID;
                String categoryName;
                String bookTitle;
                String bookDescription;
                String author;
                String image;
                float price;
                boolean status = false;
                int amout;

                while (rs.next()) {
                    bookID = rs.getString("bookID");
                    categoryName = rs.getString("categoryName");
                    bookTitle = rs.getString("bookTitle");
                    bookDescription = rs.getString("bookDescription");
                    author = rs.getString("author");
                    image = rs.getString("image");
                    price = rs.getFloat("price");
                    amout = rs.getInt("amount");
                    if (isAdmin) {
                        status = rs.getBoolean("status");
                    }

                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    BookDTO dto = new BookDTO(bookID, categoryName, bookTitle, bookDescription, author, image, price, status, amout);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<BookDTO> searchBookByCategory(String bookCategorySearch, boolean isAdmin) throws SQLException, NamingException {
        ArrayList<BookDTO> result = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "{call searchBookByCategory(@categorySearch = ?, @isAdmin = ?)}";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookCategorySearch);
                stm.setBoolean(2, isAdmin);
                rs = stm.executeQuery();

                String bookID;
                String categoryName;
                String bookTitle;
                String bookDescription;
                String author;
                String image;
                float price;
                boolean status = false;
                int amout;

                while (rs.next()) {
                    bookID = rs.getString("bookID");
                    categoryName = rs.getString("categoryName");
                    bookTitle = rs.getString("bookTitle");
                    bookDescription = rs.getString("bookDescription");
                    author = rs.getString("author");
                    image = rs.getString("image");
                    price = rs.getFloat("price");
                    amout = rs.getInt("amount");
                    if (isAdmin) {
                        status = rs.getBoolean("status");
                    }

                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    BookDTO dto = new BookDTO(bookID, categoryName, bookTitle, bookDescription, author, image, price, status, amout);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<BookDTO> searchBookByName(String bookTitleSearchValue, boolean isAdmin) throws SQLException, NamingException {
        ArrayList<BookDTO> result = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "{call searchBookByName(@searchValue = ?, @isAdmin = ?)}";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookTitleSearchValue);
                stm.setBoolean(2, isAdmin);
                rs = stm.executeQuery();

                String bookID;
                String categoryName;
                String bookTitle;
                String bookDescription;
                String author;
                String image;
                float price;
                boolean status = false;
                int amout;

                while (rs.next()) {
                    bookID = rs.getString("bookID");
                    categoryName = rs.getString("categoryName");
                    bookTitle = rs.getString("bookTitle");
                    bookDescription = rs.getString("bookDescription");
                    author = rs.getString("author");
                    image = rs.getString("image");
                    price = rs.getFloat("price");
                    amout = rs.getInt("amount");
                    if (isAdmin) {
                        status = rs.getBoolean("status");
                    }

                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    BookDTO dto = new BookDTO(bookID, categoryName, bookTitle, bookDescription, author, image, price, status, amout);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteBook(String bookID) throws SQLException, NamingException {
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "update tblBook set status = 'false' where bookID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookID);

                return stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateBook(BookDTO book) throws SQLException, NamingException {
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "update tblBook set categoryID = ?, bookTitle = ?, "
                        + "bookDescription = ?, author = ?, image = ?, price = ?, amount = ?, status = ? where bookID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, book.getCategoryID());
                stm.setString(2, book.getBookTitle());
                stm.setString(3, book.getBookDescription());
                stm.setString(4, book.getAuthor());
                stm.setString(5, book.getImage());
                stm.setFloat(6, book.getPrice());
                stm.setInt(7, book.getAmount());
                stm.setBoolean(8, book.isStatus());
                stm.setString(9, book.getBookID());
                return stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public BookDTO getBookDetail(String bookID) throws SQLException, NamingException {
        BookDTO book = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select categoryID, bookTitle, "
                        + "bookDescription, author, image, price, amount, status "
                        + "from tblBook where bookID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String bookTitle = rs.getString("bookTitle");
                    String bookDescription = rs.getString("bookDescription");
                    String author = rs.getString("author");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("status");
                    int amout = rs.getInt("amount");

                    book = new BookDTO(bookID, bookTitle, bookDescription, author, image, price, categoryID, status, amout);
                }
            }
        } finally {
            closeConnection();
        }
        return book;
    }

    public ArrayList<BookDTO> getListBookForCart(Map<String, Integer> listBookCart) throws SQLException, NamingException {
        ArrayList<BookDTO> result = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select bookTitle, price from tblBook where bookID = ?";
                for (Map.Entry<String, Integer> bookCart : listBookCart.entrySet()) {
                    stm = con.prepareStatement(sql);
                    stm.setString(1, bookCart.getKey());
                    rs = stm.executeQuery();

                    if (rs.next()) {
                        String bookTitle = rs.getString("bookTitle");
                        float price = rs.getFloat("price") * bookCart.getValue();

                        if (result == null) {
                            result = new ArrayList<>();
                        }

                        BookDTO dto = new BookDTO(bookCart.getKey(), bookTitle, price, bookCart.getValue());
                        result.add(dto);
                    }
                }

            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<BookDTO> getListBookToBuy(Map<String, Integer> listBookCart) throws SQLException, NamingException {
        ArrayList<BookDTO> result = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select price from tblBook where bookID = ?";
                for (Map.Entry<String, Integer> bookCart : listBookCart.entrySet()) {
                    stm = con.prepareStatement(sql);
                    stm.setString(1, bookCart.getKey());
                    rs = stm.executeQuery();

                    if (rs.next()) {
                        float price = rs.getFloat("price") * bookCart.getValue();

                        if (result == null) {
                            result = new ArrayList<>();
                        }

                        BookDTO dto = new BookDTO(bookCart.getKey(), price, bookCart.getValue());
                        result.add(dto);
                    }
                }

            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean oderBook(String customer, float orderTotalPrice, ArrayList<BookDTO> listBook) throws SQLException, NamingException {
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "insert into tblOrder(customer, totalPrice) values(?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, customer);
                stm.setFloat(2, orderTotalPrice);

                int row = stm.executeUpdate();
                if (row > 0) {
                    con.setAutoCommit(false);
                    sql = "insert into tblOrderDetail(orderID, bookID, amount, totalPrice) "
                            + "values (?, ?, ?, ?)";
                    stm = con.prepareStatement(sql);
                    int lastOrderID = getLastOrderID();
                    for (BookDTO book : listBook) {
                        stm.setInt(1, lastOrderID);
                        stm.setString(2, book.getBookID());
                        stm.setInt(3, book.getAmount());
                        stm.setFloat(4, book.getPrice());

                        stm.addBatch();
                    }
                    stm.executeBatch();
                    con.commit();

                    sql = "update tblBook set amount = ? where bookID = ?";
                    stm = con.prepareStatement(sql);
                    for (BookDTO book : listBook) {
                        int currentBookAmount = getCurrentBookAmount(book.getBookID());
                        int newBookAmount = currentBookAmount - book.getAmount();
                        stm.setInt(1, newBookAmount);
                        stm.setString(2, book.getBookID());

                        stm.addBatch();
                    }
                    stm.executeBatch();
                    con.commit();
                    //insertOrderDetail(listBook);
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int getCurrentBookAmount(String bookID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select amount from tblbook where bookID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, bookID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("amount");
                }
            }
        } finally {
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
        return result;
    }

    private void insertOrderDetail(ArrayList<BookDTO> listBook) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql = "insert into tblOrderDetail(orderID, bookID, amount, totalPrice) "
                        + "values (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                for (BookDTO book : listBook) {
                    stm.setInt(1, getLastOrderID());
                    stm.setString(2, book.getBookID());
                    stm.setInt(3, book.getAmount());
                    stm.setFloat(4, book.getPrice());
                    stm.addBatch();
                }

                stm.executeBatch();
                con.commit();

            }
        } finally {
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
    }

    private int getLastOrderID() throws SQLException, NamingException {
        int result = 0;

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = MyConnection.getMyConnection();
            if (con != null) {
                String sql = "select MAX(orderID) as orderID from tblOrder";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("orderID");
                }
            }
        } finally {
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
        return result;
    }
}
