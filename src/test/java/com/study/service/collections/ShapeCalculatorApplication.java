package com.study.service.collections;

import com.study.service.collections.impl.Circle;
import com.study.service.collections.impl.Rectangle;
import com.study.service.collections.impl.Square;
import com.study.service.collections.impl.Triangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ShapeCalculatorApplication {

    private static final Logger LOGGER = LogManager.getLogger(ShapeCalculatorApplication.class.getName());

    public static void main(String[] args) {
        LOGGER.trace("Start Application");

        Point pointA = new Point(5, 8);
        Point pointB = new Point(8, 9);
        Point pointC = new Point(9, 10);


        Optional<Figure> circle = Optional.of(new Circle(5));
        Optional<Figure> circle2 = Optional.of(new Circle(8));
        Optional<Figure> rectangle = Optional.of(new Rectangle(pointA, pointB));
        Optional<Figure> square = Optional.of(new Square(pointA, pointB));
        Optional<Figure> triangle = Optional.of(new Triangle(pointA, pointB, pointC));
        Optional<Figure> triangle2 = Optional.of(new Triangle(pointA, pointB, pointC));

        List<Optional<Figure>> figures = new ArrayList<>();
        figures.add(circle);
        figures.add(circle2);
        figures.add(rectangle);
        figures.add(square);
        figures.add(triangle);
        figures.add(triangle2);

        CalculatorService calculator = new CalculatorService();

        calculator.calculatePerimeterOfFigures(figures);

        calculator.setSetOfFigures(new HashSet<>());

        for (Optional<Figure> fig : figures) {
            calculator.addElOfFigure(fig);
        }

        //Maps
        LOGGER.debug("\nPerimeters of figures:");
        for (Map.Entry<Figure, Double> figure: calculator.getMapOfPerimeterFigure().entrySet()){
            LOGGER.debug("Figure" + calculator.getMapOfPerimeterFigure().getClass().getSimpleName()
                    +  "Perimeter:" + calculator.getMapOfPerimeterFigure().get(figure.getKey()));
        }

        //Sets
        LOGGER.debug("\nPerimeter of figures:");
        for (Figure figure : calculator.getSetOfFigures()){
            LOGGER.debug("Figure" + figure.getClass().getClass().getSimpleName()
                    +  "Perimeter:" + figure.calculatePerimeter());
        }

        calculator.calculateAreaOfFigures(figures);

        //Maps
        LOGGER.debug("\nAreas of figures:");
        for (Map.Entry<Figure, Double> figure: calculator.getMapOfPerimeterFigure().entrySet()){
            LOGGER.debug("Figure" + calculator.getMapOfPerimeterFigure().getClass().getSimpleName()
                    +  "Perimeter:" + calculator.getMapOfPerimeterFigure().get(figure.getKey()));
        }

        //Sets
        LOGGER.debug("\nAreas of figures:");
        for (Figure figure : calculator.getSetOfFigures()){
            LOGGER.debug("Figure" + figure.getClass().getClass().getSimpleName()
                    +  "Area:" + figure.calculateArea());
        }
    }
}
