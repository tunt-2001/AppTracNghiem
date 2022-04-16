package com.example.apptracnghiem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptracnghiem.DAO.DAO_Question;
import com.example.apptracnghiem.Database.Database;
import com.example.apptracnghiem.model.Category;
import com.example.apptracnghiem.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_HighScore;
    private Spinner spiner_category;
    private Button btn_batdau, btn_layduleiu;
    private int highScore;
    DAO_Question db;
    ImageView Img_ThongTin;
    RequestQueue queue;



    private static final int REQUEST_CODE_QUESTION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        queue = Volley.newRequestQueue(this);

        //load chủ đề
        LoadCategory();
        loadHighScore();
        //click để  bắt đầu
        btn_batdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startQuestion();
            }
        });
        btn_layduleiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Create();

            }
        });
        Img_ThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ThongTinCaNhanActivity.class));
                finish();
            }
        });

    }


    private void loadHighScore() {
        SharedPreferences preferences = getSharedPreferences("share",MODE_PRIVATE);
        highScore = preferences.getInt("highScore",0);
        tv_HighScore.setText("Điểm cao nhất: " + highScore);

    }
    public void Create(){
        String url = "http://192.168.1.9:1337/api/cau-hois";
        db = new DAO_Question(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.d("cccccc", response.toString());
                        JSONArray jsonArray;
                        JSONObject cauHoi;
                        JSONObject object;


                        try {
                            jsonArray = (JSONArray) response.get("data");
                            Log.d("zzzz","so phan tu: "+ response);
                            for( int i =0; i <jsonArray.length(); i++) {
                                cauHoi = (JSONObject) jsonArray.get(i);
                                object = (JSONObject) cauHoi.get("attributes");

                                Question q1 = new Question(object.get("content").toString(),object.get("option1").toString()
                                        ,object.get("option2").toString(),object.get("option3").toString(),object.get("option4").toString(),object.getInt("answer"),object.getInt("id_categories"));
                                long a = db.insertQuestion(q1);
                                Log.d("DDDDDD", "số phần tử: " + i + object.get("content"));


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest);
//                tvQuestion.setText(question.getQuestion());
//                Log.d("DDDDDD", "onClick: " + tvQuestion);
    }

    //hàm bắt đầu câu hỏi qua activity question
    private void startQuestion() {
        //lấy id, name chủ dề đã chọn
        Category category = (Category) spiner_category.getSelectedItem();
        int categoryID = category.getId();
        String categoryName = category.getName();

//        chuyển qua activity question
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        //gửi dữ liệu name, id
        intent.putExtra("idcategories",categoryID);
        intent.putExtra("categoriesname",categoryName);


        //sưe dụng startActivity để có thể nhận lại dữ liệu kết quả trả về thông qua phương thức onActivity()
        startActivityForResult(intent, REQUEST_CODE_QUESTION);
    }

    private void anhXa() {
        tv_HighScore = findViewById(R.id.tv_highScore);
        btn_batdau = findViewById(R.id.btn_batdau);
        btn_layduleiu = findViewById(R.id.btn_layduleiu);
        spiner_category = findViewById(R.id.spiner_category);
        Img_ThongTin = findViewById(R.id.Img_ThongTin);

    }
    //load chủ đề
    private void LoadCategory(){
        Database database = new Database(this);
        //lấy dữ liệu danh sách chủ đề
        List<Category> categories = database.getDataCategories();
        //tạo adapter
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,categories);

        //bố cục hiển thị chhur đề
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //gắn chủ đề lên spiner
        spiner_category.setAdapter(categoryArrayAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            int Score = data.getIntExtra("score", 0);
//            if(Score > highScore){
                upDateHighScore(Score);
//            }
        }


    }

    private void upDateHighScore(int score) {
        highScore += score;
        tv_HighScore.setText("Điểm cao nhất: " + highScore);
        //lưu trư điểm
        SharedPreferences preferences = getSharedPreferences("share",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //gán giá trị cho điểm mới
        editor.putInt("highScore",highScore);
        //hoàn tất
        editor.apply();




    }
}