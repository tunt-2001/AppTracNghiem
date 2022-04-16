package com.example.apptracnghiem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptracnghiem.DAO.DAO_Question;
import com.example.apptracnghiem.Database.Database;
import com.example.apptracnghiem.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuestionActivity extends AppCompatActivity {
    TextView tv_cauHoi_lv1,
            tv_Question,
            getTextViewQuestionCount,
            textViewCategory,
            textViewCCountDown,
            textViewScore;
    RadioButton radio_btn1,
            radio_btn2,
            radio_btn3,
            radio_btn4;
    RadioGroup radio_group;
    Button btn_confirm_next;
    private int questionCounter;
    private int questionSize;
    private CountDownTimer countDownTimer;
    private ArrayList<Question> questionArrayList;
    private long TimeleftInMillis;

    private int score;
    private boolean answered;
    private int count = 0;
    DAO_Question dao_question;


     Question currentQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        anhXa();

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra("idcategories",0);
        String categoryName = intent.getStringExtra("categoriesname");

        textViewCategory.setText("Môn: " + categoryName);
//        Database database = new Database(this);
//        questionArrayList = database.getQuestion(categoryID);
        dao_question = new DAO_Question(this);
//        Create();
        questionArrayList = dao_question.getQuestion(categoryID);
        questionSize = questionArrayList.size();

        //đảo vị trí các phần tử câu hỏi
        Collections.shuffle(questionArrayList);
        ShowNextQuestion();
         btn_confirm_next.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!answered){
                     if(radio_btn1.isChecked() || radio_btn2.isChecked() || radio_btn3.isChecked() || radio_btn4.isChecked()){
                         //kiểm tra đáp án

                         checkAnswer();
                     }else{
                         Toast.makeText(QuestionActivity.this, "Hãy chọn đáp án đúng",Toast.LENGTH_SHORT).show();
                     }
                 }else{
                     ShowNextQuestion();
                 }
             }
         });
    }


    private void ShowNextQuestion() {
        radio_btn1.setTextColor(Color.BLACK);
        radio_btn2.setTextColor(Color.BLACK);
        radio_btn3.setTextColor(Color.BLACK);
        radio_btn4.setTextColor(Color.BLACK);
        btn_confirm_next.setText("Xác Nhận");

        radio_group.clearCheck();
        if(questionCounter < questionSize){
            //lấy fuw liệu
            currentQuestion = questionArrayList.get(questionCounter);
            Log.d("DDDD", "ShowQuestion: " + currentQuestion);
            tv_cauHoi_lv1.setText(currentQuestion.getQuestion());
            radio_btn1.setText(currentQuestion.getOption1());
            radio_btn2.setText(currentQuestion.getOption2());
            radio_btn3.setText(currentQuestion.getOption3());
            radio_btn4.setText(currentQuestion.getOption4());


            //tăng mỗi cậu hỏi
            questionCounter++;
            getTextViewQuestionCount.setText("Câu Hỏi: " + questionCounter + "/" +questionSize);
            tv_Question.setText("Câu số: " + questionCounter);
            answered = false;
            TimeleftInMillis = 30000;
            startCountDown();
        }else{
            finishQuestion();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(TimeleftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeleftInMillis = millisUntilFinished;
                upDateCountDownText();
            }

            @Override
            public void onFinish() {
                TimeleftInMillis = 0;
                upDateCountDownText();
                checkAnswer();
                AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
                LayoutInflater inflater = QuestionActivity.this.getLayoutInflater();
                View view3 = inflater.inflate(R.layout.dialog_thatbai,null);
                builder.setView(view3);
                AlertDialog alertDialog1 = builder.create();
                alertDialog1.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(QuestionActivity.this, MainActivity.class));
                    }
                },2000);

            }

        }.start();

    }
    private void dialog(){

    }



    private void checkAnswer() {
        //click true
        answered = true;
        //trả về radioButton trong rdGroup dc check
        RadioButton rbselect = findViewById(radio_group.getCheckedRadioButtonId());
        //vị trí của câu đã chọn
        int answer = radio_group.indexOfChild(rbselect) + 1;
        //nếu trả lời đungs đáp án
        if(answer == currentQuestion.getAnswer()){
            //tăng 30 điểm
            AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
            LayoutInflater inflater = QuestionActivity.this.getLayoutInflater();
            View view2 = inflater.inflate(R.layout.dialog_chucmung,null);
            builder.setView(view2);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    alertDialog.dismiss();

                }
            },1800);
            score = score + 30;
            textViewScore.setText("Điểm: " + score);
            ShowSolusion();

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
            LayoutInflater inflater = QuestionActivity.this.getLayoutInflater();
            View view3 = inflater.inflate(R.layout.dialog_thatbai,null);
            builder.setView(view3);
            AlertDialog alertDialog1 = builder.create();
            alertDialog1.show();
            ShowSolusion();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(QuestionActivity.this, MainActivity.class));
                }
            },2000);

        }
        //phương thức hiển thị đáp án

    }

    private void ShowSolusion() {
        //set màu cho radio dáp án
        radio_btn1.setTextColor(Color.RED);
        radio_btn2.setTextColor(Color.RED);
        radio_btn3.setTextColor(Color.RED);
        radio_btn4.setTextColor(Color.RED);
        //kiểm tra đáo án set màu bà hiển thị đáp án lên màn hình
        switch (currentQuestion.getAnswer()){
            case 1:
                radio_btn1.setTextColor(Color.GREEN);

                tv_cauHoi_lv1.setText("Đáp án là A");
                break;
            case 2:
                radio_btn2.setTextColor(Color.GREEN);
                tv_cauHoi_lv1.setText("Đáp án là B");
                break;
            case 3:
                radio_btn3.setTextColor(Color.GREEN);
                tv_cauHoi_lv1.setText("Đáp án là C");
                break;
            case 4:
                radio_btn4.setTextColor(Color.GREEN);
                tv_cauHoi_lv1.setText("Đáp án là D");
                break;
        }
        //nếu còn câu trả lời thì button sẽ setText là câu tiếp
        if(questionCounter<questionSize){
            btn_confirm_next.setText("Câu Tiếp");
        }else{
            btn_confirm_next.setText("Hoàn Thành");

        }
        //dưng thời gian lại
        countDownTimer.cancel();

    }


    private void upDateCountDownText() {
        //tính phút
        int minute = (int) ((TimeleftInMillis/1000)/60);
        //tính giây
        int seconds = (int) ((TimeleftInMillis/1000)%60);
        String timeFomatted = String.format(Locale.getDefault(),"%02d:%02d",minute,seconds);
        textViewCCountDown.setText(timeFomatted);

        if(TimeleftInMillis < 10000){
            textViewCCountDown.setTextColor(Color.RED);
        }else{
            textViewCCountDown.setTextColor(Color.BLACK);
        }

    }




   private void finishQuestion() {
        Intent ResultIntent = new Intent();
        ResultIntent.putExtra("score",score);
        setResult(RESULT_OK,ResultIntent);
        finish();


    }

    @Override
    public void onBackPressed() {
        count++;
        if(count >= 1){
            finishQuestion();
        }
        count = 0;
    }

    public void anhXa(){
        tv_Question = findViewById(R.id.tv_QuestionNum1);
        tv_cauHoi_lv1 = findViewById(R.id.tv_cauHoi_lv1);
        radio_btn1 = findViewById(R.id.radio_btn1);
        radio_btn2 = findViewById(R.id.radio_btn2);
        radio_btn3 = findViewById(R.id.radio_btn3);
        radio_btn4 = findViewById(R.id.radio_btn4);
        radio_group = findViewById(R.id.radio_group);
        btn_confirm_next = findViewById(R.id.btn_confirm_next);
        getTextViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewScore = findViewById(R.id.text_view_Score);
        textViewCategory = findViewById(R.id.text_view_category);
        textViewCCountDown = findViewById(R.id.text_view_countdown);


    }
}