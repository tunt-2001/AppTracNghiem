package com.example.apptracnghiem.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptracnghiem.Database.Database;
import com.example.apptracnghiem.model.Question;
import com.example.apptracnghiem.model.Table;

import java.util.ArrayList;

public class DAO_Question {
    Database database;
    SQLiteDatabase sqLiteDatabase;
    public DAO_Question(Context context){
        database = new Database(context);
        sqLiteDatabase = database.getWritableDatabase();
//        CreateQuestions();

    }
    public long insertQuestion(Question question) {
        ContentValues values = new ContentValues();

        values.put(Table.QuestionTable.COLUMN_QUESTION, question.getQuestion());
        values.put(Table.QuestionTable.COLUMN_OPTION1, question.getOption1());
        values.put(Table.QuestionTable.COLUMN_OPTION2, question.getOption2());
        values.put(Table.QuestionTable.COLUMN_OPTION3, question.getOption3());
        values.put(Table.QuestionTable.COLUMN_OPTION4, question.getOption4());
        values.put(Table.QuestionTable.COLUMN_ANSWER, question.getAnswer());
        values.put(Table.QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryID());

        return sqLiteDatabase.insert(Table.QuestionTable.TABLE_NAME, null, values);

    }
    public ArrayList<Question> getQuestion(int categoryID){
        ArrayList<Question> questionArrayList = new ArrayList<>();
//        db= getReadableDatabase();
        sqLiteDatabase = database.getWritableDatabase();
        String selection = Table.QuestionTable.COLUMN_CATEGORY_ID + "= ?";
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};
//        Cursor cursor = db.query(Table.QuestionTable.TABLE_NAME,null,selection,selectionArgs,null,null,null);
        Cursor cursor = sqLiteDatabase.query(Table.QuestionTable.TABLE_NAME,null,selection,selectionArgs,null,null,null);


