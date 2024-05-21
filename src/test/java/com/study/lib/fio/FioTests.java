package com.study.lib.fio;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FioTests {

//    Absolute Path: Використовуйте, коли вам потрібно точно вказати розташування файлу на вашому комп'ютері.
//    Path From Content Root: Використовуйте для налаштувань проекту і при спільній роботі, щоб шляхи залишалися відносними до структури проекту.
//    Path From Source Root: Використовуйте для доступу до ресурсів з вихідного коду, особливо при написанні тестів.
//    Path From Repository Root: Використовуйте при роботі з системами контролю версій для відносних шляхів в рамках репозиторію.

    private final static String PATH_TO_EXIST_FILE_CSV = "book.csv";
    private final static String PATH_TO_NOT_EXIST_FILE_CSV = "src/test/resources/file1.csv";
    private final static String PATH_TO_EXIST_FILE_JSON = "book.csv";
    private final static String PATH_TO_NOT_EXIST_FILE_JSON = "file.csv";

    boolean searchFileByRepositoryRoot(String path) {
        // read book.csv
        // File: працює з файлами на вашому комп'ютері.
        File f = new File(path);
        if (f.exists() && !f.isDirectory()){
            return true;
        }
        return false;
    }

    boolean searchFileByClassLoader(String path) throws IOException {
        // read book.csv
        // ClassLoader: працює з файлами, які є частиною вашого Java проекту і знаходяться у класоподібному шляху.
        ClassLoader classLoader = getClass().getClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        if (resources.hasMoreElements()){
            return true;
        }
        return false;
    }

     boolean searchJARByByClassLoader(String path) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);

        // the stream holding the file content
        if (inputStream != null) {
           return true;
        }
        return false;
    }

    @Test
    void existFile() { assertTrue(searchFileByRepositoryRoot(PATH_TO_EXIST_FILE_CSV));}

    @Test
    void exist2File() throws IOException { assertTrue(searchFileByClassLoader(PATH_TO_EXIST_FILE_JSON));}

    @Test
    void notExistFile() { assertFalse(searchFileByRepositoryRoot(PATH_TO_NOT_EXIST_FILE_CSV));}

    @Test
    void not2ExistFile() throws IOException { assertFalse(searchFileByClassLoader(PATH_TO_NOT_EXIST_FILE_JSON));}
}
