package com.example.trainyourbrain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button go, playAgainButton, button0, button1, button2, button3;

    TextView scoreText, question, counter, ans;

    int locationOfCorrectAnswer, score=0, numberOfQuestionsAsked =0;

    ArrayList<Integer> answer = new ArrayList<Integer>();

    public void playAgain(View view){

        button0.setEnabled(true);

        button1.setEnabled(true);

        button2.setEnabled(true);

        button3.setEnabled(true);

        score=0;

        numberOfQuestionsAsked=0;

        counter.setText("30");

        scoreText.setText(score+"/"+numberOfQuestionsAsked);

        makeQuestion();

        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                counter.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {

                ans.setText("Done");

                playAgainButton.setVisibility(View.VISIBLE);

                button0.setEnabled(false);

                button1.setEnabled(false);

                button2.setEnabled(false);

                button3.setEnabled(false);

            }
        }.start();

        ans.setText("");

    }

    public void makeQuestion(){

        Random random = new Random();

        int firstNumber = random.nextInt(21);

        int secondNumber = random.nextInt(21);

        question.setText(firstNumber+" + "+secondNumber);

        locationOfCorrectAnswer = random.nextInt(4);

        answer.clear();

        for(int i=0;i<4;i++){

            if(i==locationOfCorrectAnswer){

                answer.add(firstNumber+secondNumber);

            } else {

                int wrongAnswer = random.nextInt(41);

                while(wrongAnswer==firstNumber+secondNumber){

                    wrongAnswer = random.nextInt(41);

                }

                answer.add(wrongAnswer);

            }

        }

        button0.setText(Integer.toString(answer.get(0)));

        button1.setText(Integer.toString(answer.get(1)));

        button2.setText(Integer.toString(answer.get(2)));

        button3.setText(Integer.toString(answer.get(3)));

    }

    public void chooseAnswer(View view){

        if(Integer.parseInt(view.getTag().toString())==locationOfCorrectAnswer){

            ans.setText("Correct!!!");

            score++;

        } else {

            ans.setText("Oops, Wrong :/");

        }

        numberOfQuestionsAsked++;

        scoreText.setText(score+"/"+numberOfQuestionsAsked);

        makeQuestion();

    }

    public void goClicked(View view){

        go.setVisibility(View.INVISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        counter.setVisibility(View.VISIBLE);
        ans.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.textViewAns));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = (Button) findViewById(R.id.button);

        button0 = (Button) findViewById(R.id.button0);

        button1 = (Button) findViewById(R.id.button1);

        button2 = (Button) findViewById(R.id.button2);

        button3 = (Button) findViewById(R.id.button3);

        scoreText = (TextView) findViewById(R.id.textViewScore);

        question = (TextView) findViewById(R.id.textViewQuestion);

        counter = (TextView) findViewById(R.id.textViewCounter);

        ans = (TextView) findViewById(R.id.textViewAns);

        playAgainButton = (Button) findViewById(R.id.buttonPlayAgain);

    }
}