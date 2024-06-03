package com.study.domain.figure;

import com.study.service.collections.Figure;
import com.study.service.collections.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Square class extends the Point class and implements the Figure interface.
 * It represents a square defined by two points (pointA and pointB) and provides methods
 * to calculate its area, perimeter, and other related functionalities.
 * */
public class Square extends Point implements Figure {

    /**
     * Logger for logging information
     * */
    private static final Logger LOGGER = LogManager.getLogger(Square.class.getName());

    /**
     * Constant for the number of sides in a square
     * */
    private static final double AMOUNT_OF_SIDE = 4;

    /**
     * Constant for the exponent used in area calculation
     * */
    private static final int SQUARE_EXPONENT = 2;

    private Point pointA;
    private Point pointB;
    public Square(){}

    public Square(Point pointA, Point pointB){
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
     * Method calculate the side length of the square
     *
     * @return The side length of the square.
     * */
    public double getSide(){
        double sideLength = calculatePointDistance(pointA, pointB) / Math.sqrt(2);
        LOGGER.info("Side length of the square: " + sideLength);
        return sideLength;
    }

    /**
     * Method calculate the area of the square
     *
     * @return The area of the square.
     * */
    @Override
    public double calculateArea() {
        LOGGER.trace("Start method calculateArea");
        double area = Math.pow(getSide(), SQUARE_EXPONENT);
        LOGGER.info("Area of the square: " + area);
        LOGGER.trace("End method calculateArea");
        return area;
    }

    /**
     * Method calculate the perimeter of the square
     *
     * @return The perimeter of the square.
     * */
    @Override
    public double calculatePerimeter(){
        LOGGER.trace("Start method calculatePerimeter");
        double perimeter = AMOUNT_OF_SIDE * getSide();
        LOGGER.info("Perimeter of the square: " + perimeter);
        LOGGER.trace("End method calculatePerimeter");
        return perimeter;
    }

    /**
     * Method which print information about the square
     * */
    @Override
    public void printInfo(){
        LOGGER.trace("Start method printInfo");
        LOGGER.info(this::toString);
        LOGGER.trace("End method printInfo");
    }

    @Override
    public String toString(){
        return "Square {" + "side: " + getSide() + "}";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || obj.getClass() != getClass()){
            return false;
        }
        Square square = (Square) obj;
        return square.pointA.equals(pointA) && square.pointB.equals(pointB);
    }

    @Override
    public int hashCode(){
        int hash = 13;
        if (pointA != null){
            hash *= 31 + pointA.hashCode();
        }
        if (pointB != null){
            hash *= 31 + pointB.hashCode();
        }
        return hash;
    }
}
