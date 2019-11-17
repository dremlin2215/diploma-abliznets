package com.minsk.BSU.abliznets.cafe.entitie.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class Order {
    private static final long serialVersionUID = 1L;

    public static final String ID_COLUMN = "ID";
    public static final String RECEIPT_TIME_COLUMN = "ReceiptTime";
    public static final String RESULT_COST_COLUMN = "ResultCost";
    public static final String PAYMENT_METHOD_COLUMN = "PaymentMethod";
    public static final String SCORE_COLUMN = "Score";
    public static final String COMMENT_COLUMN = "Comment";
    public static final String USER_ID_COLUMN = "UserID";

    private int ID;

    private Map<Integer, Integer> chosenDishes;
    private LocalDateTime receiptTime;

    private BigDecimal resultCost;
    private PaymentMethod paymentMethod;

    private Integer score;
    private String comment;

    private int userID;

    public Order(int ID, Map<Integer, Integer> chosenDishes, LocalDateTime receiptTime,
                 BigDecimal resultCost, PaymentMethod paymentMethod,
                 Integer score, String comment, int userID) {
        this.ID = ID;
        this.chosenDishes = chosenDishes;
        this.receiptTime = receiptTime;
        this.resultCost = resultCost;
        this.paymentMethod = paymentMethod;
        this.score = score;
        this.comment = comment;
        this.userID = userID;
    }

    public Order(Map<Integer, Integer> chosenDishes, LocalDateTime receiptTime,
                 BigDecimal resultCost, PaymentMethod paymentMethod, int userID) {
        this.chosenDishes = chosenDishes;
        this.receiptTime = receiptTime;
        this.resultCost = resultCost;
        this.paymentMethod = paymentMethod;
        this.userID = userID;
    }

    public int getID() {
        return ID;
    }

    public Map<Integer, Integer> getChosenDishes() {
        return chosenDishes;
    }

    public LocalDateTime getReceiptTime() {
        return receiptTime;
    }

    public BigDecimal getResultCost() {
        return resultCost;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + ID_COLUMN + "=" + ID + " " + RECEIPT_TIME_COLUMN + "=" + receiptTime
                + " " + RESULT_COST_COLUMN + "=" + resultCost + " " + PAYMENT_METHOD_COLUMN + paymentMethod
                + " " + SCORE_COLUMN + "=" + score + " " + COMMENT_COLUMN + "=" + comment + " "
                + USER_ID_COLUMN + "=" + userID;
    }
}
