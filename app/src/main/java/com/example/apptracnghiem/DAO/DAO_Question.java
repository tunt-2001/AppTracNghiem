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
        //D??? li???u b???ng question
//        Question q1 = new Question("Ph???c ph?? ph???c ph???ch."+"\n Ch??n qu??? tay ch???ng? L?? con g???",
//                "A. Con c??c",
//                "B. Con g??",
//                "C. Con ch??",
//                "D. Con b??", 1, 1);
//        insertQuestion(q1);
//        Question q2 = new Question("Th???c d??n Ph??p ti???n h??nh cu???c khai th??c thu???c ?????a l???n th??? hai ??? ????ng D????ng (1919 - 1929) khi",
//                "A. H??? th???ng thu???c ?????a c???a ch??? ngh??a ????? qu???c tan r??.",
//                "B. Th??? gi???i t?? b???n ??ang l??m v??o kh???ng ho???ng th???a.",
//                "C. Cu???c chi???n tranh th??? gi???i th??? nh???t k???t th??c.",
//                "D. Kinh t??? c??c n?????c t?? b???n ??ang tr??n ???? ph??t tri???n.", 3, 2);
//        insertQuestion(q2);
//        Question q3 = new Question("Ng??nh kinh t??? n??o ???????c th???c d??n Ph??p ?????u t?? nhi???u nh???t trong cu???c khai th??c thu???c ?????a l???n th??? hai (1919 ??? 1929) ??? ????ng D????ng?",
//                "A. N??ng nghi???p", "B. C??ng nghi???p", "C. T??i ch??nh- ng??n h??ng","D. Giao th??ng v???n t???i", 1, 2);
//        insertQuestion(q3);
//
//        Question q4 = new Question("Trong cu???c khai th??c thu???c ?????a l???n th??? hai ??? ????ng D????ng (1919 - 1929), th???c d??n Ph??p t???p trung ?????u t?? v??o",
//                "A. ?????n ??i???n cao su.", "B. C??ng nghi???p h??a ch???t.", "C. C??ng nghi???p luy???n kim. ","D. Ng??nh ch??? t???o m??y.", 1, 2);
//        insertQuestion(q4);
//
//        Question q5 = new Question("Trong cu???c khai th??c thu???c ?????a l???n th??? hai ??? ????ng D????ng (1919 - 1929), th???c d??n Ph??p ch?? tr???ng ?????u t?? v??o",
//                "A. Ch??? t???o m??y.", "B. C??ng nghi???p luy???n kim.", "C. C??ng nghi???p h??a ch???t.","D. Khai th??c m???.", 4, 2);
//        insertQuestion(q5);
//
//        Question q6 = new Question("Trong cu???c khai th??c thu???c ?????a l???n th??? hai (1919), th???c d??n Ph??p s??? d???ng bi???n ph??p n??o ????? t??ng ng??n s??ch ????ng D????ng?",
//                "A. M??? r???ng quy m?? s???n xu???t", "B. Khuy???n kh??ch ph??t tri???n c??ng nghi???p nh???", "C. T??ng thu??? v?? cho vay l??i","D. M??? r???ng trao ?????i bu??n b??n", 3, 2);
//        insertQuestion(q6);
//        Question q7 = new Question("Giai c???p n??o trong x?? h???i Vi???t Nam ?????u th??? k??? XX c?? quan h??? g???n b?? v???i giai c???p n??ng d??n?",
//                "A. C??ng nh??n", "B. ?????a ch???", "C. T?? s???n","D. Ti???u t?? s???n", 1, 2);
//        insertQuestion(q7);
//        Question q8 = new Question("Sau chi???n tranh th??? gi???i th??? nh???t, l???c l?????ng x?? h???i c?? kh??? n??ng v????n l??n n???m ng???n c??? l??nh ?????o c??ch m???ng Vi???t Nam l??",
//                "A. ?????i ?????a ch???", "B. Trung ?????a ch???", "C. Ti???u ?????a ch???","D. Trung, ti???u ?????a ch???", 4, 2);
//        insertQuestion(q8);
//        Question q9 = new Question("Trung v?? ti???u ?????a ch??? Vi???t Nam sau Chi???n tranh th??? gi???i th??? nh???t l?? l???c l?????ng",
//                "A. c?? tinh th???n ch???ng Ph??p v?? tay sai. ", "B. l??m tay sai cho Ph??p.","C. b??c l???t n??ng d??n v?? l??m tay sai cho Ph??p.","D. th???a hi???p v???i Ph??p.", 1, 2);
//        insertQuestion(q9);
//        Question q10 = new Question("Ai l?? t??c gi??? c???a ch????ng ch????ng tr??nh khai th??c thu???c ?????a l???n th??? hai c???a th???c d??n Ph??p ??? ????ng D????ng?",
//                "A. P??-??u-me", "B. Anbe-xar??", "C. P??n-b??","D. Va-ren", 2, 2);
//        insertQuestion(q10);
//        Question q11 = new Question("H?? N???i l?? th??? ???? n?????c n??o?",
//                "A. M???", "B. C?? M??u", "C.Nam C???c","D.Vi???t Nam", 4, 3);
//        insertQuestion(q11);
//        Question q12 = new Question("Trong c??u ???Th??a ??ng, ch??ng ch??u ??? Gia L??m l??n ?????y ???. ??i b???n n??m h??m m???i l??n ?????n ????y, v???t v??? qu??!???. C??u n??i ???Th??a ??ng??? thu???c th??nh ph???n g?? c???a c??u?",
//                " A. Ph??? ch??", " B. C???m th??n", "C. G???i ????p","D. T??nh th??i", 3, 1);
//        insertQuestion(q12);
//        Question q13 = new Question("?????nh n??i Pan-xi-p??ng c?? ????? cao bao nhi??u m??t?",
//                "a. 3134 m??t.", "b. 3143 m??t.", "c. 3314 m??t.","a. 1 m??t 2", 2, 3);
//        insertQuestion(q13);


