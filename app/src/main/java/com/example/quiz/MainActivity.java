package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button trueButton, falseButton;
    ProgressBar progressBar;
    QuestionBank questionBank = new QuestionBank();
    FileStorageManager storageManager = new FileStorageManager();

    int position;
    int progress = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setProgress(0);
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("CurrentIndexPosition");

        } else {
            position = 0;

            updateQuestionFragment(questionBank.listOfQuestions.get(position).getQuestionId(), questionBank.listOfQuestions.get(position).getColorId());
        }
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionBank.listOfQuestions.get(position).isAnswerId()) {
                    score++;
                    Toast.makeText(MainActivity.this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.incorrect_answer, Toast.LENGTH_SHORT).show();
                }
                position++;
                if (position == questionBank.listOfQuestions.size()) {
                    position = 0;
                    showScoreDialog();
                }
                updateQuestionFragment(questionBank.listOfQuestions.get(position).getQuestionId(), questionBank.listOfQuestions.get(position).getColorId());
                progress++;
                progressBar.setProgress(progress);
                progressBar.setMax(10);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!questionBank.listOfQuestions.get(position).isAnswerId()) {
                    score++;
                    Toast.makeText(MainActivity.this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.incorrect_answer, Toast.LENGTH_SHORT).show();
                }
                position++;
                if (position == questionBank.listOfQuestions.size()) {
                    position = 0;
                    showScoreDialog();

                }
                updateQuestionFragment(questionBank.listOfQuestions.get(position).getQuestionId(), questionBank.listOfQuestions.get(position).getColorId());
                progress++;
                progressBar.setProgress(progress);
                progressBar.setMax(10);
            }
        });
    }

    private void updateQuestionFragment(int questionID, int colorID) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.findFragmentById(R.id.frame_layout);
        QuestionFragment questionfragment = QuestionFragment.newInstance(questionID, colorID);
        fragmentManager.beginTransaction().replace(R.id.frame_layout, questionfragment).commit();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CurrentIndexPosition", position);
    }

    public void showScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Score is " + score + " out of 10");
        String dialogString = "" + score;
        builder.setPositiveButton(R.string.saved, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                storageManager.writeResult(dialogString, MainActivity.this);
                progress = 0;
                progressBar.setProgress(0);
                score = 0;
            }
        });
        builder.setNegativeButton(R.string.ignored, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progress = 0;
                progressBar.setProgress(0);
                score = 0;
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuitems, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.average: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                File f = getFileStreamPath("quizScore.txt");
                if (f.length() != 0) {
                    builder.setMessage(storageManager.getTheAverage(this));
                    builder.setPositiveButton(R.string.ok_button, null);
                    builder.create().show();
                    progressBar.setProgress(0);
                } else {
                    builder.setMessage(R.string.previous_results);
                    builder.setPositiveButton(R.string.ok_button, null);
                    builder.create().show();
                    progressBar.setProgress(0);

                }

                break;
            }
            case R.id.reset_results: {
                storageManager.resetAllResults(this);
                Toast.makeText(this, R.string.previous_erased, Toast.LENGTH_SHORT).show();
                break;
            }
//            case R.id.select_questions:{
//                break;
//            }
//            case R.id.add_questions:{
//                break;
//            }
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }

}