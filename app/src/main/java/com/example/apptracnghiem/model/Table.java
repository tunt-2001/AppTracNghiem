package com.example.apptracnghiem.model;

import android.provider.BaseColumns;

public class Table {
    private Table(){

    }
    //dữ liệu bảng categories
    public static class CategoryTable implements BaseColumns{
        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_NAME = "name";
//        public static final String COLUMN_ID = "id";


    }
    //dữ liệu bẳng question
    public static class QuestionTable implements BaseColumns{
        //tên bảng
        public static final String TABLE_NAME = "questions";
        //câu hỏi
        public static final String COLUMN_QUESTION = "question";
        //4 đáp án
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        //dáp án
        public static final String COLUMN_ANSWER = "answer";
        //id chuyên mục
        public static final String COLUMN_CATEGORY_ID = "id_categories";



    }
    public static class LoginTable implements BaseColumns{
        //tên bảng
        public static final String TABLE_NAME = "login";

        public static final String COLUMN_USER = "username";
        public static final String COLUMN_PASS = "password";


    }
}
