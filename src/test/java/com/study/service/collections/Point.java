package com.study.service.collections;

import com.study.service.collections.impl.Triangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Point class represents a point in a two-dimensional Cartesian coordinate system.
 * It is the base class for all shapes
 */
public class Point {
    private static final Logger LOGGER = LogManager.getLogger(Triangle.class.getName());
    private double pointX;
    private double pointY;

    public Point() { }

    public Point(double pointX, double pointY){
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public double getPointX(){
        return this.pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY(){
        return this.pointY;
    }

    public void setPointY(double pointY){
        this.pointY = pointY;
    }

    @Override
    public String toString() {
        return "Point{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                '}';
    }

    @Override
    public boolean equals(Object obj){
       if (this == obj){
         return true;
       }
       if (obj == null || getClass() != obj.getClass()){
           return false;
       }
       Point point = (Point)obj;
       return Double.compare(point.pointX, pointX) == 0 && Double.compare(point.pointY, pointY) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash *= 31 + Double.hashCode(pointX);
        hash *= 31 + Double.hashCode(pointY);
        return hash;
    }
}
