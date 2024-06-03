package com.study.service.collections.tests;

import com.study.service.collections.Figure;
import com.study.service.collections.impl.Circle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the Circle class.
 * It contains test cases to verify the functionality of methods in the Circle class
 * */
public class CircleTest {
    private static Figure figure;
    public static double radius = 5;

    /**
     * Allowable discrepancy for floating-point comparisons
     * */
    public static final double DISCREPANCY = 0.1;

    /**
     * Test case to determine the calculation of the perimeter of a Circle.
     * */
    @Test
    void  testPerimeterOfCircle(){
        figure = new Circle(radius);
        double expectedPerimeter = 31.42;
        Assertions.assertEquals(expectedPerimeter, figure.calculatePerimeter(), DISCREPANCY);
    }

    /**
     * Test case to determine the calculation of the area of a Circle.
     * */
    @Test
    void testAreaOfCircle(){
        figure = new Circle(radius);
        double expectedArea = 78.54;
        Assertions.assertEquals(expectedArea, figure.calculateArea(), DISCREPANCY);
    }

    /**
     * Testing the equals() method for Circle objects
     * */
    @Test
    void testEqualsSquare(){
        // Verifies if two references to the same object are considered equal.
        // Checks if a Circle object is equal to itself
        Circle circle1 = new Circle(radius);
        Circle circle2 = circle1;
        assertTrue(circle1.equals(circle2));
        assertEquals(circle1, circle2);
        // Tests equality of Circle objects with the same radius.
        figure = new Circle(radius);
        assertTrue(circle1.equals(figure));

        // Tests inequality of Circle objects with different radius.
        circle1 = new Circle(5);
        circle2 = new Circle(8);
        Assertions.assertFalse(circle1.equals(circle2));
    }

    /**
     * Testing the hashCode() method for Circle objects
     * */
    @Test
    void testHashSquare(){
        // Checks if the hash code of a Circle object is consistent.
        Circle circle1 = new Circle(radius);
        Circle circle2 = circle1;
        assertTrue(circle1.hashCode() == circle2.hashCode());

        //Tests the hash codes of Circle objects with the same radius are equal.
        figure = new Circle(radius);
        assertTrue(circle1.hashCode() == figure.hashCode());

        // Tests inequality of Circle objects with different radius.
        circle1 = new Circle(5);
        circle2 = new Circle(8);
        Assertions.assertFalse(circle1.hashCode() == circle2.hashCode());
    }
}
