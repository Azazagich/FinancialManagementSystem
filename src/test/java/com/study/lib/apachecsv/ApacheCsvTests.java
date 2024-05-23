package com.study.lib.apachecsv;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class ApacheCsvTests {

    // maybe we need to take more clear name? - agree!
    private final int EMPTY_FILE_SIZE = 0;
    private final int HEADERS_INDEX = 0;
    private String[] headersInFile = {"author", "title"};

    //All paths
    private final static String PATH_FILE_CSVBOOK = "src/test/resources/csvbookWithPayloadsAndHeaders.csv";
    private final static String PATH_FILE_CSVBOOK_EMPTY = "src/test/resources/csvbookempty.csv";
    private final static String PATH_FILE_CSVBOOK_PAYLOADS = "src/test/resources/csvbookWithPayloads.csv";
    private final static String PATH_FILE_CSVBOOK_HEADERS = "src/test/resources/csvbookHeaders.csv";
    private final static String PATH_FILE_CSVBOOK_USING_SEPARATE = "src/test/resources/csvUsingSep.csv";
    private final static boolean IS_WRITE_IN_FILE = true;
    private final static boolean APPEND_IN_FILE = true;

    //DONE
    // maybe we need to use camel case? - agree!
   String[] readCsvHeaders(String path, String ... headers) throws IOException {
       System.out.println("Path to csv: " + path);

       CSVRecord header;

       // maybe we need to replace magic number? - agree!
       if (new File(path).length() == EMPTY_FILE_SIZE) {
           return new String[0];
           // maybe we need to use String[] instead null? - agree!
       }

       try (Reader in = new FileReader(path)) {

           CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                   .setHeader(headers)
                   .setSkipHeaderRecord(false)
                   .build();

           // maybe we need to replace magic number? - agree!
           header = csvFormat.parse(in).getRecords().get(HEADERS_INDEX);
       }
       return header.stream().toArray(String[]::new);
   }

    @Test
    void successReadCsvHeadersWithoutPayload() throws IOException {
        assertArrayEquals(new String[]{"author", "title"}, readCsvHeaders(PATH_FILE_CSVBOOK_HEADERS, "author", "title"));
    }

    @Test
    void failedReadCsvHeadersWithoutPayload() throws IOException {
        assertNull(readCsvHeaders(PATH_FILE_CSVBOOK_EMPTY, "author", "title"));
    }

    //DONE
    Map<String, String> readCsvPayload(String path, String ... headers) throws IOException{
        System.out.println("Path to csv: " + path);

        Map<String, String> data = new HashMap<>();


        // maybe we need to move this if on method start? agree!
        // maybe we need to replace magic number? agree!
        if (new File(path).length() == EMPTY_FILE_SIZE) {
            // maybe we need to use `data` instead null? - agree!
            return data;
        }

        try (Reader in = new FileReader(path)) {

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers)
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);

            for (CSVRecord record : records) {
                // maybe we need to replace magic string? - agree! (but I think magic int we need leave here because
                // we can determine more headers and if we wanna replace headers we need change all variable in file,
                // it's a lot of and influence on productivity)
                data.put(record.get(headersInFile[0]), record.get(headersInFile[1]));
            }

            // maybe we need to add catch section? - agree!
        } catch (IOException ex){
            throw new IOException(ex);
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
        assertEquals(payload, new String[0]);
//        assertTrue(payload.size() == 0);
    }

    //DONE
    boolean searchHeaders(String path, String ... headers) throws IOException {
        System.out.println("Path to csv: " + path);
        // IS
        // String[] myheader = readCsvHeaders(path, headers);
        return Arrays.equals(headers,  readCsvHeaders(path, headers));

        // Was

        // maybe we need to call ReadCsvHeaders()? absolutely agree!!!
//        try (Reader in = new FileReader(path)) {

            // maybe we need to move this if on method start?
            // maybe we need to replace magic number?
   //         if (new File(path).length() == 0) {
                // maybe we need to replace magic boolean?
//                return false;
//            }

//            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
//                    .setHeader(headers)
//                    .setSkipHeaderRecord(false)
//                    .build();

            // maybe we need to replace magic number?
            //header = csvFormat.parse(in).getRecords().get(0);
//        }

        //String[] myheader = header.stream().toArray(String[]::new);

//        return Arrays.equals(headers, myheader);
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

    //DONE
    boolean writeCsvHeaders(String path, String ... headers) throws Exception {
        System.out.println("Path to csv: " + path);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(false)
                .build();

        if(searchHeaders(path)){
            throw new IOException("Headers is exist");
        }

        // maybe we need to replace magic boolean? agree!
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, APPEND_IN_FILE), csvFormat)) {
            csvPrinter.printRecord(headers);
            // maybe we need to replace magic boolean? agree!
            return IS_WRITE_IN_FILE;
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

        // maybe we need to replace magic boolean? - agree!
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, APPEND_IN_FILE), csvFormat)) {
            csvPrinter.printRecord(data);
        }catch (IOException ex){
            throw new IOException(ex);
        }
            // maybe we need to replace magic boolean? - agree!
            return IS_WRITE_IN_FILE;

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

        // maybe we need to replace magic boolean? - agree!
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, APPEND_IN_FILE), csvFormat)) {
            csvPrinter.printRecord(data);
            return IS_WRITE_IN_FILE;
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
