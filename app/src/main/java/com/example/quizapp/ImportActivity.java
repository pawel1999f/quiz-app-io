package com.example.quizapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

// SOLID
// SRP - klasa implementuje jedno Activity
// OCP - metody odpowiedzialne są za jedną, prostą czynność

public class ImportActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    private static final int MY_REQUEST_CODE_PERMISSION = 1000;


    private EditText editTextPath;

    private EditText newFileNameText;

    private Uri selectedFileUri = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        this.editTextPath = (EditText) findViewById(R.id.filePathText);
        Button buttonBrowse = (Button) findViewById(R.id.fileBrowseButton);

        this.newFileNameText = (EditText) findViewById(R.id.fileNameText);
        Button buttonImport = (Button) findViewById(R.id.fileImportButton);

        Button buttonReturn = (Button) findViewById(R.id.importActivityReturnButton);

        buttonBrowse.setOnClickListener(view -> askPermissionAndBrowseFiles());
        buttonImport.setOnClickListener(view -> tryImportingFile());

        buttonReturn.setOnClickListener(view -> returnToMainMenu());

    }

    public void setSelectedFileUri(Uri u){
        selectedFileUri = u;
    }

    private void returnToMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void tryImportingFile() {
        if (selectedFileUri != null) {
            String newFileName = newFileNameText.getText().toString();

            if (!newFileName.isEmpty()) {
                if( newFileName.matches("^[a-zA-Z0-9]*$") ){

                    File dir = new File(ImportActivity.this.getFilesDir(), "Sets");

                    if (!dir.exists()) {
                        dir.mkdir();
                    }

                    File newFile = new File(dir, newFileName + ".txt");
                    try {
                        if(newFile.createNewFile()) {
                            if(copyFile(newFile)){
                                Toast.makeText(ImportActivity.this, "Successfully created new file", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ImportActivity.this, "Error copying contents of the file", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ImportActivity.this, "Error. The filename already exists", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        Toast.makeText(ImportActivity.this, "Error: " + e, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                } else{
                    Toast.makeText(ImportActivity.this, "Error. \"" + newFileName + "\" is a wrong filename. Please use only alphanumeric name with no spaces", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ImportActivity.this, "Error. You didn't choose a name", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ImportActivity.this, "Error. You didn't select a file", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean copyFile(File dst){
        boolean success = false;

        Uri src = selectedFileUri;

        try {
            InputStream inputStream = getContentResolver().openInputStream(src);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            FileWriter writer = new FileWriter(dst);

            String line;

            writer.append(br.readLine());

            while ((line = br.readLine()) != null)
                writer.append("\n").append(line);

            writer.flush();
            writer.close();

            br.close();

            success = true;
            //Toast.makeText(getBaseContext(), "File saved successfully! Number of files: "
            //        + dir.listFiles().length, Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }

    private void askPermissionAndBrowseFiles() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_REQUEST_CODE_PERMISSION);
            return;
        }
        this.browseFiles();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ImportActivity.this, "Permission granted!", Toast.LENGTH_SHORT).show();
                this.browseFiles();
            } else {
                Toast.makeText(ImportActivity.this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void browseFiles() {
        // Requires Kitkat (API 19)
        Intent chooseFileIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        chooseFileIntent.addCategory(Intent.CATEGORY_OPENABLE);

        chooseFileIntent.setType("text/plain");

        chooseFileIntent = Intent.createChooser(chooseFileIntent, "Choose a file");
        startActivityForResult(chooseFileIntent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {

                Uri fileUri = data.getData();
                String fileName = "";

                // Trying to get the file name
                try {
                    fileName = fileUri.getPath();
                    fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
                } catch (Exception e) {
                    Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
                }

                // If the formatting is correct, then:
                if(isCorrectFormatting(fileUri)) {
                    // Change the name of the file
                    this.editTextPath.setText(fileName);

                    // and set the correct Uri
                    this.selectedFileUri = fileUri;

                }else{
                    this.selectedFileUri = null;
                }
            }
        }

        // Is required?
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isCorrectFormatting(Uri uri){
        boolean isCorrect = false;

        try {
            int counter = 0;

            try {

                InputStream inputStream = getContentResolver().openInputStream(uri);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = br.readLine()) != null) {
                    if ((counter + 1) % 6 == 0 && !line.matches("[0-3]")) {
                        counter = 0;
                        break;
                    }
                    counter++;
                }

                br.close();

            } catch (IOException e) {
                Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            if(counter > 10000)
                Toast.makeText(this, "Error. The file is too long", Toast.LENGTH_SHORT).show();
            else if(counter!=0 && counter%6==0)
                isCorrect = true;
            else
                Toast.makeText(this, "Error. The file has wrong formatting", Toast.LENGTH_SHORT).show();


        } catch (Exception e){
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return isCorrect;
    }

    public File getSetsDir(){
        return new File(this.getFilesDir(), "Sets");
    }

    public int getNumberOfSets(){
        File dir = new File(ImportActivity.this.getFilesDir(), "Sets");

        if(!dir.exists())
            dir.mkdir();

        return Objects.requireNonNull(dir.listFiles()).length;
    }

    // Template for testing, not used
    public void writeFileOnInternalStorage(String sFileName, String sBody){
        File dir = new File(ImportActivity.this.getFilesDir(), "Sets");

        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            File gpxfile = new File(dir, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();

            //testing
            //readFileFromStorage(gpxfile);

            Toast.makeText(getBaseContext(), "File saved successfully! Number of files: "
                            + dir.listFiles().length, Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Template for testing, not used
    public void readFileFromStorage(File gpxfile){

        try {

            StringBuilder text = new StringBuilder();
            try {

                BufferedReader br = new BufferedReader(new FileReader(gpxfile));
                String line;

                while ((line = br.readLine()) != null) {

                    Toast.makeText(ImportActivity.this, line, Toast.LENGTH_SHORT).show();
                    text.append(line).append("\n");

                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Template for testing, not used
    public void readFileFromStorage(Uri uri){

        try {

            StringBuilder text = new StringBuilder();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = br.readLine()) != null) {

                    Toast.makeText(ImportActivity.this, line, Toast.LENGTH_SHORT).show();
                    text.append(line).append("\n");
                }
                br.close();
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e){
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}