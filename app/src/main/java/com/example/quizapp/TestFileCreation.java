package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// SOLID
// SRP - klasa ma za zadanie jedynie tworzenie pliku o podanej parametrem nazwie
// OCP - klasa z pytaniami jest otwarta na rozszerzenia i zamknięta na modyfikacje, ponieważ
//       możemy łatwo tworzyć nowe wzorce pliku i dodawać je jako metody

public class TestFileCreation extends AppCompatActivity {
    TestFileCreation(){}

    void createTestFile(File newFile, int numberOfTemplate){
        switch(numberOfTemplate){
            case 0:
                threeQuestionsTemplate(newFile);
                break;
            default:
                threeQuestionsTemplate(newFile);
                break;
        }
    }

    void threeQuestionsTemplate(File newFile){

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

    void createDefaultFile(){
        File file = new File(TestFileCreation.this.getFilesDir().toString() + "/Zestawy/PlikDoTestowania.txt");

        if(!file.exists()) {
            try {
                if (file.createNewFile()) {

                    FileWriter writer = new FileWriter(file);

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
}
