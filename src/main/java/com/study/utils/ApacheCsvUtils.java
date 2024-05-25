package com.study.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Utility class for working with CSV files using Apache Commons CSV library
 * */
public class ApacheCsvUtils {

    /**
     * Logger instance for tracing errors and exceptions in the ApacheCsvUtils class.
     * */
    private static final Logger LOGGER = LogManager.getLogger(ApacheCsvUtils.class.getName());

    /**
     * The size of an empty file.
     * This constant represents the size in bytes for a file that has no content.
     * */
    private static final int EMPTY_FILE_SIZE = 0;

    /**
     * The index used to determine the position of headers in a method.
     * This constant represents the position of the headers in a list or array.
     * */
    private static final int HEADERS_INDEX = 0;

    /**
     * The index of the first column in a CSV file.
     * This constant represents the zero-based position of the first column.
     * */
    private final static int INDEX_OF_FIRST_COLUMN = 0;

    /**
     * The index of the second column in a CSV file.
     * This constant represents the zero-based position of the second column.
     * */
    private final static int INDEX_OF_SECOND_COLUMN = 1;

    /**
     * An empty array of strings.
     * This constant represents an empty string array, typically used to default
     * empty array when needed.
     * */
    private final static String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     *  An array of strings containing the headers for a file.
     *  This constant represents the headers used in the file, for define the columns "author"
     *  and "title" in CSV.
     * */
    private final static String[] HEADERS_IN_FILE = {"author", "title"};

    /**
     * A boolean indicating if the data was successfully added to the file.
     * This constant represents the state of a successful write operation.
     * */
    private final static boolean SUCCESSFUL_WRITE_IN_FILE = true;

    /**
     * A boolean indicating if the data wasn't add or unsuccessful added to the file.
     * This constant represents the state of a failed write operation.
     * */
    private final static boolean FAIL_WRITE_IN_FILE = false;

    /**
     * A boolean indicating whether data should be written to the end of the file.
     * If true, data will be appended to the end of the file rather than overwriting its beginning.
     * */
    private final static boolean APPEND_IN_FILE = true;

    /**
     * Reads headers from a CSV file and returns an array of strings.
     * If the method encounters an exception, it throws IOException.
     *
     * @param headers | All header strings which contains in our csv file
     * @param path | Path to our csv file
     * @return Array strings from headers
     * @throws IOException | If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     * @throws FileNotFoundException | If the named file does not exist, is a directory rather than a regular file,
     * or for some other reason cannot be opened for reading.
     * */
   public static String[] readCsvHeaders(String path, String ... headers) throws IOException {
       LOGGER.trace("Start method readCsvHeaders");
       LOGGER.info("Path to csv: {}", path);

        CSVRecord header;

        if (new File(path).length() == EMPTY_FILE_SIZE) {
            LOGGER.error("File doesn't contain any data");
            return EMPTY_STRING_ARRAY;
        }

        try (Reader in = new FileReader(path)) {

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
                    .setSkipHeaderRecord(false)
                    .build();

            header = csvFormat.parse(in).getRecords().get(HEADERS_INDEX);
        } catch (IOException exception){
            LOGGER.fatal("Error reading CSV file: ", exception.getMessage(), exception);
            throw new IOException(exception.getMessage(), exception);
        }
            LOGGER.trace("End method readCsvHeaders");
        return header.stream().toArray(String[]::new);
   }

