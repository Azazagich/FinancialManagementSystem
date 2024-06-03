package com.study.service.collections;

import com.study.domain.figure.Figure;
import com.study.domain.figure.Point;
import com.study.domain.figure.Rectangle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Rectangle class.
 * It contains test cases to verify the functionality of methods in the Rectangle class
 * */
public class RectangleTest {
    private static Point pointA;
    private static Point pointB;
    private static Figure figure;

    /**
     * Allowable discrepancy for floating-point comparisons
     * */
    private static final double DISCREPANCY = 0.1;

    /**
     * This method is executed before each test case to initialize the points for the Rectangle.
     * */
    @BeforeEach
    void initialize() {
        pointA = new Point(4, 7);
        pointB = new Point(9, 14);
    }

    /**
     * Test case to determine the calculation of the perimeter of a Rectangle.
     * */
    @Test
    void  testPerimeterOfRectangle(){
        figure = new Rectangle(pointA, pointB);
        double expectedPerimeter = 24;
        Assertions.assertEquals(expectedPerimeter, figure.calculatePerimeter(), DISCREPANCY);
    }

    /**
     * Test case to determine the calculation of the area of a Rectangle.
     * */
    @Test
    void testAreaOfRectangle(){
        figure = new Rectangle(pointA, pointB);
        double expectedArea = 35;
        Assertions.assertEquals(expectedArea, figure.calculateArea(), DISCREPANCY);
    }

    /**
     * Testing the equals() method for Square objects
     * */
    @Test
    void testEqualsRectangle(){
        // Test for equality of the same object reference
        Rectangle rectangle1 = new Rectangle(pointA, pointB);
        Rectangle rectangle2 = rectangle1;
        Assertions.assertTrue(rectangle1.equals(rectangle2));

        // Test for inequality due to different point coordinates
        // Point(5,7) -> Point(5,5)
        Point pointATest1 = new Point(5, 5);
        Point pointBTest1 = new Point(8, 10);
        figure = new Rectangle(pointATest1, pointBTest1);
        Assertions.assertFalse(rectangle1.equals(figure));

        //TODO
        //Test for equality of different objects with the same coordinates
//        Point pointATest2 = new Point(5, 7);
//        Point pointBTest2 = new Point(8, 10);
//        figure = new Rectangle(pointATest2, pointBTest2);
//        Assertions.assertTrue(rectangle1.equals(figure));

        // Test for inequality with points in reversed order
        rectangle1 = new Rectangle(pointA, pointB);
        rectangle2 = new Rectangle(pointB, pointA);
        Assertions.assertFalse(rectangle1.equals(rectangle2));
    }

    /**
     * Testing the hashCode() method for Square objects
     * */
    @Test
    void testHashRectangle(){
        // Test for hash code equality of the same object reference
        Rectangle rectangle1 = new Rectangle(pointA, pointB);
        Rectangle rectangle2 = rectangle1;
        Assertions.assertTrue(rectangle1.hashCode() == rectangle2.hashCode());

        // Test for different hash codes due to different point coordinates
        Point pointATest1 = new Point(5, 5);
        Point pointBTest1 = new Point(8, 10);
        figure = new Rectangle(pointATest1, pointBTest1);
        Assertions.assertFalse(rectangle1.hashCode() == figure.hashCode());

        //TODO
//        Test for hash code equality of different objects with the same coordinates
//        Point pointATest2 = new Point(5, 7);
//        Point pointBTest2 = new Point(8, 10);
//        figure = new Rectangle(pointATest2, pointBTest2);
//        Assertions.assertTrue(rectangle1.hashCode() == figure.hashCode());

        // Test for hash code equality with points in reversed order
        rectangle1 = new Rectangle(pointA, pointB);
        rectangle2 = new Rectangle(pointB, pointA);
        Assertions.assertTrue(rectangle1.hashCode() == rectangle2.hashCode());
    }
}
