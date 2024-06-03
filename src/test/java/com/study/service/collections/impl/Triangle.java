package com.study.service.collections.impl;

import com.study.service.collections.Figure;
import com.study.service.collections.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Triangle class represents a triangle defined by three points (pointA, pointB, and pointC).
 * It implements the Figure interface and provides methods to calculate its area, perimeter, and other related functionalities.
 * */
public class Triangle extends Point implements Figure {

    /**
     * Logger for logging information
     * */
    private static final Logger LOGGER = LogManager.getLogger(Triangle.class.getName());

    /**
     * Coefficient used in area calculation
     * */
    private static final double AREA_COEFFICIENT = 0.5;

    private Point pointA;
    private Point pointB;
    private Point pointC;

    public Triangle(){ }

    public Triangle(Point sideA, Point sideB, Point sideC){
        this.pointA = sideA;
        this.pointB = sideB;
        this.pointC = sideC;
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

    public Point getPointC() {
        return pointC;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    /**
     * Method to calculate side A of the triangle
     *
     * @return The length of side A of the triangle.
     * */
    public double getSideA(){
        double sideA = calculatePointDistance(pointA, pointB);
        LOGGER.info("Side A of triangle: " + sideA);
        return sideA;
    }

    /**
     * Method to calculate side B of the triangle
     *
     * @return The length of side B of the triangle.
     * */
    public double getSideB(){
        double sideB = calculatePointDistance(pointB, pointC);
        LOGGER.info("Side B of triangle: " + sideB);
        return sideB;
    }

    /**
     * Method to calculate side C of the triangle
     *
     * @return The length of side C of the triangle.
     * */
    public double getSideC(){
        double sideC = calculatePointDistance(pointA, pointC);
        LOGGER.info("Side C of triangle: " + sideC);
        return sideC;
    }

    /**
     * Method to calculate the perimeter of the triangle
     *
     * @return The perimeter of the triangle.
     * */
    @Override
    public double calculatePerimeter(){
        LOGGER.trace("Start method calculatePerimeter | triangle");
        double perimeter = getSideA() + getSideB() + getSideC();
        LOGGER.info("Perimeter triangle: " + perimeter);
        LOGGER.trace("End method calculatePerimeter | triangle");
        return perimeter;
    }

    /**
     * Method to calculate the area of the triangle
     *
     * @return The area of the triangle.
     * */
    @Override
    public double calculateArea(){
        return AREA_COEFFICIENT * Math.abs(
                pointA.getPointX() * (pointB.getPointY() - pointC.getPointY()) +
                pointB.getPointX() * (pointC.getPointY() - pointA.getPointY()) +
                pointC.getPointX() * (pointA.getPointY() - pointB.getPointY()));
    }

    /**
     * Method to print information about the triangle
     * */
    @Override
    public void printInfo(){
        LOGGER.trace("Start method printInfo | triangle");
        LOGGER.info(getClass()::toString);
        LOGGER.trace("End method printInfo | triangle");
    }

    @Override
    public String toString(){
        return "Triangle {" +
                "sideA" + getSideA() +
                "sideB" + getSideB() +
                "sideC" + getSideC() + "}";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || obj.getClass() != getClass()){
            return false;
        }
        Triangle triangle = (Triangle) obj;
        return triangle.pointA.equals(pointA) &&
                triangle.pointB.equals(pointB) &&
                triangle.pointC.equals(pointC);
    }

    @Override
    public int hashCode(){
        int hash = 13;
        if (pointA != null) {
            hash *= 31 + pointA.hashCode();
        }
        if (pointB != null){
            hash *= 31 + pointB.hashCode();
        }
        if (pointC != null){
            hash *= 31 + pointC.hashCode();
        }
        return hash;
    }

}
