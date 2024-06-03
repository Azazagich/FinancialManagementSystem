package com.study.domain.figure;

import com.study.service.collections.Figure;
import com.study.service.collections.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Rectangle class extends the Point class and implements the Figure interface.
 * It represents a rectangle defined by two points (pointA and pointB) and provides methods
 * to calculate its area, perimeter, and other related functionalities.
 * */
public class Rectangle extends Point implements Figure {

    /**
     * Logger for logging information
     * */
    private static final Logger LOGGER = LogManager.getLogger(Rectangle.class.getName());

    /**
     * Constant for the number of sides used in perimeter calculation
     * */
    private static final short RECTANGLE_SIDES = 2;

    private Point pointA;
    private Point pointB;

    public Rectangle(){ }

    public Rectangle(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }
    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    /**
     * Method for calculate the width of the rectangle
     *
     * @return The width of the rectangle.
     * */
    public double getWidth(){
        double width = pointB.getPointX() - pointA.getPointX();
        LOGGER.info("Width of rectangle" + width);
        return width;
    }

    /**
     * Method for calculate the length of the rectangle
     *
     * @return The length of the rectangle.
     * */
    public double getLength(){
        double length = pointB.getPointY() - pointA.getPointY();
        LOGGER.info("Length of rectangle" + length);
        return length;
    }

    /**
     * Method for calculate the area of the rectangle
     *
     * @return The area of the rectangle.
     * */
    @Override
    public double calculateArea() {
        LOGGER.trace("start method calculateArea | rectangle");
        double area = getWidth() * getLength();
        LOGGER.info("Area of rectangle" + area);
        LOGGER.trace("end method calculateArea | rectangle");
        return area;
    }

    /**
     * Method for calculate the perimeter of the rectangle
     *
     * @return The perimeter of the rectangle.
     * */
    @Override
    public double calculatePerimeter() {
        LOGGER.trace("start method calculatePerimeter | rectangle");
        double perimeter = RECTANGLE_SIDES * (getLength() + getWidth());
        LOGGER.info("Perimeter of rectangle" + perimeter);
        LOGGER.trace("end method calculatePerimeter | rectangle");
        return perimeter;
    }

    /**
     * Method which print information about the rectangle
     * */
    @Override
    public void printInfo(){
        LOGGER.trace("Start method printInfo | rectangle");
        LOGGER.info(getClass()::toString);
        LOGGER.trace("End method printInfo | rectangle");
    }

    @Override
    public String toString(){
        return "Rectangle {" +
                "width: " + getWidth() +
                "length: " + getLength() + "}";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()){
            return false;
        }
        Rectangle rectangle = (Rectangle) obj;
        return rectangle.pointA.equals(pointA) && rectangle.pointB.equals(pointB);
    }

    @Override
    public int hashCode(){
        int hash = 17;
        if (pointA != null) {
            hash *= 31 + pointA.hashCode();
        }
        if (pointB != null) {
            hash *= 31 + pointB.hashCode();
        }
        return hash;
    }
}