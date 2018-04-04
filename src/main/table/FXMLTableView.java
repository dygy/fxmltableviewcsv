package table;/*
 * Copyright (c) 2012, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


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

public class FXMLTableView extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	primaryStage.setTitle("FXML TableView Example");

    	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        FXMLLoader loader = new FXMLLoader(classloader.getResource("fxml_tableview.fxml"));

        Pane myPane = loader.load();

        ArrayList<Person> l = parseCSV();

        FXMLTableViewController ctr = loader.getController();

        for (Person p: l) {
            ctr.getData().add(p);
        }

    	
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        
          	   	
    	
        primaryStage.show();
        
        
        
    }

    public ArrayList<Person> parseCSV() throws IOException {
        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);

        Reader in = new FileReader("src/main/file.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        System.out.println("here");
        ArrayList<Person> list = new ArrayList<Person>();
        for (CSVRecord record : records) {
            String lastName = record.get(0);
            String firstName = record.get(1);
            String email = record.get(2);
            String status = record.get(3);
            Person p = new Person(record);
            list.add(p);

            System.out.println(lastName);
            System.out.println(firstName);
            System.out.println(email);
            System.out.println(status);
        }

        return list;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
