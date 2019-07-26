package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class AdditionGameActivity extends AppCompatActivity {

    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button playAgainButton;
    TextView sumTextView;
    TextView pointTextView;
    TextView resultTextView;
    TextView timerTextView;
    TextView commentTextView;
    LinearLayout awardLinearLayout;
    ImageView awardImageView;
    ArrayList<Integer> answers = new ArrayList<>();
    String[] operators = new String[]{"+","-","*"};
    int locationOfCorrectAnswer;
    int score;
    int numberOfQuestions = 0;
    int dataCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_game);

        playAgainButton = findViewById(R.id.playAgainButton);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        pointTextView = findViewById(R.id.pointTextView);
        timerTextView = findViewById(R.id.timerTextView);
        commentTextView = findViewById(R.id.commentTextView);
        awardLinearLayout = findViewById(R.id.awardLinearLayout);
        awardImageView = findViewById(R.id.awardImageView);

        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        generateQuestion();
        playAgain(findViewById(R.id.playAgainButton));
        awardLinearLayout.setVisibility(View.INVISIBLE);

    }

    public void playAgain(final View view){

        score = 0;
        numberOfQuestions = 0;
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        timerTextView.setText(R.string.seconds);
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        awardLinearLayout.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(31000,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(((int)l/1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                button5.setEnabled(false);
                playAgainButton.setVisibility(View.VISIBLE);
                awardLinearLayout.setVisibility(View.VISIBLE);

                if(score <= 4){
                    awardImageView.setImageResource(R.drawable.brainthree);
                    commentTextView.setTextSize(23);
                    commentTextView.setBackgroundResource(R.drawable.resultview);
                    commentTextView.setGravity(Gravity.CENTER);
                    commentTextView.setText("Your Score : " + (score + "/" + numberOfQuestions + "\n\nNot Good!\nYou Need To Work Hard!"));
                }
                else if(score >= 5 && score <= 8){
                    awardImageView.setImageResource(R.drawable.brainseven);
                    commentTextView.setTextSize(23);
                    commentTextView.setBackgroundResource(R.drawable.resultview);
                    commentTextView.setGravity(Gravity.CENTER);
                    commentTextView.setText("Your Score : " + (score + "/" + numberOfQuestions + "\n\nGood!\nYou've Got Some Brain There!"));
                }
                else if(score > 8 && score <= 12){
                    awardImageView.setImageResource(R.drawable.brainten);
                    commentTextView.setTextSize(23);
                    commentTextView.setBackgroundResource(R.drawable.resultview);
                    commentTextView.setGravity(Gravity.CENTER);
                    commentTextView.setText("Your Score : " + (score + "/" + numberOfQuestions + "\n\nExcellent!\nYou've A Sharp Brain!"));
                }
                else if(score > 12 && score <=16){
                    awardImageView.setImageResource(R.drawable.brainnine);
                    commentTextView.setTextSize(23);
                    commentTextView.setBackgroundResource(R.drawable.resultview);
                    commentTextView.setGravity(Gravity.CENTER);
                    commentTextView.setText("Your Score : " + (score + "/" + numberOfQuestions + "\n\nOutstanding!\nYou Are Talented!"));
                }
                else if(score > 16 && score <=20){
                    awardImageView.setImageResource(R.drawable.braintwo);
                    commentTextView.setTextSize(23);
                    commentTextView.setBackgroundResource(R.drawable.resultview);
                    commentTextView.setGravity(Gravity.CENTER);
                    commentTextView.setText("Your Score : " + (score + "/" + numberOfQuestions + "\n\nFabulous!\nYou Play Like A God!"));
                }
                else{
                    commentTextView.setTextSize(23);
                    awardImageView.setImageResource(R.drawable.brainsix);
                    commentTextView.setBackgroundResource(R.drawable.resultview);
                    commentTextView.setGravity(Gravity.CENTER);
                    commentTextView.setText("Your Score : " + (score + "/" + numberOfQuestions + "\n\nVery Fast!\nLooks Like You're Cheating!"));
                }
            }
        }.start();
    }

    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int op = rand.nextInt(3);

            if (operators[op].equals("+")) {
            sumTextView.setText(a + " + " + b);
            }
            else if(operators[op].equals("*")){
                sumTextView.setText(a + " * " + b);
            }
            /*else if(operators[op].equals("/")){
                if((a % b == 0) && (a > b)){
                    sumTextView.setText(a + " / " + b);
                }
                else {
                    generateQuestion();
                }
            }*/
            else{
            sumTextView.setText(a + " - " + b);
            }

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        dataCheck = 0;

        int inCorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {

                if (operators[op].equals("-")) {

                    dataCheck = a - b;
                    answers.add(a - b);

                }
                else if(operators[op].equals("*")){

                    dataCheck = a * b;
                    answers.add(a * b);

                }
                /*else if(operators[op].equals("/")){

                    dataCheck = a / b;
                    answers.add(a / b);

                }*/
                else{

                    dataCheck = a + b;
                    answers.add(a + b);

                }
            }

            else {

                inCorrectAnswer = rand.nextInt(441);

                if (inCorrectAnswer == dataCheck){

                    inCorrectAnswer = rand.nextInt(441);

                }
                answers.add(inCorrectAnswer);
            }
        }

        button2.setText(Integer.toString(answers.get(0)));
        button3.setText(Integer.toString(answers.get(1)));
        button4.setText(Integer.toString(answers.get(2)));
        button5.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++;
            resultTextView.setText("Correct!");

        }
        else {

            resultTextView.setText("Wrong!");

        }
        numberOfQuestions++;
        pointTextView.setText(score + "/" + numberOfQuestions);
        generateQuestion();
    }
}