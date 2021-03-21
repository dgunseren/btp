package spmakbit.deniz.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.IOException;

public class error extends AppCompatActivity {
    String mystring = "";
    int[][] occ = new int[15][1];


    String[][] string_mat = new String[15][1];
    Button mButton;
    EditText mEdit_1;
    EditText mEdit_2;
    TextView mText;
    double kanat_say = 0;
    double cal_fre = 0;
    String error_0_10 = "- Pompada (EVN’den daha kötü) devridaim (recirculation)\n" +
            "- Rotorun eksenel olarak yanlış hizalanması,\n" +
            "- Besleme hattının yerleşimi,\n" +
            "- Çark ile ön ve arka yanakları arasındaki boşluk.";
    String error_3_15 = "-Boru hattının titreşimleri";
    String error_005_025 = "- Akış problemleri,\n" +
            "- Kanatsız difüzör kararsızlığı.";
    String error_01_04 = "-Kavitasyon";
    String error_04_05 = "- Dinamik kararsızlık,\n" +
            "- Sürtünme/sürtme,\n" +
            "- Az yüklü kaymalı yataklarda yağlama (oil whip/whirl) problemleri,\n" +
            "- Yataklarda gevşeklik veya fazlaca yatak boşluğu.";
    String error_05_075 = "-Gevşek yatak parça ve boşluklarının rezonansa girmesi sonucunda kısmi debilerde ortaya çıkan\n" +
            "“rotor rotating stall”.";
    String error_07_085 = "- Çark çıkışı ile difüzör girişi geometrileri arasındaki uyumsuzluktan kaynaklanan hidrolik\n" +
            "kararsızlık,\n" +
            "- Nadiren rotorun doğal frekansları.";
    String error_1 = "- Mekanik dengesizlik,\n" +
            "- Yanlış hizalama,\n" +
            "- Eğik şaft,\n" +
            "- Çark üzerindeki düzgün olmayan kanat aralıklarından kaynaklanan hidrolik dengesizlik,\n" +
            "- Şaft salgısı,\n" +
            "- Fazla yatak boşluğu,\n" +
            "- Çark ile ön ve arka yanakları arasında fazla boşluk,\n" +
            "- Çarkın eksenel yanlış hizalanması,\n" +
            "- Yatak kapaklarının gevşekliği.";
    String error_11x_12x = "Dönen kavitasyon";
    String error_2x = "- Yanlış hizalanması,\n" +
            "- Gevşek iç bileşenler,\n" +
            "- Çift salyangozlu pompalarda da görülebilir.\n" +
            "- Bazen sürtünme nedeniyle oluşan termal etkilerle eğilen şaftlardan kaynaklanır.";
    String error_2x_3x = "Gevşeklik";
    String error_kx = "- Kanat geçişleri,\n" +
            "- Çark uçunun difüzör-çark giriş kenarlarına yakınlığı,\n" +
            "- Çarkın yanlış hizalanması,\n" +
            "- Kavitelerin akustik doğal frekansları ve rezonansları,\n" +
            "- Çark ön yanak geometrisinin bozulması.";
    String error_2kx = "- Kanat geçişleri,\n" +
            "- Çark uçunun difüzör-çark giriş kenarlarına yakınlığı,\n" +
            "- Çarkın yanlış hizalanması,\n" +
            "- Kavitelerin akustik doğal frekansları ve rezonansları,\n" +
            "- Çark ön yanak geometrisinin bozulması.";
    String error_5x_50x = "-Rulmanlar (Titreşimler pompa kapanırken de devam ederse).";
    String error_6x_12xx = "-Değişken frekanslı sürücüler";
    ///  Bundle bundle;
    plot a1 = new plot();
    double[][] malfunction = a1.malfunction;
    //Double[][] malfunction = (Double[][]) bundle.getSerializable("malfunction");

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        mButton = (Button) findViewById(R.id.button1);
        mEdit_1 = findViewById(R.id.editText1);
        mEdit_2 = findViewById(R.id.editText2);
        string_mat[0][0] = error_0_10;
        string_mat[1][0] = error_3_15;
        string_mat[2][0] = error_005_025;
        string_mat[3][0] = error_01_04;
        string_mat[4][0] = error_04_05;
        string_mat[5][0] = error_05_075;
        string_mat[6][0] = error_07_085;
        string_mat[7][0] = error_1;
        string_mat[8][0] = error_11x_12x;
        string_mat[9][0] = error_2x;
        string_mat[10][0] = error_2x_3x;
        string_mat[11][0] = error_kx;
        string_mat[12][0] = error_2kx;
        string_mat[13][0] = error_5x_50x;
        string_mat[14][0] = error_6x_12xx;


    }

    public void openplot(View v) {
        Intent intent_3 = new Intent(this, error.class);
        startActivity(intent_3);
    }

    public void showAlertDialogButtonClicked(View view) {
        String text_1 = mEdit_1.getText().toString();
        if (text_1 == null) {
            System.out.println(0);
        } else {
            kanat_say = Double.valueOf(text_1);
        }
        String text_2 = mEdit_2.getText().toString();
        if (text_2 == null) {
            System.out.println(0);
        } else {
            cal_fre = Double.valueOf(text_2);
        }
        System.out.println(kanat_say);
        System.out.println(cal_fre);
        // setup the alert builder


        for (int i = 0; i < 5; i++) {

            if (malfunction[i][0] == 0) {
                break;
            }
            System.out.println(malfunction[i][0]);
            if (Double.valueOf(1) < malfunction[i][0] && Double.valueOf(10) > malfunction[i][0]) {
                if (occ[0][0] ==0) {
                    mystring = mystring + "\n" + "!!!!ERROR!!!!" + "\n" + error_0_10 + "!!!!ERROR!!!!" + "\n";
                    occ[0][0] = occ[0][0] + 1;
                }else{;}



            }
            if (Double.valueOf(3) < malfunction[i][0] && Double.valueOf(15) > malfunction[i][0]) {

                if (occ[1][0] ==0) {
                    mystring = mystring + "\n" + "!!!!ERROR!!!!" + "\n" + error_3_15 + "!!!!ERROR!!!!" + "\n";
                    occ[1][0] = occ[1][0] + 1;
                }else{;}


            }

            if (Double.valueOf(0.005) * cal_fre < malfunction[i][0] && Double.valueOf(0.25) * cal_fre > malfunction[i][0]) {

                if (occ[2][0] ==0) {
                    mystring = mystring + "\n" + "!!!!ERROR!!!!" + "\n" + error_01_04 + "!!!!ERROR!!!!" + "\n";
                    occ[2][0] = occ[2][0] + 1;
                }else{;}


            }
            if (Double.valueOf(0.4) * cal_fre < malfunction[i][0] && Double.valueOf(0.5) * cal_fre > malfunction[i][0]) {

                if (occ[3][0] ==0) {
                    mystring = mystring + "\n" + "!!!!ERROR!!!!" + "\n" + error_04_05 + "!!!!ERROR!!!!" + "\n";
                    occ[3][0] = occ[3][0] + 1;
                }else{;}


            }
            if (Double.valueOf(0.5) * cal_fre < malfunction[i][0] && Double.valueOf(0.75) * cal_fre > malfunction[i][0]) {

                if (occ[4][0] ==0) {
                    mystring = mystring + "\n" + "!!!!ERROR!!!!" + "\n" + error_05_075 + "!!!!ERROR!!!!" + "\n";
                    occ[4][0] = occ[4][0] + 1;
                }else{;}


            }
            if (Double.valueOf(0.7) * cal_fre < malfunction[i][0] && Double.valueOf(0.85) * cal_fre > malfunction[i][0]) {

                if (occ[5][0] ==0) {
                    mystring = mystring + "\n" + "!!!!ERROR!!!!" + "\n" + error_07_085 + "!!!!ERROR!!!!" + "\n";
                    occ[5][0] = occ[5][0] + 1;
                }else{;}


            }
            if (Double.valueOf(0.95) * cal_fre < malfunction[i][0] && Double.valueOf(1.05) * cal_fre > malfunction[i][0]) {

                if (occ[6][0] ==0) {
                    mystring = mystring + "\n" + "!!!!ERROR!!!!" + "\n" + error_1 + "!!!!ERROR!!!!" + "\n";
                    occ[6][0] = occ[6][0] + 1;
                }else{;}


            }
            if (Double.valueOf(1.1) * cal_fre < malfunction[i][0] && Double.valueOf(1.2) * cal_fre > malfunction[i][0]) {

                if (occ[7][0] ==0) {
                    mystring = mystring + "\n" + "!!!!ERROR!!!!" + "\n" + error_11x_12x + "!!!!ERROR!!!!" + "\n";
                    occ[7][0] = occ[7][0] + 1;
                }else{;}


            }
            if (Double.valueOf(1.9) * cal_fre < malfunction[i][0] && Double.valueOf(2.1) * cal_fre > malfunction[i][0]) {

                if (occ[8][0] == 0) {
                    mystring = mystring + "\n" + "!!!!ERROR!!!!" + "\n" + error_kx + "!!!!ERROR!!!!" + "\n";
                    occ[8][0] = occ[8][0] + 1;
                }else{;}


            }
//          else  if(Double.valueOf(0)< malfunction[i][0]|Double.valueOf(10)>malfunction[i][0]){
//                mystring=mystring+"\n"+"!!!!ERROR!!!!"+"\n"+error_2kx+"!!!!ERROR!!!!"+"\n";
//
//            }
//          else  if(Double.valueOf(0)< malfunction[i][0]|Double.valueOf(10)>malfunction[i][0]){
//                mystring=mystring+"\n"+"!!!!ERROR!!!!"+"\n"+error_5x_50x+"!!!!ERROR!!!!"+"\n";
//
//
//            }
//          else  if(Double.valueOf(0)< malfunction[i][0]|Double.valueOf(10)>malfunction[i][0]){
//                mystring=mystring+"\n"+"!!!!ERROR!!!!"+"\n"+error_6x_12xx+"!!!!ERROR!!!!"+"\n";


            //           }
            else {
                mystring = mystring + " ";

            }


        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Check for Following Faults");
        builder.setMessage(mystring);
        // add a button
        builder.setPositiveButton("OK", null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();


//        public void INIT (View view){
//            String text_1 = mEdit_1.getText().toString();
//            if (text_1 == null) {
//                System.out.println(0);
//            } else {
//                kanat_say = Double.valueOf(text_1);
//            }
//            String text_2 = mEdit_2.getText().toString();
//            if (text_2 == null) {
//                System.out.println(0);
//            } else {
//                cal_fre = Double.valueOf(text_2);
//            }
//            System.out.println(kanat_say);
//            System.out.println(cal_fre);
//
//        }
    }
}