        if(cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Table.QuestionTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndexOrThrow(Table.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndexOrThrow(Table.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndexOrThrow(Table.QuestionTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndexOrThrow(Table.QuestionTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndexOrThrow(Table.QuestionTable.COLUMN_OPTION4)));
                question.setAnswer(cursor.getInt(cursor.getColumnIndexOrThrow(Table.QuestionTable.COLUMN_ANSWER)));
                question.setCategoryID(cursor.getInt(cursor.getColumnIndexOrThrow(Table.QuestionTable.COLUMN_CATEGORY_ID)));

                questionArrayList.add(question);
            }while(cursor.moveToNext());

        }
        cursor.close();
        return questionArrayList;
    }
//    public void CreateQuestions(){
        //Dữ liệu bảng question
//        Question q1 = new Question("Phục phà phục phịch."+"\n Chân quỳ tay chống? Là con gì?",
//                "A. Con cóc",
//                "B. Con gà",
//                "C. Con chó",
//                "D. Con bò", 1, 1);
//        insertQuestion(q1);
//        Question q2 = new Question("Thực dân Pháp tiến hành cuộc khai thác thuộc địa lần thứ hai ở Đông Dương (1919 - 1929) khi",
//                "A. Hệ thống thuộc địa của chủ nghĩa đế quốc tan rã.",
//                "B. Thế giới tư bản đang lâm vào khủng hoảng thừa.",
//                "C. Cuộc chiến tranh thế giới thứ nhất kết thúc.",
//                "D. Kinh tế các nước tư bản đang trên đà phát triển.", 3, 2);
//        insertQuestion(q2);
//        Question q3 = new Question("Ngành kinh tế nào được thực dân Pháp đầu tư nhiều nhất trong cuộc khai thác thuộc địa lần thứ hai (1919 – 1929) ở Đông Dương?",
//                "A. Nông nghiệp", "B. Công nghiệp", "C. Tài chính- ngân hàng","D. Giao thông vận tải", 1, 2);
//        insertQuestion(q3);
//
//        Question q4 = new Question("Trong cuộc khai thác thuộc địa lần thứ hai ở Đông Dương (1919 - 1929), thực dân Pháp tập trung đầu tư vào",
//                "A. Đồn điền cao su.", "B. Công nghiệp hóa chất.", "C. Công nghiệp luyện kim. ","D. Ngành chế tạo máy.", 1, 2);
//        insertQuestion(q4);
//
//        Question q5 = new Question("Trong cuộc khai thác thuộc địa lần thứ hai ở Đông Dương (1919 - 1929), thực dân Pháp chú trọng đầu tư vào",
//                "A. Chế tạo máy.", "B. Công nghiệp luyện kim.", "C. Công nghiệp hóa chất.","D. Khai thác mỏ.", 4, 2);
//        insertQuestion(q5);
//
//        Question q6 = new Question("Trong cuộc khai thác thuộc địa lần thứ hai (1919), thực dân Pháp sử dụng biện pháp nào để tăng ngân sách Đông Dương?",
//                "A. Mở rộng quy mô sản xuất", "B. Khuyến khích phát triển công nghiệp nhẹ", "C. Tăng thuế và cho vay lãi","D. Mở rộng trao đổi buôn bán", 3, 2);
//        insertQuestion(q6);
//        Question q7 = new Question("Giai cấp nào trong xã hội Việt Nam đầu thế kỉ XX có quan hệ gắn bó với giai cấp nông dân?",
//                "A. Công nhân", "B. Địa chủ", "C. Tư sản","D. Tiểu tư sản", 1, 2);
//        insertQuestion(q7);
//        Question q8 = new Question("Sau chiến tranh thế giới thứ nhất, lực lượng xã hội có khả năng vươn lên nắm ngọn cờ lãnh đạo cách mạng Việt Nam là",
//                "A. Đại địa chủ", "B. Trung địa chủ", "C. Tiểu địa chủ","D. Trung, tiểu địa chủ", 4, 2);
//        insertQuestion(q8);
//        Question q9 = new Question("Trung và tiểu địa chủ Việt Nam sau Chiến tranh thế giới thứ nhất là lực lượng",
//                "A. có tinh thần chống Pháp và tay sai. ", "B. làm tay sai cho Pháp.","C. bóc lột nông dân và làm tay sai cho Pháp.","D. thỏa hiệp với Pháp.", 1, 2);
//        insertQuestion(q9);
//        Question q10 = new Question("Ai là tác giả của chương chương trình khai thác thuộc địa lần thứ hai của thực dân Pháp ở Đông Dương?",
//                "A. Pô-đu-me", "B. Anbe-xarô", "C. Pôn-bô","D. Va-ren", 2, 2);
//        insertQuestion(q10);
//        Question q11 = new Question("Hà Nội là thủ đô nước nào?",
//                "A. Mỹ", "B. Cà Màu", "C.Nam Cực","D.Việt Nam", 4, 3);
//        insertQuestion(q11);
//        Question q12 = new Question("Trong câu “Thưa ông, chúng cháu ở Gia Lâm lên đấy ạ. Đi bốn năm hôm mới lên đến đây, vất vả quá!”. Câu nói “Thưa ông” thuộc thành phần gì của câu?",
//                " A. Phụ chú", " B. Cảm thán", "C. Gọi đáp","D. Tình thái", 3, 1);
//        insertQuestion(q12);
//        Question q13 = new Question("Đỉnh núi Pan-xi-păng có độ cao bao nhiêu mét?",
//                "a. 3134 mét.", "b. 3143 mét.", "c. 3314 mét.","a. 1 mét 2", 2, 3);
//        insertQuestion(q13);


//        Question q1 = new Question("Phục phà phục phịch."+"\n Chân quỳ tay chống? Là con gì?",
//                "A. Con cóc",
//                "B. Con gà",
//                "C. Con chó",
//                "D. Con bò", 1, 5);
//        insertQuestion(q1);
//
//        Question q2 = new Question("Con gì chân ngắn. Mà lại có màng. Mỏ bẹt màu vàng. Hay kêu cạp cạp.Là con gì?",
//                "A. Con Bò",
//                "B. Con Gà",
//                "C. Con Chó",
//                "D. Con Vịt", 4, 5);
//        insertQuestion(q2);
//        Question q3 = new Question("Da cóc mà bọc bột lọc, \n Bột lọc mà bọc hòn than? \n Là quả gì?",
//                "A. Quả dứa",
//                "B. Quả mít",
//                "C. Quả nhãn",
//                "D. Quả cóc", 3, 5);
//        insertQuestion(q3);
//        Question q4 = new Question("Vừa bằng quả ổi. \n Vừa nổi vừa chìm. \n Là con gì?",
//                "A. Con Nai",
//                "B. Con Gà",
//                "C. Con Ốc",
//                "D. Con Chó", 3, 5);
//        insertQuestion(q4);
//        Question q5 = new Question("Hoa gì ẹ thẹn bên đường?",
//                "A. Hoa Trinh Nữ",
//                "B. Hoa Phượng",
//                "C. Hoa Mai",
//                "D. Hoa Đào ", 1, 5);
//        insertQuestion(q5);
//        Question q6 = new Question("Sông gì có nước mắt? Sông gì có mùi thơm?",
//                "A. Sông Hương / sông Gianh",
//                "B. Sông Nhật Lệ / sông Hương",
//                "C. Sông Mã / Sông Nhật Lệ",
//                "D. Sông Hồng / sông Hương ", 2, 5);
//        insertQuestion(q6);
//        Question q7 = new Question("Mình rồng, đuôi phụng le te. Mùa Đông ấp trứng, mùa Hè nở con. Là Cây gì?",
//                "A. Cây Phượng",
//                "B. Cây Tre",
//                "C. Cây Bàng",
//                "D. Cây Cau ", 4, 5);
//        insertQuestion(q7);
//        Question q8 = new Question("Bốn cột tứ trụ. Người ngự lên trên. Gươm bạc hai bên. Chầu vua thượng đế. Là Con gì?",
//                "A. Con Voi",
//                "B. Con Hà Mã",
//                "C. Con Sư Tử",
//                "D. Con Hươu ", 1, 5);
//        insertQuestion(q8);
//        Question q9 = new Question("Con gì có mũi mà không có mắt?",
//                "A. Con gà",
//                "B. Con Voi",
//                "C. Con Dao",
//                "D. Con Đường ", 3, 5);
//        insertQuestion(q9);
//        Question q10 = new Question("Vừa học vừa vui \n Ai biết giúp tui \n Kể tên ba tỉnh \n Có ba con vật hiện ra?",
//                "A. Sóc Trăng, Đồng Nai, Sơn La.",
//                "B. Sơn La, Tây Ninh, Đồng Nai.",
//                "C. Đồng Nai, Sóc Trăng, Thái Nguyên.",
//                "D. Sóc Trăng, Sơn La, Phú Quốc", 1, 5);
//        insertQuestion(q10);
//        Question q11 = new Question("Tỉnh nào: Có TIỀN, có BẠC???",
//                "A. Sóc Trăng; Trà Vinh",
//                "B. Tiền Giang, Bạc Liêu",
//                "C. Cần Thơ, An Giang",
//                "D. Hà Giang; Kiên Giang", 2, 5);
//        insertQuestion(q11);
//        Question q12 = new Question("Tỉnh nào : Có THƠ, có TRĂNG?",
//                "A. Bình Định, Quảng Nam",
//                "B. Kiên Giang, Bình Phước",
//                "C. Cần Thơ, Sóc Trăng",
//                "D. Tây Ninh, Vũng Tàu", 3, 5);
//        insertQuestion(q12);
//        Question q13 = new Question("Trên trời có ông sao tua. \n Ớ đâu lại có nhiều dừa bạn ơi?",
//                "A. TP Hà Nội",
//                "B.Cà Mau",
//                "C. Bến Tre",
//                "D. TP Hồ Chí Minh", 3, 5);
//        insertQuestion(q13);
//        Question q14 = new Question("Nơi nào biết mấy tự hào \n Tên vàng chói lọi, thay vào tên xưa?",
//                "A. Phú Thọ",
//                "B. TP Hà Nội",
//                "C. Huế",
//                "D. TP Hồ Chí Minh", 4, 5);
//        insertQuestion(q14);
//        Question q15 = new Question("Mèo gì sợ chuột?",
//                "A. Mèo Mun",
//                "B. Mèo Kitty",
//                "C. Mèo Máy Doraemon",
//                "D. Mèo Mướp", 3, 5);
//        insertQuestion(q15);
//        Question q16 = new Question("Theo truyền thuyết dân gian, Nhà Táo có những ai?",
//                "A. 2 ông và 1 bà",
//                "B. 2 ông và 2 bà",
//                "C. 2 bà và 1 ông",
//                "D. 3 ông và 1 bà", 1, 5);
//        insertQuestion(q16);
//        Question q17 = new Question("Mâm ngũ quả cúng ngày tết của miền Nam là những trái nào?",
//                "A. Nho, dừa, xoài, ổi, sung",
//                "B. Cầu, dừa, đu đủ, xoài, sung",
//                "C. Cầu, đu đủ, ổi, na, sầu riêng",
//                "D. Cam, ổi, lê, táo, đu đủ", 2, 5);
//        insertQuestion(q17);
//        Question q18 = new Question("Có mấy loại bánh chưng?",
//                "A. 1",
//                "B. 2",
//                "C. 3",
//                "D. 4", 2, 5);
//        insertQuestion(q18);
//        Question q19 = new Question("Người đầu tiên đến chơi nhà trong dịp năm mới được gọi là gì?",
//                "A. Người lì xì",
//                "B. Người xông nhà (xông đất)",
//                "C. Người mở hàn",
//                "D. Người trong gia đình", 2, 5);
//        insertQuestion(q19);
//        Question q20 = new Question("Ông Táo là vị thần gì trong nhà?",
//                "A. Thần bể nước",
//                "B. Thần thổ địa",
//                "C. Thần tài",
//                "D. Thần bếp", 4, 5);
//        insertQuestion(q20);
//        Question q21 = new Question("Ông già Noel vào nhà của các em nhỏ bằng đường nào?",
//                "A. Cửa chính",
//                "B. Cửa sổ",
//                "C. Ban công",
//                "D. Ống khói", 4, 5);
//        insertQuestion(q21);
//        Question q22 = new Question("Ông già Noel thường mặc quần áo màu gì?",
//                "A. Màu cam, viền trắng",
//                "B. Màu đỏ, viền trắng",
//                "C. Màu trắng, viền đỏ",
//                "D. Màu đỏ, viền xanh", 2, 5);
//        insertQuestion(q22);
//        Question q23 = new Question("Lễ chính thức của Lễ Giáng sinh là ngày nào?",
//                "A. Ngày 23 tháng 12",
//                "B. Ngày 22 tháng 12",
//                "C. Ngày 25 tháng 12",
//                "D. Ngày 20 tháng 12", 3, 5);
//        insertQuestion(q23);
//        Question q24 = new Question("Ông già Noel sinh sống tại Bắc Cực với những người nào?",
//                "A. Sống cùng nàng bạch tuyết và 7 chú lùn",
//                "B. Sống cùng các chú lùn và các con tuần lộc",
//                "C. Sống cùng những chú cánh cụt",
//                "D. Sống cùng những con gấu tuyết", 2, 5);
//        insertQuestion(q24);
//        Question q25 = new Question("Ông già Noel cưỡi mấy con tuần lộc?",
//                "A. 7 con",
//                "B. 8 con",
//                "C. 9 con",
//                "D. 10 con", 3, 5);
//        insertQuestion(q25);
//        Question q26 = new Question("Kết quả của phép tính 2+2 là: ",
//                "A. 4",
//                "B. 1",
//                "C. 5",
//                "D. 2", 1, 1);
//        insertQuestion(q26);
//        Question q27 = new Question("Kết quả của phép tính 4+6 là: ",
//                "A. 8",
//                "B. 10",
//                "C. 24",
//                "D. 2", 2, 1);
//        insertQuestion(q27);
//        Question q28 = new Question("Kết quả của phép tính 9x7 là: ",
//                "A. 43",
//                "B. 16",
//                "C. 56",
//                "D. 63", 4, 1);
//        insertQuestion(q28);
//        Question q29 = new Question("Kết quả của phép tính 12:6 là: ",
//                "A. 4",
//                "B. 1",
//                "C. 6",
//                "D. 2", 4, 1);
//        insertQuestion(q29);
//        Question q30 = new Question("Kết quả của phép tính 45-16 là: ",
//                "A. 32",
//                "B. 61",
//                "C. 29",
//                "D. 28", 3, 1);
//        insertQuestion(q30);
//        Question q31 = new Question("Kết quả của phép tính 152-126 là: ",
//                "A. 26",
//                "B. 28",
//                "C. 20",
//                "D. 24", 1, 1);
//        insertQuestion(q31);
//        Question q32 = new Question("Kết quả của phép tính 15x5 là: ",
//                "A. 70",
//                "B. 75",
//                "C. 80",
//                "D. 90", 2, 1);
//        insertQuestion(q32);
//        Question q33 = new Question("Kết quả của phép tính 45:3 là: ",
//                "A. 15",
//                "B. 13",
//                "C. 12",
//                "D. 16", 1, 1);
//        insertQuestion(q33);
//        Question q34 = new Question("Kết quả của phép tính 415+105 là: ",
//                "A. 502",
//                "B. 520",
//                "C. 255",
//                "D. 525", 2, 1);
//        insertQuestion(q34);
//        Question q35 = new Question("Kết quả của phép tính 2002:2 là: ",
//                "A. 1001",
//                "B. 1020",
//                "C. 1010",
//                "D. 10022", 1, 1);
//        insertQuestion(q34);
//        Question q36 = new Question("Kết quả của phép tính 2020+2022 là: ",
//                "A. 4044",
//                "B. 4004",
//                "C. 4444",
//                "D. 4042", 4, 1);
//        insertQuestion(q36);
//        Question q37 = new Question("Kết quả của phép tính 123+456 là: ",
//                "A. 552",
//                "B. 568",
//                "C. 579",
//                "D. 525", 3, 1);
//        insertQuestion(q37);
//        Question q38 = new Question("Kết quả của phép tính 1995-1698 là: ",
//                "A. 303",
//                "B. 293",
//                "C. 297",
//                "D. 295", 3, 1);
//        insertQuestion(q38);
//        Question q39 = new Question("Kết quả của phép tính 415-105 là: ",
//                "A. 310",
//                "B. 520",
//                "C. 305",
//                "D. 320", 1, 1);
//                insertQuestion(q39);
//        Question q40 = new Question("Kết quả của phép tính 12x12 là: ",
//                "A. 122",
//                "B. 142",
//                "C. 124",
//                "D. 144", 4, 1);
//        insertQuestion(q40);
//        Question q41 = new Question("Kết quả của phép tính 169:13 là: ",
//                "A. 11",
//                "B. 16",
//                "C. 13",
//                "D. 12", 3, 1);
//        insertQuestion(q41);
//        Question q42 = new Question("Kết quả của phép tính 7x7 là: ",
//                "A. 44",
//                "B. 47",
//                "C. 49",
//                "D. 46", 3, 1);
//        insertQuestion(q42);
//        Question q43 = new Question("Kết quả của phép tính 167-141 là: ",
//                "A. 32",
//                "B. 36",
//                "C. 25",
//                "D. 26", 4, 1);
//        insertQuestion(q43);
//        Question q44 = new Question("Kết quả của phép tính 26x30 là: ",
//                "A. 700",
//                "B. 750",
//                "C. 785",
//                "D. 780", 4, 1);
//        insertQuestion(q44);
//
//        Question q45 = new Question("Kết quả của phép tính 556+443 là: ",
//                "A. 1001",
//                "B. 999",
//                "C. 998",
//                "D. 995", 2, 1);
//        insertQuestion(q45);
//        Question q46 = new Question("Từ nào sau đây viết đúng? ",
//                "A. Sắp sếp",
//                "B. Xắp sếp",
//                "C. Xắp xếp",
//                "D. Sắp xếp", 4, 2);
//        insertQuestion(q46);
//        Question q47 = new Question("Từ nào sau đây có tiếng khác các từ còn lại? ",
//                "A. Cắp",
//                "B. Cặp",
//                "C. Lắp",
//                "D. Tắp", 2, 2);
//        insertQuestion(q47);
//        Question q48  = new Question("Tôi là học sinh lớp 1. \n Từ nào có chứa vần 'inh'",
//                "A. Tôi",
//                "B. Lớp",
//                "C. Học",
//                "D. Sinh", 4, 2);
//        insertQuestion(q48);
//        Question q49  = new Question("Điền vào chỗ chấm: ...inh đẹp.",
//                "A. S",
//                "B. L",
//                "C. H",
//                "D. X", 4, 2);
//        insertQuestion(q49);
//        Question q50  = new Question("Từ nào viết sai chính tả?",
//                "A. Gà",
//                "B. Kẻ",
//                "C. Quả",
//                "D. Gé", 4, 2);
//        insertQuestion(q50);
//        Question q51  = new Question("Từ nào sau chỉ người?",
//                "A. Mèo",
//                "B. Hộp bút",
//                "C. Áo len",
//                "D. Thợ mỏ", 4, 2);
//        insertQuestion(q51);
//        Question q52  = new Question("Từ nào sau chỉ hành động?",
//                "A. Đồng hồ",
//                "B. Đi học",
//                "C. Áo khoác",
//                "D. Con chó", 2, 2);
//        insertQuestion(q52);
//        Question q53  = new Question("Chủ ngữ trong câu: ''Em là học sinh lớp 2'' là:",
//                "A. Em",
//                "B. Em là",
//                "C. Là học sinh lớp 2",
//                "D. Lớp 2", 1, 2);
//        insertQuestion(q53);
//        Question q54  = new Question("Vị Ngữ trong câu: ''Em là học sinh lớp 2'' là:",
//                "A. Em",
//                "B. Em là",
//                "C. Là học sinh lớp 2",
//                "D. Lớp 2", 3, 2);
//        insertQuestion(q54);
//        Question q55  = new Question("Câu nào sau đây là câu giới thiệu?",
//                "A. Em đang đi học",
//                "B. Em đang quét nhà",
//                "C. Em là học sinh lớp 2",
//                "D. Em đang đi chơi", 3, 2);
//        insertQuestion(q55);
//TVTVL3









//    }



}
