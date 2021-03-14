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

    Button mButton;
    EditText mEdit_1;
    EditText mEdit_2;
    TextView mText;
    double kanat_say=0;
    double cal_fre=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        mButton = (Button) findViewById(R.id.button1);
        mEdit_1 = findViewById(R.id.editText1);
        mEdit_2 = findViewById(R.id.editText2);

    }

    public void openplot(View v) {
        Intent intent_3 = new Intent(this, error.class);
        startActivity(intent_3);
    }

    public void showAlertDialogButtonClicked(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("My title");

        builder.setMessage("This is my message.");

        // add a button
        builder.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void INIT(View view) {
        String text_1 = mEdit_1.getText().toString();
        if(text_1 == null){
            System.out.println(0);
        }else{kanat_say = Double.valueOf(text_1);}
        String text_2 = mEdit_2.getText().toString();
        if(text_2 == null){
            System.out.println(0);
        }else{cal_fre = Double.valueOf(text_2);}
        System.out.println(kanat_say);
        System.out.println(cal_fre);

    }
}
