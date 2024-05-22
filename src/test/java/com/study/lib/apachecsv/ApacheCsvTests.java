package com.study.lib.apachecsv;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class ApacheCsvTests {
    String[] HEADERS = {"author", "title"};

    private final static String PATH_FILE_CSVBOOK = "src/test/resources/csvbookWithPayloadsAndHeaders.csv";
    private final static String PATH_FILE_CSVBOOK_EMPTY = "src/test/resources/csvbookempty.csv";
    private final static String PATH_FILE_CSVBOOK_PAYLOADS = "src/test/resources/csvbookWithPayloads.csv";
    private final static String PATH_FILE_CSVBOOK_HEADERS = "src/test/resources/csvbookemptyWithHeaders.csv";

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

    @Test
    void successReadCsvHeadersWithoutPayload() throws IOException {
        assertArrayEquals(HEADERS, ReadCsvHeaders(PATH_FILE_CSVBOOK));
    }

    @Test
    void failedReadCsvHeadersWithoutPayload() throws IOException {
        assertNull(ReadCsvHeaders(PATH_FILE_CSVBOOK_EMPTY));
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

    boolean searchHeaders(String path) throws IOException {
        System.out.println("Path to csv: " + path);

        CSVRecord headers;

        try (Reader in = new FileReader(path)) {

            if (new File(path).length() == 0) {
                return false;
            }

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(HEADERS)
                    .setSkipHeaderRecord(false)
                    .build();

            headers = csvFormat.parse(in).getRecords().get(0);
        }
        String[] header = headers.stream().toArray(String[]::new);

        return Arrays.equals(HEADERS, header);

    }

    @Test
    void existHeaders() throws IOException {
       assertTrue(searchHeaders(PATH_FILE_CSVBOOK));
       assertTrue(searchHeaders(PATH_FILE_CSVBOOK_HEADERS));
    }

    @Test
    void notExistHeaders() throws IOException {
        assertFalse(searchHeaders(PATH_FILE_CSVBOOK_EMPTY));
        assertFalse(searchHeaders(PATH_FILE_CSVBOOK_PAYLOADS));
    }

    @Test
    void writeCsvHeadersWithoutPayload() {
        
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


    //TODO
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApacheCsvTests that = (ApacheCsvTests) o;
        return Arrays.equals(HEADERS, that.HEADERS);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(HEADERS);
    }
}
