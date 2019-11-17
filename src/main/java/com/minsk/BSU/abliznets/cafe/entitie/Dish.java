package com.minsk.BSU.abliznets.cafe.entitie;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ID_COLUMN = "ID";
    public static final String NAME_COLUMN = "Name";
    public static final String DESCRIPTION_COLUMN = "Description";
    public static final String COST_COLUMN = "Cost";
    public static final String IMAGE_HREF_COLUMN = "ImageHref";
    public static final String IS_IN_MENU_COLUMN = "IsInMenu";

    private int ID;
    private String name;
    private String description;
    private BigDecimal cost;

    private String imageHref;

    private boolean isInMenu;

    public Dish(String name, String description, BigDecimal cost, String imageHref, boolean isInMenu) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.imageHref = imageHref;
        this.isInMenu = isInMenu;
    }

    public Dish(int ID, String name, String description, BigDecimal cost, String imageHref, boolean isInMenu) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.imageHref = imageHref;
        this.isInMenu = isInMenu;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public boolean isInMenu() {
        return isInMenu;
    }

    public void setInMenu(boolean inMenu) {
        isInMenu = inMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Dish dish = (Dish) o;
        return ID == dish.ID &&
                isInMenu == dish.isInMenu &&
                name.equals(dish.name) &&
                Objects.equals(description, dish.description) &&
                cost.equals(dish.cost) &&
                Objects.equals(imageHref, dish.imageHref);
    }

    @Override
    public int hashCode() {
        return 31 * ID
                + name.hashCode()
                + (description == null ? 0 : description.hashCode())
                + cost.hashCode()
                + (imageHref == null ? 0 : imageHref.hashCode())
                + (isInMenu ? 0 : 1);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + ID_COLUMN + "=" + ID + " " + NAME_COLUMN + "=" + name
                + " " + COST_COLUMN + "=" + cost + " " + IMAGE_HREF_COLUMN + "=" + imageHref
                + " " + IS_IN_MENU_COLUMN + "=" + isInMenu;
    }
}
