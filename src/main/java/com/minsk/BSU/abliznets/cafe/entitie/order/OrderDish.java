package com.minsk.BSU.abliznets.cafe.entitie.order;

public class OrderDish {
    private static final long serialVersionUID = 1L;

    public static final String ID_COLUMN = "ID";
    public static final String ORDER_ID_COLUMN = "OrderID";
    public static final String DISH_ID_COLUMN = "DishID";
    public static final String SERVINGS_NUMBER_COLUMN = "ServingsNumber";

    private int ID;
    private int orderID;
    private int dishID;
    private int servingsNumber;

    public OrderDish(int orderID, int dishID, int servingsNumber) {
        this.orderID = orderID;
        this.dishID = dishID;
        this.servingsNumber = servingsNumber;
    }

    public OrderDish(int ID, int orderID, int dishID, int servingsNumber) {
        this.ID = ID;
        this.orderID = orderID;
        this.dishID = dishID;
        this.servingsNumber = servingsNumber;
    }

    public int getID() {
        return ID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getDishID() {
        return dishID;
    }

    public int getServingsNumber() {
        return servingsNumber;
    }
}
