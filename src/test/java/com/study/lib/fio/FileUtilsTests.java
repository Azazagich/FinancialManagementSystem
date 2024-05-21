package com.study.lib.fio;

import com.study.utils.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @see FileUtils
 */
public class FileUtilsTests {

//    Absolute Path: Використовуйте, коли вам потрібно точно вказати розташування файлу на вашому комп'ютері.
//    Path From Content Root: Використовуйте для налаштувань проекту і при спільній роботі, щоб шляхи залишалися відносними до структури проекту.
//    Path From Source Root: Використовуйте для доступу до ресурсів з вихідного коду, особливо при написанні тестів.
//    Path From Repository Root: Використовуйте при роботі з системами контролю версій для відносних шляхів в рамках репозиторію.

    private final static String PATH_TO_EXIST_FILE_CSV = "src/test/resources/book.csv";
    private final static String PATH_TO_NOT_EXIST_FILE_CSV = "src/test/resources/file1.csv";
    private final static String PATH_TO_EXIST_FILE_JSON = "book.csv";
    private final static String PATH_TO_NOT_EXIST_FILE_JSON = "file.csv";

    @Test
    void existFile() { assertTrue(FileUtils.searchFileByRepositoryRoot(PATH_TO_EXIST_FILE_CSV));}

    @Test
    void exist2File() throws IOException { assertTrue(FileUtils.searchFileByClassLoader(PATH_TO_EXIST_FILE_JSON));}

    @Test
    void notExistFile() { assertFalse(FileUtils.searchFileByRepositoryRoot(PATH_TO_NOT_EXIST_FILE_CSV));}

    @Test
    void not2ExistFile() throws IOException { assertFalse(FileUtils.searchFileByClassLoader(PATH_TO_NOT_EXIST_FILE_JSON));}
}