    /**
     * Reads payload data from a CSV file and returns a map of data.
     *
     * @param headers | All header strings which contains in our csv file
     * @param path | Path to our csv file
     * @return Map String key: data in field1, String value: data in field2
     * @throws IOException | If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     * @throws FileNotFoundException | If the named file does not exist, is a directory rather than a regular file,
     * or for some other reason cannot be opened for reading.
     * */
    public static Map<String, String> readCsvPayload(String path, String ... headers) throws IOException{
        LOGGER.trace("Start method readCsvPayload");
        LOGGER.info("Path to csv: {}",path);

        Map<String, String> data = new HashMap<>();

        if (new File(path).length() == EMPTY_FILE_SIZE) {
            LOGGER.error("File not contain any data");
            return data;
        }

        try (Reader in = new FileReader(path)) {

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);

            for (CSVRecord record : records) {
                data.put(record.get(HEADERS_IN_FILE[INDEX_OF_FIRST_COLUMN]),
                        record.get(HEADERS_IN_FILE[INDEX_OF_SECOND_COLUMN]));
            }
        } catch (IOException exception){
            LOGGER.fatal("Error reading CSV file: ", exception.getMessage(), exception);
            throw new IOException(exception);
        }
        LOGGER.trace("End method readCsvPayload");
        return data;
    }

    /**
     * Checks if the headers are present in a CSV file.
     * Returns true if the headers are found, otherwise false.
     *
     * @param headers | All header strings which contains in our csv file
     * @param path | Path to our csv file
     * @return boolean value(true, false)
     * @throws IOException | if the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     * @throws FileNotFoundException | if the named file does not exist, is a directory rather than a regular file,
     * or for some other reason cannot be opened for reading.
     * */
    public static boolean searchHeaders(String path, String ... headers) throws IOException {
        LOGGER.trace("Start method searchHeaders");
        LOGGER.info("Path to csv: {}", path);
        LOGGER.trace("End method searchHeaders");
        return Arrays.equals(headers,  readCsvHeaders(path, headers));
    }

    /**
     * Writes headers to a CSV file. Returns true if the operation is successful, otherwise throws an IOException.
     *
     * @param headers | All header strings which contains in our csv file
     * @param path | Path to our csv file
     * @return boolean value(true, false)
     * @throws IOException | if the named file exists but is a directory rather than a regular file,
     *  does not exist but cannot be created, or cannot be opened for any other reason
     * @throws FileNotFoundException | if the named file does not exist, is a directory rather than a regular file,
     *  or for some other reason cannot be opened for reading.
     * */
    public static boolean writeCsvHeaders(String path, String ... headers) throws Exception {
        LOGGER.trace("Start method writeCsvHeaders");
        LOGGER.info("Path to csv: {}", path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(false)
                .build();

        if(searchHeaders(path)){
            LOGGER.error("File not contain any data");
            return FAIL_WRITE_IN_FILE;
        }

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, APPEND_IN_FILE), csvFormat)) {
            csvPrinter.printRecord(headers);
        } catch(Exception exception) {
            LOGGER.fatal("Error reading CSV file: ", exception.getMessage(), exception);
            throw new IOException(exception);
        }
        LOGGER.trace("End method writeCsvHeaders");
        return SUCCESSFUL_WRITE_IN_FILE;
    }

    /**
     * Writes payload data to a CSV file. Returns true if the operation is successful, otherwise throws an IOException.
     *
     * @param data | Our data for which will add to file
     * @param path | Path to our csv file
     * @return boolean value(true, false)
     * @throws IOException | if the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     * @throws FileNotFoundException | if the named file does not exist, is a directory rather than a regular file,
     * or for some other reason cannot be opened for reading.
     * */
    public static boolean writeCsvPayload(String path, String ... data) throws IOException{
        LOGGER.trace("Start method writeCsvPayload");
        LOGGER.info("Path to csv: {}", path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, APPEND_IN_FILE), csvFormat)) {
            csvPrinter.printRecord(data);
        }catch (IOException exception){
            LOGGER.fatal("Error reading CSV file: ", exception.getMessage(), exception);
            throw new IOException(exception);
        }
        LOGGER.trace("End method writeCsvPayload");
        return SUCCESSFUL_WRITE_IN_FILE;
    }

    /**
     * Writes data with another separator in file CSV if data successful add return true else threw IOException
     *
     * @param path path to our csv file
     * @param separator for separate our data in csv file
     * @param data  data which will add to csv file
     * @return  boolean value(true, false)
     * @throws IOException | if the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     * @throws FileNotFoundException | if the named file does not exist, is a directory rather than a regular file,
     * or for some other reason cannot be opened for reading.
     * */
    public static boolean writeUsingAnotherSeparator(String path, char separator, String ... data) throws Exception {
        LOGGER.trace("Start method writeUsingAnotherSeparator");
        LOGGER.info("Path to csv: {}", path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(separator).builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, APPEND_IN_FILE), csvFormat)) {
            csvPrinter.printRecord(data);
        } catch (IOException ex){
            LOGGER.fatal("Error reading CSV file: ", ex.getMessage(), ex);
            throw new IOException(ex);
        }
        LOGGER.trace("End method writeUsingAnotherSeparator");
        return SUCCESSFUL_WRITE_IN_FILE;
    }
}

