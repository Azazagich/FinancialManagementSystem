package com.study.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for working with CSV files using Apache Commons CSV library
 * */
public class ApacheCsvUtils {
    /**
     * int field using in method for checking is file empty or not
     * */
    private final int EMPTY_FILE_SIZE = 0;

    /**
     * int field for determine header index in method
     * */
    private final int HEADERS_INDEX = 0;

    /**
     * String[] field contain our headers
     * */
    private String[] headersInFile = {"author", "title"};

    /**
     * boolean field what calling when our data was success add to file
     * */
    private final static boolean IS_WRITE_IN_FILE = true;

    /**
     * boolean if true, then data will be written to the end of the file rather than the beginning.
     * */
    private final static boolean APPEND_IN_FILE = true;

    /**
     * method which read headers from CSV file and return array of String if method not work threw exception
     *
     * @param headers
     * @param path
     * @return String of headers
     * @throws IOException
     * */
    String[] readCsvHeaders(String path, String ... headers) throws IOException {
        System.out.println("Path to csv: " + path);

        CSVRecord header;

        if (new File(path).length() == EMPTY_FILE_SIZE) {
            return new String[0];
        }

        try (Reader in = new FileReader(path)) {

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
                    .setSkipHeaderRecord(false)
                    .build();

            header = csvFormat.parse(in).getRecords().get(HEADERS_INDEX);
        } catch (IOException ex){
            throw new IOException(ex);
        }

        return header.stream().toArray(String[]::new);
    }

    /**
     * method which read payload from CSV file and return map of data if method not work threw exception
     *
     * @param path
     * @param headers
     * @return Map String key: data in field1, String value: data in field2
     * @throws IOException
     * */
    Map<String, String> readCsvPayload(String path, String ... headers) throws IOException{
        System.out.println("Path to csv: " + path);

        Map<String, String> data = new HashMap<>();

        if (new File(path).length() == EMPTY_FILE_SIZE) {
            return data;
        }

        try (Reader in = new FileReader(path)) {

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);

            for (CSVRecord record : records) {
                data.put(record.get(headersInFile[0]), record.get(headersInFile[1]));
            }
        } catch (IOException ex){
            throw new IOException(ex);
        }

        return data;
    }

    /**
     * method check is headers in file CSV or not if headers is return true, else return false
     *
     * @param path
     * @param headers
     * @return boolean value(true, false)
     * @throws IOException
     * */
    boolean searchHeaders(String path, String ... headers) throws IOException {
        System.out.println("Path to csv: " + path);
        return Arrays.equals(headers,  readCsvHeaders(path, headers));
    }

    /**
     * method write headers in file CSV if data successful add return true else threw IOException
     *
     * @param path
     * @param headers
     * @return  boolean value(true, false)
     * @throws IOException
     * */
    boolean writeCsvHeaders(String path, String ... headers) throws Exception {
        System.out.println("Path to csv: " + path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(false)
                .build();

        if(searchHeaders(path)){
            throw new IOException("Headers is exist");
        }

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, APPEND_IN_FILE), csvFormat)) {
            csvPrinter.printRecord(headers);

            return IS_WRITE_IN_FILE;
        }
    }

    /**
     * method write payload in file CSV if data successful add return true else threw IOException
     *
     * @param path
     * @param data
     * @return  boolean value(true, false)
     * @throws IOException
     * */
    boolean writeCsvPayload(String path, String ... data) throws IOException{
        System.out.println("Path to csv: " + path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, APPEND_IN_FILE), csvFormat)) {
            csvPrinter.printRecord(data);
        }catch (IOException ex){
            throw new IOException(ex);
        }

        return IS_WRITE_IN_FILE;
    }

    /**
     * method write data with another separator in file CSV if data successful add return true else threw IOException
     *
     * @param path
     * @param separator
     * @param data
     * @return  boolean value(true, false)
     * @throws IOException
     * */
    boolean writeUsingAnotherSeparator(String path, char separator, String ... data) throws Exception {
        System.out.println("Path to csv: " + path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(separator).builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, APPEND_IN_FILE), csvFormat)) {
            csvPrinter.printRecord(data);
        } catch (IOException ex){
            throw new IOException(ex);
        }
        
        return IS_WRITE_IN_FILE;
    }

}
