package table;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.ObservableList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVWork
{
    public ArrayList<Person> parseCSV() throws IOException {
        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);

        Reader in = new FileReader("src/main/file.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        System.out.println("here");
        ArrayList<Person> list = new ArrayList<Person>();
        for (CSVRecord record : records) {
            /*String lastName = record.get(0);
            String firstName = record.get(1);
            String email = record.get(2);
            String status = record.get(3);*/

            Person p = new Person(record);
            list.add(p);

            /*System.out.println(lastName);
            System.out.println(firstName);
            System.out.println(email);
            System.out.println(status);*/
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        CSVWork w = new CSVWork();
        // w.writeCsvFile("file.csv");
    }

    //Delimiter used in CSV file
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    public void writeCsvFile(List<Person> list) throws IOException {

        FileWriter fileWriter = new FileWriter("src/main/file.csv", false);

        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

        //Write a new object to the CSV file
        for (Person p : list) {
            csvFilePrinter.printRecord(p.getRecord());
        }

        // fileWriter.flush();
        fileWriter.close();
        csvFilePrinter.close();

    }
}


