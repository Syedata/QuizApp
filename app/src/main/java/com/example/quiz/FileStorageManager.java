package com.example.quiz;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FileStorageManager {
    static String fileName = "quizScore.txt";

    public void writeResult(String text, Context context) {
        FileOutputStream fos;
        PrintWriter pw = null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            pw = new PrintWriter(fos);
            pw.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.flush();
                pw.close();
            }
        }
    }

    public void resetAllResults(Context context) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(("").getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTheAverage(Context context) {
        int avg = 0;
        int NoOfAttempts = 0;
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String score;
            int sum = 0;
            while ((score = br.readLine()) != null) {
                sum = sum + Integer.parseInt(score);
                NoOfAttempts++;
                avg = sum / NoOfAttempts;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Your correct answers are " + avg + " in " + NoOfAttempts + " attempts.";
    }

}