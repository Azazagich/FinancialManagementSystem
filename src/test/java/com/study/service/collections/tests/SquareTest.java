package com.study.service.collections.tests;

import com.study.service.collections.Figure;
import com.study.service.collections.Point;
import com.study.service.collections.impl.Square;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Square class.
 * It contains test cases to verify the functionality of methods in the Square class
 * */
public class SquareTest{
    private static Point pointA;
    private static Point pointB;
    private static Figure figure;

    /**
     * Allowable discrepancy for floating-point comparisons
     * */
    private static final double DISCREPANCY = 0.1;

    /**
     * This method is executed before each test case to initialize the points for the Square.
     * */
    @BeforeEach
    void initialize() {
        pointA = new Point(5, 7);
        pointB = new Point(8, 10);
    }

    /**
     * Test case to determine the calculation of the perimeter of a Square.
     * */
    @Test
    void testPerimeterOfSquare(){
        figure = new Square(pointA, pointB);
        double expectPerimeter = 12;
        Assertions.assertEquals(expectPerimeter, figure.calculatePerimeter(), DISCREPANCY);
    }

    /**
     * Test case to determine the calculation of the area of a Square.
     * */
    @Test
    void testAreaOfSquare(){
        figure = new Square(pointA, pointB);
        double expectArea = 9;
        Assertions.assertEquals(expectArea, figure.calculateArea(), DISCREPANCY);
    }

    /**
     * Testing the equals() method for Square objects
     * */
    @Test
    void testEqualsSquare(){
        // Verifying that they are equal since they represent the same geometric shape
        Square square1 = new Square(pointA, pointB);
        Square square2 = square1;
        Assertions.assertTrue(square1.equals(square2));

        // Verifying that it is not equal to square1
        // Point(5,7) -> Point(5,5)
        Point pointATest1 = new Point(5, 5);
        Point pointBTest1 = new Point(8, 10);
        figure = new Square(pointATest1, pointBTest1);
        Assertions.assertFalse(square1.equals(figure));

        // Verifying that it is equal to square1
        Point pointATest2 = new Point(5, 7);
        Point pointBTest2 = new Point(8, 10);
        figure = new Square(pointATest2, pointBTest2);
        Assertions.assertTrue(square1.equals(figure));

        // Verifying that they are not equal since they represent different geometric shapes
        square1 = new Square(pointA, pointB);
        square2 = new Square(pointB, pointA);
        Assertions.assertFalse(square1.equals(square2));
    }

    /**
     * Testing the hashCode() method for Square objects
     * */
    @Test
    void testHashSquare(){
        // Verifying that their hash codes are equal since they represent the same geometric shape
        Square square1 = new Square(pointA, pointB);
        Square square2 = square1;
        Assertions.assertTrue(square1.hashCode() == square2.hashCode());

        // Verifying that its hash code is different from that of square1
        Point pointATest1 = new Point(5, 5);
        Point pointBTest1 = new Point(8, 10);
        figure = new Square(pointATest1, pointBTest1);
        Assertions.assertFalse(square1.hashCode() == figure.hashCode());

        // Verifying that its hash code is the same as that of square1
        Point pointATest2 = new Point(5, 7);
        Point pointBTest2 = new Point(8, 10);
        figure = new Square(pointATest2, pointBTest2);
        Assertions.assertTrue(square1.hashCode() == figure.hashCode());

        // Verifying that their hash codes are equal since they represent the same geometric shape
        square1 = new Square(pointA, pointB);
        square2 = new Square(pointB, pointA);
        Assertions.assertTrue(square1.hashCode() == square2.hashCode());
    }
}
