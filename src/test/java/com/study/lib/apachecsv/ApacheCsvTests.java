package com.study.lib.apachecsv;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static org.junit.Assert.*;

public class ApacheCsvTests {

    String[] HEADERS = {"author", "title"};

    private final static String PATH_FILE_CSVBOOK = "src/test/resources/csvbook.csv";
    private final static String PATH_FILE_CSVBOOK_EMPTY = "src/test/resources/csvbookempty";

   String[] ReadCsvHeaders(String path) throws IOException {
       System.out.println("Path to csv: " + path);

       CSVRecord headers;

       try (Reader in = new FileReader(path)) {

           if (new File(path).length() == 0) {
               return null;
           }

           CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                   .setHeader(HEADERS)
                   .setSkipHeaderRecord(false)
                   .build();

           headers = csvFormat.parse(in).getRecords().get(0);
       }
       return headers.stream().toArray(String[]::new);
   }

    Map<String, String> readCsvPayload(String path) throws IOException{
        System.out.println("Path to csv: " + path);

        Map<String, String> data = new HashMap<>();

        try (Reader in = new FileReader(path)) {

            if (new File(path).length() == 0) {
                return null;
            }

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(HEADERS)
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);

            for (CSVRecord record : records) {
                data.put(record.get("author"), record.get("title"));
            }
        }
            return data;
    }

    HashMap<String,String> headerAutoDetection(String path) throws Exception {
       System.out.println("Path to csv: " + path);

       Map<String,String> data = new HashMap<>();

        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build()
                .parse(in);
        for (CSVRecord record : records) {
            data.put(record.get("author"),record.get("title"));
        }
        return (HashMap<String, String>) data;
    }

    @Test
    void successReadCsvHeadersWithoutPayload() throws IOException {
        assertArrayEquals(HEADERS, ReadCsvHeaders(PATH_FILE_CSVBOOK));
    }

    @Test
    void failedReadCsvHeadersWithoutPayload() throws IOException {
        assertNull(ReadCsvHeaders(PATH_FILE_CSVBOOK_EMPTY));
    }

    //TODO
    @Test
    void successReadCsvPayloadWithoutHeaders() throws IOException{
        HashMap<String, String> payload = (HashMap<String, String>) readCsvPayload(PATH_FILE_CSVBOOK);
        assertNotNull(payload);
        assertTrue(payload.size() == 2);
    }

    @Test
    void failedReadCsvPayloadWithoutHeaders() throws  IOException{
        HashMap<String, String> payload = (HashMap<String, String>)readCsvPayload(PATH_FILE_CSVBOOK_EMPTY);
        assertNull(payload);
//        assertTrue(payload.size() == 0);
    }

    @Test
    void existHeaders() {
        // 3
        // read book.csv
    }

    @Test
    void notExistHeaders() {
        // 3
        // read book.csv
    }

    @Test
    void writeCsvHeadersWithoutPayload() {
        // read book.csv
    }

    @Test
    void writeCsvPayloadWithoutHeaders() {
        // read book.csv
    }

    @Test
    void clearCsvPayload() {
        // read book.csv
    }

    @Test
    void clearAllCsv() {
        // read book.csv
    }

    @Test
    void writeToCsvUsingAnotherSeparator() {
        // read book.csv
    }
}
