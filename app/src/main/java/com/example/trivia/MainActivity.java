package com.example.trivia;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trivia.Data.AnswerListAsyncResponse;
import com.example.trivia.Data.QuestionBank;
import com.example.trivia.Model.Question;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;
    private TextView questioncount;
    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private int questionNo = 0;
//    private TextView score;
//    private TextView highestScore;
//    private int points=0;
    private List<Question> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.imageButton_next);
        prevButton = findViewById(R.id.imageButton_prev);
        trueButton = findViewById(R.id.button_true);
        falseButton = findViewById(R.id.button_false);
        questioncount = findViewById(R.id.textView_qno);
        questionTextView = findViewById(R.id.textView_question);
//        score=findViewById(R.id.score_textView);
//        highestScore=findViewById(R.id.hs_textView);

        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);

        questionList = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                questionTextView.setText(questionArrayList.get(questionNo).getAnswer());
                questioncount.setText(questionNo + "/" + questionArrayList.size());
                Log.d("Inside", "processFinished " + questionArrayList);
            }
        });

    }
        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.imageButton_next:
                    questionNo=(questionNo+1)%questionList.size();
                    updateQuestion();

                    break;
                case R.id.imageButton_prev:
                    if(questionNo==0)
                        questionNo=questionList.size()-1;
                    else
                        questionNo=questionNo-1;
                    updateQuestion();

                    break;
                case R.id.button_true:
                    if((questionList.get(questionNo).isValue())==false){
                        shakeAnimation();
                        updateQuestion();
                    }
                        else{

                         fadeAnim();
                         updateQuestion();
                        }

                    break;
                case R.id.button_false:
                    if((questionList.get(questionNo).isValue())==true){
                        shakeAnimation();
                        updateQuestion();
                    }
                    else{

                        fadeAnim();
                        updateQuestion();
                    }
                    break;
            }
        }

    private void updateQuestion() {
        String question=questionList.get(questionNo).getAnswer();
        questionTextView.setText(question);
        questioncount.setText(questionNo + "/" + (questionList.size()-1));
    }

    private void fadeAnim(){
        final CardView cardView=findViewById(R.id.cardView_box);
        AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);

        alphaAnimation.setDuration(120);
        alphaAnimation.setRepeatCount(2);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        cardView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void shakeAnimation(){
        Animation shake= AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake_animation);
        final CardView cardView=findViewById(R.id.cardView_box);
        shake.setDuration(100);
        shake.setRepeatCount(1);
        shake.setRepeatMode(Animation.REVERSE);
        cardView.setAnimation(shake);


        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
//         private void updateScore(){
//             points+=100;
//             score.setText(String.valueOf(points));
//         }
}
