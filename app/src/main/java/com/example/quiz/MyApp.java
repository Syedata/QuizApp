package com.example.quiz;

import android.app.Application;

public class MyApp extends Application {
    QuestionBank questionBank = new QuestionBank();
    FileStorageManager fileStorageManager = new FileStorageManager();

}
