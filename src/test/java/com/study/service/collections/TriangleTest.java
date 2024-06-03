package com.study.service.collections;

import com.study.domain.figure.Figure;
import com.study.domain.figure.Point;
import com.study.domain.figure.Triangle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Triangle class.
 * It contains test cases to verify the functionality of methods in the Triangle class
 * */
public class TriangleTest {
    private static Point pointA;
    private static Point pointB;
    private static Point pointC;
    private static Figure figure;

    /**
     * Allowable discrepancy for floating-point comparisons
     * */
    private static final double DISCREPANCY = 0.1;

    /**
     * This method is executed before each test case to initialize the points for the Triangle.
     * */
    @BeforeEach
    void initialize() {
        pointA = new Point(5, 7);
        pointB = new Point(8, 10);
        pointC = new Point(4, 7);
    }

    /**
     * Test case to determine the calculation of the perimeter of a Triangle.
     * */
    @Test
    void testPerimeterOfTriangle(){
        figure = new Triangle(pointA, pointB, pointC);
        double expectPerimeter = 10.24;
        Assertions.assertEquals(expectPerimeter, figure.calculatePerimeter(), DISCREPANCY);
    }

    /**
     * Test case to determine the calculation of the area of a Triangle.
     * */
    @Test
    void testAreaOfTriangle(){

        figure = new Triangle(pointA, pointB, pointC);
        double expectArea = 1.5;
        Assertions.assertEquals(expectArea, figure.calculateArea(), DISCREPANCY);
    }

    /**
     * Testing the equals() method for Triangle objects
     * */
    @Test
    void testEqualsTriangle(){
        // Test for reference equality
        Triangle triangle1 = new Triangle(pointA, pointB, pointC);
        Triangle triangle2 = triangle1;
        Assertions.assertTrue(triangle1.equals(triangle2));

        // Test for inequality due to a change in the coordinates of one point
        // Point(5,7) -> Point(5,5)
        Point pointATest1 = new Point(5, 5);
        Point pointBTest1 = new Point(8, 10);
        Point pointCTest1 = new Point(4,7);
        figure = new Triangle(pointATest1, pointBTest1, pointCTest1);
        Assertions.assertFalse(triangle1.equals(figure));

        // Test for equality of different objects with the same coordinates
        Point pointATest2 = new Point(5, 7);
        Point pointBTest2 = new Point(8, 10);
        Point pointCTest2 = new Point(4,7);
        figure = new Triangle(pointATest2, pointBTest2, pointCTest2);
        Assertions.assertTrue(triangle1.equals(figure));

        // Testing inequality of triangles with different vertex orders
        triangle1 = new Triangle(pointA, pointB, pointC);
        triangle2 = new Triangle(pointC, pointB, pointA);
        Assertions.assertFalse(triangle1.equals(triangle2));
    }

    /**
     * Testing the hashCode() method for Triangle objects
     * */
    @Test
    void testHashTriangle(){
        // Verifying that their hash codes are equal since they represent the same geometric shape
        Triangle triangle1 = new Triangle(pointA, pointB, pointC);
        Triangle triangle2 = triangle1;
        Assertions.assertTrue(triangle1.hashCode() == triangle2.hashCode());

        // Verifying that its hash code is different from that of triangle1
        Point pointATest1 = new Point(5, 5);
        Point pointBTest1 = new Point(8, 10);
        Point pointCTest1 = new Point(4,7);
        figure = new Triangle(pointATest1, pointBTest1, pointCTest1);
        Assertions.assertFalse(triangle1.hashCode() == figure.hashCode());

        // Verifying that its hash code is the same as that of triangle1
        Point pointATest2 = new Point(5, 7);
        Point pointBTest2 = new Point(8, 10);
        Point pointCTest2 = new Point(4,7);
        figure = new Triangle(pointATest2, pointBTest2, pointCTest2);
        Assertions.assertTrue(triangle1.hashCode() == figure.hashCode());

        // Verifying that their hash codes are equal since they represent the same geometric shape
        triangle1 = new Triangle(pointA, pointB, pointC);
        triangle2 = new Triangle(pointC, pointB, pointA);
        Assertions.assertTrue(triangle1.hashCode() == triangle2.hashCode());
    }
}