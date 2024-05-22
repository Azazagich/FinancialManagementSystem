package com.study.lib.apachecsv;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class ApacheCsvTests {

    //All paths
    private final static String PATH_FILE_CSVBOOK = "src/test/resources/csvbookWithPayloadsAndHeaders.csv";
    private final static String PATH_FILE_CSVBOOK_EMPTY = "src/test/resources/csvbookempty.csv";
    private final static String PATH_FILE_CSVBOOK_PAYLOADS = "src/test/resources/csvbookWithPayloads.csv";
    private final static String PATH_FILE_CSVBOOK_HEADERS = "src/test/resources/csvbookHeaders.csv";
    private final static String PATH_FILE_CSVBOOK_USING_SEPARATE = "src/test/resources/csvUsingSep.csv";

    //
   String[] ReadCsvHeaders(String path, String ... headers) throws IOException {
       System.out.println("Path to csv: " + path);

       CSVRecord header;

       if (new File(path).length() == 0) {
           return null;
       }

       try (Reader in = new FileReader(path)) {

           CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                   .setHeader(headers)
                   .setSkipHeaderRecord(false)
                   .build();

           header = csvFormat.parse(in).getRecords().get(0);
       }
       return header.stream().toArray(String[]::new);
   }

    @Test
    void successReadCsvHeadersWithoutPayload() throws IOException {
        assertArrayEquals(new String[]{"author", "title"}, ReadCsvHeaders(PATH_FILE_CSVBOOK_HEADERS, "author", "title"));
    }

    @Test
    void failedReadCsvHeadersWithoutPayload() throws IOException {
        assertNull(ReadCsvHeaders(PATH_FILE_CSVBOOK_EMPTY, "author", "title"));
    }


    Map<String, String> readCsvPayload(String path, String ... headers) throws IOException{
        System.out.println("Path to csv: " + path);

        Map<String, String> data = new HashMap<>();

        try (Reader in = new FileReader(path)) {

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);

            for (CSVRecord record : records) {
                data.put(record.get("author"), record.get("title"));
            }

            if (new File(path).length() == 0) {
                return null;
            }
        }
        return data;
    }

    @Test
    void successReadCsvPayloadWithoutHeaders() throws IOException{
        HashMap<String, String> payload = (HashMap<String, String>) readCsvPayload(PATH_FILE_CSVBOOK_PAYLOADS, "author", "title");
        assertNotNull(payload);
        assertTrue(payload.size() == 2);
    }

    @Test
    void failedReadCsvPayloadWithoutHeaders() throws  IOException{
        HashMap<String, String> payload = (HashMap<String, String>)readCsvPayload(PATH_FILE_CSVBOOK_EMPTY, "author", "title");
        assertNull(payload);
//        assertTrue(payload.size() == 0);
    }

    boolean searchHeaders(String path, String ... headers) throws IOException {
        System.out.println("Path to csv: " + path);

        CSVRecord header;

        try (Reader in = new FileReader(path)) {

            if (new File(path).length() == 0) {
                return false;
            }

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
                    .setSkipHeaderRecord(false)
                    .build();

            header = csvFormat.parse(in).getRecords().get(0);
        }
        String[] myheader = header.stream().toArray(String[]::new);

        return Arrays.equals(headers, myheader);
    }

    @Test
    void existHeaders() throws IOException {
       assertTrue(searchHeaders(PATH_FILE_CSVBOOK, "author", "title"));
       assertTrue(searchHeaders(PATH_FILE_CSVBOOK_HEADERS, "author", "title"));
    }

    @Test
    void notExistHeaders() throws IOException {
        assertFalse(searchHeaders(PATH_FILE_CSVBOOK_EMPTY, "author", "title"));
        assertFalse(searchHeaders(PATH_FILE_CSVBOOK_PAYLOADS, "author", "title"));
    }

    boolean writeCsvHeaders(String path, String ... headers) throws Exception {
        System.out.println("Path to csv: " + path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(false)
                .build();

        if(searchHeaders(path)){
            throw new Exception("Headers exist");
        }

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, true), csvFormat)) {
            csvPrinter.printRecord(headers);
            return true;
        }
   }

    @Test
    void writeCsvHeadersWithoutPayload() throws Exception {
        assertTrue(writeCsvHeaders(PATH_FILE_CSVBOOK_EMPTY, "title", "book"));
    }


    boolean writeCsvPayload(String path, String ... data) throws IOException{
        System.out.println("Path to csv: " + path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, true), csvFormat)) {
            csvPrinter.printRecord(data);
            return true;
        }
    }

    @Test
    void writeCsvPayloadWithoutHeaders() throws Exception {
        assertTrue(writeCsvPayload(PATH_FILE_CSVBOOK_PAYLOADS, "data1", "data2","data3"));
    }


    boolean writeUsingAnotherSeparator(String path, char separator, String ... data) throws Exception {
        System.out.println("Path to csv: " + path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(separator).builder()
                .setHeader()
                .setSkipHeaderRecord(true)
//                .setRecordSeparator(separator)
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, true), csvFormat)) {
            csvPrinter.printRecord(data);
            return true;
        }
    }

    @Test
    void writeToCsvUsingAnotherSeparator() throws Exception {
        assertTrue(writeUsingAnotherSeparator(PATH_FILE_CSVBOOK_USING_SEPARATE, '*', "ggg","wkd","wdw"));
    }

//    HashMap<String,String> headerAutoDetection(String path) throws Exception {
//        System.out.println("Path to csv: " + path);
//
//        Map<String,String> data = new HashMap<>();
//
//        Reader in = new FileReader(path);
//
//        Iterable<CSVRecord> records = CSVFormat.RFC4180.builder()
//                .setHeader()
//                .setSkipHeaderRecord(true)
//                .build()
//                .parse(in);
//
//        for (CSVRecord record : records) {
//            data.put(record.get("author"),record.get("title"));
//        }
//
//        return (HashMap<String, String>) data;
//    }
}
