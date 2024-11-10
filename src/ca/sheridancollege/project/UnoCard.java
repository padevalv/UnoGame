/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author diask
 */

public class UnoCard extends Card {
    private String color;
    private String value;

    public UnoCard(String color, String value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }

    // Getters and setters for color and value
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

