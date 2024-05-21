package com.study.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

/**
 * Utility class for file operations.
 */
public class FileUtils {

    /**
     * Method searchFileByRepositoryRoot search path to our file, if file exist return true else return false.
     * Method using class File for searching
     *
     * @param path
     * @return boolean value(true, false)
     */
    public static boolean searchFileByRepositoryRoot(String path) {
        // read book.csv
        // File: працює з файлами на вашому комп'ютері.
        File f = new File(path);
        if (f.exists() && !f.isDirectory()){
            return true;
        }
        return false;
    }

    /**
     * Method searchFileByRepositoryRoot search path to our file, if file exist return true else return false.
     * Method using class ClassLoader for searching
     *
     * @param path
     * @return boolean value(true, false)
     * @throws IOException
     */
    public static boolean searchFileByClassLoader(String path) throws IOException {
        // read book.csv
        // ClassLoader: працює з файлами, які є частиною вашого Java проекту і знаходяться у класоподібному шляху.
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        if (resources.hasMoreElements()){
            return true;
        }
        return false;
    }

    /**
     * Method searchFileByRepositoryRoot search path to our file, if file exist return true else return false.
     * Method using class ClassLoader for searching
     *
     * @param path
     * @return boolean value(true, false)
     */
    public static boolean searchJARByByClassLoader(String path) {

        // The class loader that loaded the class
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);

        // the stream holding the file content
        if (inputStream != null) {
            return true;
        }
        return false;
    }
}
