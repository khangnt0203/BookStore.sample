/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ACER
 */
public class OrderDTO implements Serializable{
    private int orderID;
    private Date orderCreateDate;
    private float orderTotalPrice;
    private String customer;

    public OrderDTO(int orderID, Date orderCreateDate, float orderTotalPrice, String customer) {
        this.orderID = orderID;
        this.orderCreateDate = orderCreateDate;
        this.orderTotalPrice = orderTotalPrice;
        this.customer = customer;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    public float getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public String getCustomer() {
        return customer;
    }
    
    
}
