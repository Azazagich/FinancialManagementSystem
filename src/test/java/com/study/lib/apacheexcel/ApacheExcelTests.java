package com.study.lib.apacheexcel;

import org.apache.commons.csv.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class ApacheExcelTests {

        boolean ReadCsvHeaders(String path, String ... headers) throws IOException {

//            InputStream is = null;
//            Reader in = null;
//            is = new FileInputStream("src/test/resources/ex.xlsx");
//
//            in = new InputStreamReader(is);
//
//            CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader("EMPLOYEE_ID", "NAME", "DOJ",
//                    "MOBILE_NO").withSkipHeaderRecord(true).withTrim(true));
//
//            List<CSVRecord> records = parser.getRecords();
//
//            for (CSVRecord record : records) {
//
//                System.out.println("Employee Id::" + record.get("EMPLOYEE_ID"));
//
//                System.out.println("Employee Name::" + record.get("NAME"));
//            }
            System.out.println("Path to xlsx: " + path);

            CSVFormat.EXCEL.withHeader("Col1", "Col2", "Col3");
        CSVFormat csvFormat = CSVFormat.EXCEL.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(false)
                .build()
                .withDelimiter(';');


        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(path, true), csvFormat)) {
            csvPrinter.printRecord(headers);
        }
        return true;
        }

    Map<String, String> readCsvPayload(String path) throws IOException{
        return null;
    }

    HashMap<String,String> headerAutoDetection(String path) throws Exception {
        return null;
    }

    boolean ExistHeaders(String path) throws IOException {
        return false;
    }

    @Test
    void successReadCsvHeadersWithoutPayload() throws IOException {
            assertTrue(ReadCsvHeaders("src/test/resources/ex.xls", "country", "name"));
    }

    @Test
    void failedReadCsvHeadersWithoutPayload() throws IOException {

    }

    //TODO
    @Test
    void successReadCsvPayloadWithoutHeaders() throws IOException{

    }

    @Test
    void failedReadCsvPayloadWithoutHeaders() throws  IOException{

    }

    @Test
    void existHeaders() throws IOException {

    }

    @Test
    void notExistHeaders() {

    }

    @Test
    void writeCsvHeadersWithoutPayload() {

    }

    @Test
    void writeCsvPayloadWithoutHeaders() {

    }

    @Test
    void writeToCsvUsingAnotherSeparator() {

    }
}

