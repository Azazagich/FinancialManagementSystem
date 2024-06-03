package com.study.domain.figure;

import com.study.domain.figure.Point;

/**
 * The Figure interface represents geometric figures and defines methods for calculating their area and perimeter.
 * It also provides a default method for calculating the distance between two points.
 * */
public interface Figure {
    /**
     * Calculates the area of the geometric figure.
     *
     * @return The area of the geometric figure.
     * */
    double calculateArea();

    /**
     * Calculates the perimeter of the geometric figure.
     *
     * @return The perimeter of the geometric figure.
     * */
    double calculatePerimeter();

    /**
     * Calculates the Euclidean distance between two points.
     *
     * @param pointA | The first point.
     * @param pointB | The second point.
     * @return The distance between the two points.
     * */
    default double calculatePointDistance(Point pointA, Point pointB){
        return Math.sqrt(Math.pow(pointB.getPointX() - pointA.getPointX(), 2) +
                         Math.pow(pointB.getPointY() - pointA.getPointY(), 2));
    }

    /**
     * Prints information about the geometric figure.
     * */
    void printInfo();
}
