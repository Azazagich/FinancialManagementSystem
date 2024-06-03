package com.study.service.figure;

import com.study.domain.figure.Figure;
import com.study.domain.figure.Triangle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * The CalculatorService class provides methods to calculate and
 * manage the perimeter and area of geometric figures.
 * */
public class CashFigureCalculateService {

    /**
     * Logger for logging information
     * */
    private static final Logger LOGGER = LogManager.getLogger(Triangle.class.getName());

    /**
     * HashMap to store figures and their perimeters
     * */
    private HashMap<Figure, Double> mapOfPerimeterFigure = new HashMap<>();

    /**
     * HashMap to store figures and their areas
     * */
    private HashMap<Figure, Double> mapOfAreaFigure = new HashMap<>();

    /**
     * HashSet to store unique figures
     * */
    private HashSet<Figure> setOfFigures = new HashSet<>();

    public HashMap<Figure, Double> getMapOfPerimeterFigure(){
        return mapOfPerimeterFigure;
    }

    public void setMapOfPerimeterFigure(HashMap<Figure, Double> mapOfPerimeterFigure){
        this.mapOfPerimeterFigure = mapOfPerimeterFigure;
    }

    public HashMap<Figure, Double> getMapOfAreaFigure() {
        return mapOfAreaFigure;
    }

    public void setMapOfAreaFigure(HashMap<Figure, Double> mapOfAreaFigure) {
        this.mapOfAreaFigure = mapOfAreaFigure;
    }

    public HashSet<Figure> getSetOfFigures() {
        return setOfFigures;
    }

    public void setSetOfFigures(HashSet<Figure> setOfFigures) {
        this.setOfFigures = setOfFigures;
    }

    /**
     * Method to calculate the perimeter of figures and store in a HashMap
     * */
    public void calculatePerimeterOfFigures(List<Optional<Figure>> figures){
        LOGGER.trace("Start method calculatePerimeterOfFigures");
        for (Optional<Figure> figure : figures){
            if (figure.isPresent()){
                mapOfPerimeterFigure.put(figure.get(), figure.get().calculatePerimeter());
            }
        }
        LOGGER.trace("End method calculatePerimeterOfFigures successfully.");
    }

    /**
     * Method to calculate the area of figures and store in a HashMap
     * */
    public void calculateAreaOfFigures(List<Optional<Figure>> figures){
        LOGGER.trace("Start method calculateAreaOfFigures");
        for (Optional<Figure> figure : figures) {
            if (figure.isPresent()){
                mapOfAreaFigure.put(figure.get(), figure.get().calculateArea());
            }
        }
        LOGGER.trace("End method calculateAreaOfFigures successfully.");
    }

    /**
     * Adds the specified figure and its perimeter to the HashMap.
     *
     * @param figure   | An optional containing the figure to add.
     * @param perimeter | An optional containing the perimeter associated with the figure.
     * @return boolean | true if the figure and its perimeter were successfully added, otherwise false.
     * */
    public boolean addElOfFigureAndPerimeter(Optional<Figure> figure, Optional<Double> perimeter){
        if (figure.isPresent() && perimeter.isPresent()){
            mapOfPerimeterFigure.put(figure.get(), perimeter.get());
            return true;
        }
        LOGGER.warn("Either figure or perimeter is not present");
        return false;
    }

    /**
     * Removes the specified figure and its perimeter from the HashMap.
     *
     * @param figure    An optional containing the figure to remove.
     * @param perimeter An optional containing the perimeter associated with the figure.
     * @return true if the figure and its perimeter were successfully removed, otherwise false.
     */
    public boolean removeElFigureAndPerimeter(Optional<Figure> figure, Optional<Double> perimeter){
        if (figure.isPresent() && perimeter.isPresent()) {
            mapOfPerimeterFigure.remove(figure.get(), perimeter.get());
            return true;
        }
        LOGGER.warn("Either figure or perimeter is not present");
        return false;
    }

    /**
     * Calculates and logs the perimeter of all figures in the set of figures.
     * */
    public void calculatePerimeterOfFigures(){
        LOGGER.trace("Start method calculatePerimeterOfFigures");
        for (Figure figure : getSetOfFigures()){
            LOGGER.info("Figure:" + figure.getClass().getSimpleName()
                    + "Perimeter" + figure.calculatePerimeter());
        }
        LOGGER.trace("End method calculatePerimeterOfFigures successfully.");
    }

    /**
     * Calculates and logs the area of all figures in the set of figures.
     * */
    public void calculateAreaOfFigures(){
        LOGGER.trace("Start method calculatePerimeterOfFigures");
        for (Figure figure : getSetOfFigures()){
            LOGGER.info("Figure:" + figure.getClass().getSimpleName()
                    + "Perimeter" + figure.calculateArea());
        }
        LOGGER.trace("End method calculatePerimeterOfFigures successfully.");
    }

    /**
     * Adds the specified figure to the set of figures.
     * @param figure | The figure to add.
     * @return boolean | true if the figure was successfully added, otherwise false.
     * */
    public boolean addElOfFigure(Optional<Figure> figure){
        if (figure.isPresent()){
            setOfFigures.add(figure.get());
            return true;
        }
        LOGGER.warn("Figure is not present");
        return false;
    }

    /**
     * Removes the specified figure from the set of figures.
     * @param figure | The figure to remove.
     * @return boolean | true if the figure was successfully removed, otherwise false.
     * */
    public boolean removeElOfFigure(Optional<Figure> figure){
        if (figure.isPresent()){
            setOfFigures.remove(figure.get());
            return true;
        }
        LOGGER.warn("Figure is not present");
        return false;
    }
}
