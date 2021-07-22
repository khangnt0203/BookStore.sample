/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.book;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class BookDTO implements Serializable {

    private String bookID;
    private String categoryName;
    private String bookTitle;
    private String bookDescription;
    private String author;
    private String image;
    private float price;
    private String categoryID;
    private boolean status;
    private int amount;

    public BookDTO(String bookID, String bookTitle, float price, int amount) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.price = price;
        this.amount = amount;
    }

    public BookDTO(String bookID, float price, int amount) {
        this.bookID = bookID;
        this.price = price;
        this.amount = amount;
    }

    
    public BookDTO(String bookID, String bookTitle, String bookDescription, String author, String image, float price, String categoryID, int amount) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookDescription = bookDescription;
        this.author = author;
        this.image = image;
        this.price = price;
        this.categoryID = categoryID;
        this.amount = amount;
    }

    public BookDTO(String bookID, String categoryName, String bookTitle, String bookDescription, String author, String image, float price, boolean status, int amout) {
        this.bookID = bookID;
        this.categoryName = categoryName;
        this.bookTitle = bookTitle;
        this.bookDescription = bookDescription;
        this.author = author;
        this.image = image;
        this.price = price;
        this.status = status;
        this.amount = amout;
    }

    public BookDTO(String bookID, String bookTitle, String bookDescription, String author, String image, float price, String categoryID, boolean status, int amount) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookDescription = bookDescription;
        this.author = author;
        this.image = image;
        this.price = price;
        this.categoryID = categoryID;
        this.status = status;
        this.amount = amount;
    }



    public String getBookID() {
        return bookID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public boolean isStatus() {
        return status;
    }

    public int getAmount() {
        return amount;
    }

}
