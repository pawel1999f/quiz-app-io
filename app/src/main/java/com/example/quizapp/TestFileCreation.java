package com.example.quizapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestFileCreation {
    TestFileCreation(){}

    void createTestFile(File newFile){

        try {
            if(newFile.createNewFile()) {

                FileWriter writer = new FileWriter(newFile);

                writer.append("Treść pierwszego pytania").append('\n');
                writer.append("Treść odpowiedzi A").append('\n');
                writer.append("Treść odpowiedzi B").append('\n');
                writer.append("Treść odpowiedzi C").append('\n');
                writer.append("Treść odpowiedzi D").append('\n');
                writer.append("1").append('\n');

                writer.append("Treść drugiego pytania").append('\n');
                writer.append("Treść odpowiedzi A").append('\n');
                writer.append("Treść odpowiedzi B").append('\n');
                writer.append("Treść odpowiedzi C").append('\n');
                writer.append("Treść odpowiedzi D").append('\n');
                writer.append("0").append('\n');

                writer.append("Treść trzeciego pytania").append('\n');
                writer.append("Treść odpowiedzi A").append('\n');
                writer.append("Treść odpowiedzi B").append('\n');
                writer.append("Treść odpowiedzi C").append('\n');
                writer.append("Treść odpowiedzi D").append('\n');
                writer.append("3");

                writer.flush();
                writer.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
