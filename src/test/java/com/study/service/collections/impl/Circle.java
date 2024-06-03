package com.study.service.collections.impl;

import com.study.service.collections.Figure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Circle class implements the Figure interface.
 * It provides methods to calculate its area, perimeter, and other related functionalities.
 * */
public class Circle implements Figure {

    /**
     * Logger for logging information
     * */
    private static final Logger LOGGER = LogManager.getLogger(Circle.class.getName());

    /**
     * Multiplier for calculating diameter
     * */
    private static final short DIAMETER_MULTIPLIER = 2;

    /**
     * Radius of the circle
     * */
    private double radius;

    public Circle(){ }

    public Circle(double radius){
        this.radius =  radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Method for calculate the area of the circle
     *
     * @return The area of the circle.
     * */
    @Override
    public double calculateArea(){
        LOGGER.trace("Start method calculateArea | circle");
        double area = Math.PI * getRadius() * getRadius();
        LOGGER.info("Calculated area: " + area);
        LOGGER.trace("End method calculateArea | circle");
        return area;
    }

    /**
     * Method for calculate the perimeter of the circle
     *
     * @return The perimeter of the circle.
     * */
    @Override
    public double calculatePerimeter() {
        LOGGER.trace("Start method calculatePerimeter | circle");
        double perimeter = DIAMETER_MULTIPLIER * Math.PI * getRadius();
        LOGGER.info("Calculated perimeter: " + perimeter);
        LOGGER.trace("End method calculatePerimeter | circle");
        return perimeter;
    }

    /**
     * Method which print information about the circle
     * */
    @Override
    public void printInfo(){
        LOGGER.trace("Start method printInfo | circle");
        LOGGER.info(getClass()::toString);
        LOGGER.trace("End method printInfo | circle");
    }

    @Override
    public String toString(){
        return "Circle { " +
                "radius " + getRadius() + "}";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || (obj.getClass() != getClass())){
            return false;
        }
        Circle circle = (Circle) obj;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode(){
        int hash = 17;
            hash *= 31 + Double.hashCode(radius);
        return hash;
    }
}