//        Question q1 = new Question("Ph???c ph?? ph???c ph???ch."+"\n Ch??n qu??? tay ch???ng? L?? con g???",
//                "A. Con c??c",
//                "B. Con g??",
//                "C. Con ch??",
//                "D. Con b??", 1, 5);
//        insertQuestion(q1);
//
//        Question q2 = new Question("Con g?? ch??n ng???n. M?? l???i c?? m??ng. M??? b???t m??u v??ng. Hay k??u c???p c???p.L?? con g???",
//                "A. Con B??",
//                "B. Con G??",
//                "C. Con Ch??",
//                "D. Con V???t", 4, 5);
//        insertQuestion(q2);
//        Question q3 = new Question("Da c??c m?? b???c b???t l???c, \n B???t l???c m?? b???c h??n than? \n L?? qu??? g???",
//                "A. Qu??? d???a",
//                "B. Qu??? m??t",
//                "C. Qu??? nh??n",
//                "D. Qu??? c??c", 3, 5);
//        insertQuestion(q3);
//        Question q4 = new Question("V???a b???ng qu??? ???i. \n V???a n???i v???a ch??m. \n L?? con g???",
//                "A. Con Nai",
//                "B. Con G??",
//                "C. Con ???c",
//                "D. Con Ch??", 3, 5);
//        insertQuestion(q4);
//        Question q5 = new Question("Hoa g?? ??? th???n b??n ???????ng?",
//                "A. Hoa Trinh N???",
//                "B. Hoa Ph?????ng",
//                "C. Hoa Mai",
//                "D. Hoa ????o ", 1, 5);
//        insertQuestion(q5);
//        Question q6 = new Question("S??ng g?? c?? n?????c m???t? S??ng g?? c?? m??i th??m?",
//                "A. S??ng H????ng / s??ng Gianh",
//                "B. S??ng Nh???t L??? / s??ng H????ng",
//                "C. S??ng M?? / S??ng Nh???t L???",
//                "D. S??ng H???ng / s??ng H????ng ", 2, 5);
//        insertQuestion(q6);
//        Question q7 = new Question("M??nh r???ng, ??u??i ph???ng le te. M??a ????ng ???p tr???ng, m??a H?? n??? con. L?? C??y g???",
//                "A. C??y Ph?????ng",
//                "B. C??y Tre",
//                "C. C??y B??ng",
//                "D. C??y Cau ", 4, 5);
//        insertQuestion(q7);
//        Question q8 = new Question("B???n c???t t??? tr???. Ng?????i ng??? l??n tr??n. G????m b???c hai b??n. Ch???u vua th?????ng ?????. L?? Con g???",
//                "A. Con Voi",
//                "B. Con H?? M??",
//                "C. Con S?? T???",
//                "D. Con H????u ", 1, 5);
//        insertQuestion(q8);
//        Question q9 = new Question("Con g?? c?? m??i m?? kh??ng c?? m???t?",
//                "A. Con g??",
//                "B. Con Voi",
//                "C. Con Dao",
//                "D. Con ???????ng ", 3, 5);
//        insertQuestion(q9);
//        Question q10 = new Question("V???a h???c v???a vui \n Ai bi???t gi??p tui \n K??? t??n ba t???nh \n C?? ba con v???t hi???n ra?",
//                "A. S??c Tr??ng, ?????ng Nai, S??n La.",
//                "B. S??n La, T??y Ninh, ?????ng Nai.",
//                "C. ?????ng Nai, S??c Tr??ng, Th??i Nguy??n.",
//                "D. S??c Tr??ng, S??n La, Ph?? Qu???c", 1, 5);
//        insertQuestion(q10);
//        Question q11 = new Question("T???nh n??o: C?? TI???N, c?? B???C???",
//                "A. S??c Tr??ng; Tr?? Vinh",
//                "B. Ti???n Giang, B???c Li??u",
//                "C. C???n Th??, An Giang",
//                "D. H?? Giang; Ki??n Giang", 2, 5);
//        insertQuestion(q11);
//        Question q12 = new Question("T???nh n??o : C?? TH??, c?? TR??NG?",
//                "A. B??nh ?????nh, Qu???ng Nam",
//                "B. Ki??n Giang, B??nh Ph?????c",
//                "C. C???n Th??, S??c Tr??ng",
//                "D. T??y Ninh, V??ng T??u", 3, 5);
//        insertQuestion(q12);
//        Question q13 = new Question("Tr??n tr???i c?? ??ng sao tua. \n ??? ????u l???i c?? nhi???u d???a b???n ??i?",
//                "A. TP H?? N???i",
//                "B.C?? Mau",
//                "C. B???n Tre",
//                "D. TP H??? Ch?? Minh", 3, 5);
//        insertQuestion(q13);
//        Question q14 = new Question("N??i n??o bi???t m???y t??? h??o \n T??n v??ng ch??i l???i, thay v??o t??n x??a?",
//                "A. Ph?? Th???",
//                "B. TP H?? N???i",
//                "C. Hu???",
//                "D. TP H??? Ch?? Minh", 4, 5);
//        insertQuestion(q14);
//        Question q15 = new Question("M??o g?? s??? chu???t?",
//                "A. M??o Mun",
//                "B. M??o Kitty",
//                "C. M??o M??y Doraemon",
//                "D. M??o M?????p", 3, 5);
//        insertQuestion(q15);
//        Question q16 = new Question("Theo truy???n thuy???t d??n gian, Nh?? T??o c?? nh???ng ai?",
//                "A. 2 ??ng v?? 1 b??",
//                "B. 2 ??ng v?? 2 b??",
//                "C. 2 b?? v?? 1 ??ng",
//                "D. 3 ??ng v?? 1 b??", 1, 5);
//        insertQuestion(q16);
//        Question q17 = new Question("M??m ng?? qu??? c??ng ng??y t???t c???a mi???n Nam l?? nh???ng tr??i n??o?",
//                "A. Nho, d???a, xo??i, ???i, sung",
//                "B. C???u, d???a, ??u ?????, xo??i, sung",
//                "C. C???u, ??u ?????, ???i, na, s???u ri??ng",
//                "D. Cam, ???i, l??, t??o, ??u ?????", 2, 5);
//        insertQuestion(q17);
//        Question q18 = new Question("C?? m???y lo???i b??nh ch??ng?",
//                "A. 1",
//                "B. 2",
//                "C. 3",
//                "D. 4", 2, 5);
//        insertQuestion(q18);
//        Question q19 = new Question("Ng?????i ?????u ti??n ?????n ch??i nh?? trong d???p n??m m???i ???????c g???i l?? g???",
//                "A. Ng?????i l?? x??",
//                "B. Ng?????i x??ng nh?? (x??ng ?????t)",
//                "C. Ng?????i m??? h??n",
//                "D. Ng?????i trong gia ????nh", 2, 5);
//        insertQuestion(q19);
//        Question q20 = new Question("??ng T??o l?? v??? th???n g?? trong nh???",
//                "A. Th???n b??? n?????c",
//                "B. Th???n th??? ?????a",
//                "C. Th???n t??i",
//                "D. Th???n b???p", 4, 5);
//        insertQuestion(q20);
//        Question q21 = new Question("??ng gi?? Noel v??o nh?? c???a c??c em nh??? b???ng ???????ng n??o?",
//                "A. C???a ch??nh",
//                "B. C???a s???",
//                "C. Ban c??ng",
//                "D. ???ng kh??i", 4, 5);
//        insertQuestion(q21);
//        Question q22 = new Question("??ng gi?? Noel th?????ng m???c qu???n ??o m??u g???",
//                "A. M??u cam, vi???n tr???ng",
//                "B. M??u ?????, vi???n tr???ng",
//                "C. M??u tr???ng, vi???n ?????",
//                "D. M??u ?????, vi???n xanh", 2, 5);
//        insertQuestion(q22);
//        Question q23 = new Question("L??? ch??nh th???c c???a L??? Gi??ng sinh l?? ng??y n??o?",
//                "A. Ng??y 23 th??ng 12",
//                "B. Ng??y 22 th??ng 12",
//                "C. Ng??y 25 th??ng 12",
//                "D. Ng??y 20 th??ng 12", 3, 5);
//        insertQuestion(q23);
//        Question q24 = new Question("??ng gi?? Noel sinh s???ng t???i B???c C???c v???i nh???ng ng?????i n??o?",
//                "A. S???ng c??ng n??ng b???ch tuy???t v?? 7 ch?? l??n",
//                "B. S???ng c??ng c??c ch?? l??n v?? c??c con tu???n l???c",
//                "C. S???ng c??ng nh???ng ch?? c??nh c???t",
//                "D. S???ng c??ng nh???ng con g???u tuy???t", 2, 5);
//        insertQuestion(q24);
//        Question q25 = new Question("??ng gi?? Noel c?????i m???y con tu???n l???c?",
//                "A. 7 con",
//                "B. 8 con",
//                "C. 9 con",
//                "D. 10 con", 3, 5);
//        insertQuestion(q25);
//        Question q26 = new Question("K???t qu??? c???a ph??p t??nh 2+2 l??: ",
//                "A. 4",
//                "B. 1",
//                "C. 5",
//                "D. 2", 1, 1);
//        insertQuestion(q26);
//        Question q27 = new Question("K???t qu??? c???a ph??p t??nh 4+6 l??: ",
//                "A. 8",
//                "B. 10",
//                "C. 24",
//                "D. 2", 2, 1);
//        insertQuestion(q27);
//        Question q28 = new Question("K???t qu??? c???a ph??p t??nh 9x7 l??: ",
//                "A. 43",
//                "B. 16",
//                "C. 56",
//                "D. 63", 4, 1);
//        insertQuestion(q28);
//        Question q29 = new Question("K???t qu??? c???a ph??p t??nh 12:6 l??: ",
//                "A. 4",
//                "B. 1",
//                "C. 6",
//                "D. 2", 4, 1);
//        insertQuestion(q29);
//        Question q30 = new Question("K???t qu??? c???a ph??p t??nh 45-16 l??: ",
//                "A. 32",
//                "B. 61",
//                "C. 29",
//                "D. 28", 3, 1);
//        insertQuestion(q30);
//        Question q31 = new Question("K???t qu??? c???a ph??p t??nh 152-126 l??: ",
//                "A. 26",
//                "B. 28",
//                "C. 20",
//                "D. 24", 1, 1);
//        insertQuestion(q31);
//        Question q32 = new Question("K???t qu??? c???a ph??p t??nh 15x5 l??: ",
//                "A. 70",
//                "B. 75",
//                "C. 80",
//                "D. 90", 2, 1);
//        insertQuestion(q32);
//        Question q33 = new Question("K???t qu??? c???a ph??p t??nh 45:3 l??: ",
//                "A. 15",
//                "B. 13",
//                "C. 12",
//                "D. 16", 1, 1);
//        insertQuestion(q33);
//        Question q34 = new Question("K???t qu??? c???a ph??p t??nh 415+105 l??: ",
//                "A. 502",
//                "B. 520",
//                "C. 255",
//                "D. 525", 2, 1);
//        insertQuestion(q34);
//        Question q35 = new Question("K???t qu??? c???a ph??p t??nh 2002:2 l??: ",
//                "A. 1001",
//                "B. 1020",
//                "C. 1010",
//                "D. 10022", 1, 1);
//        insertQuestion(q34);
//        Question q36 = new Question("K???t qu??? c???a ph??p t??nh 2020+2022 l??: ",
//                "A. 4044",
//                "B. 4004",
//                "C. 4444",
//                "D. 4042", 4, 1);
//        insertQuestion(q36);
//        Question q37 = new Question("K???t qu??? c???a ph??p t??nh 123+456 l??: ",
//                "A. 552",
//                "B. 568",
//                "C. 579",
//                "D. 525", 3, 1);
//        insertQuestion(q37);
//        Question q38 = new Question("K???t qu??? c???a ph??p t??nh 1995-1698 l??: ",
//                "A. 303",
//                "B. 293",
//                "C. 297",
//                "D. 295", 3, 1);
//        insertQuestion(q38);
//        Question q39 = new Question("K???t qu??? c???a ph??p t??nh 415-105 l??: ",
//                "A. 310",
//                "B. 520",
//                "C. 305",
//                "D. 320", 1, 1);
//                insertQuestion(q39);
//        Question q40 = new Question("K???t qu??? c???a ph??p t??nh 12x12 l??: ",
//                "A. 122",
//                "B. 142",
//                "C. 124",
//                "D. 144", 4, 1);
//        insertQuestion(q40);
//        Question q41 = new Question("K???t qu??? c???a ph??p t??nh 169:13 l??: ",
//                "A. 11",
//                "B. 16",
//                "C. 13",
//                "D. 12", 3, 1);
//        insertQuestion(q41);
//        Question q42 = new Question("K???t qu??? c???a ph??p t??nh 7x7 l??: ",
//                "A. 44",
//                "B. 47",
//                "C. 49",
//                "D. 46", 3, 1);
//        insertQuestion(q42);
//        Question q43 = new Question("K???t qu??? c???a ph??p t??nh 167-141 l??: ",
//                "A. 32",
//                "B. 36",
//                "C. 25",
//                "D. 26", 4, 1);
//        insertQuestion(q43);
//        Question q44 = new Question("K???t qu??? c???a ph??p t??nh 26x30 l??: ",
//                "A. 700",
//                "B. 750",
//                "C. 785",
//                "D. 780", 4, 1);
//        insertQuestion(q44);
//
//        Question q45 = new Question("K???t qu??? c???a ph??p t??nh 556+443 l??: ",
//                "A. 1001",
//                "B. 999",
//                "C. 998",
//                "D. 995", 2, 1);
//        insertQuestion(q45);
//        Question q46 = new Question("T??? n??o sau ????y vi???t ????ng? ",
//                "A. S???p s???p",
//                "B. X???p s???p",
//                "C. X???p x???p",
//                "D. S???p x???p", 4, 2);
//        insertQuestion(q46);
//        Question q47 = new Question("T??? n??o sau ????y c?? ti???ng kh??c c??c t??? c??n l???i? ",
//                "A. C???p",
//                "B. C???p",
//                "C. L???p",
//                "D. T???p", 2, 2);
//        insertQuestion(q47);
//        Question q48  = new Question("T??i l?? h???c sinh l???p 1. \n T??? n??o c?? ch???a v???n 'inh'",
//                "A. T??i",
//                "B. L???p",
//                "C. H???c",
//                "D. Sinh", 4, 2);
//        insertQuestion(q48);
//        Question q49  = new Question("??i???n v??o ch??? ch???m: ...inh ?????p.",
//                "A. S",
//                "B. L",
//                "C. H",
//                "D. X", 4, 2);
//        insertQuestion(q49);
//        Question q50  = new Question("T??? n??o vi???t sai ch??nh t????",
//                "A. G??",
//                "B. K???",
//                "C. Qu???",
//                "D. G??", 4, 2);
//        insertQuestion(q50);
//        Question q51  = new Question("T??? n??o sau ch??? ng?????i?",
//                "A. M??o",
//                "B. H???p b??t",
//                "C. ??o len",
//                "D. Th??? m???", 4, 2);
//        insertQuestion(q51);
//        Question q52  = new Question("T??? n??o sau ch??? h??nh ?????ng?",
//                "A. ?????ng h???",
//                "B. ??i h???c",
//                "C. ??o kho??c",
//                "D. Con ch??", 2, 2);
//        insertQuestion(q52);
//        Question q53  = new Question("Ch??? ng??? trong c??u: ''Em l?? h???c sinh l???p 2'' l??:",
//                "A. Em",
//                "B. Em l??",
//                "C. L?? h???c sinh l???p 2",
//                "D. L???p 2", 1, 2);
//        insertQuestion(q53);
//        Question q54  = new Question("V??? Ng??? trong c??u: ''Em l?? h???c sinh l???p 2'' l??:",
//                "A. Em",
//                "B. Em l??",
//                "C. L?? h???c sinh l???p 2",
//                "D. L???p 2", 3, 2);
//        insertQuestion(q54);
//        Question q55  = new Question("C??u n??o sau ????y l?? c??u gi???i thi???u?",
//                "A. Em ??ang ??i h???c",
//                "B. Em ??ang qu??t nh??",
//                "C. Em l?? h???c sinh l???p 2",
//                "D. Em ??ang ??i ch??i", 3, 2);
//        insertQuestion(q55);
//TVTVL3









//    }



}
