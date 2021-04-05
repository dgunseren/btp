package spmakbit.deniz.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


public class choose extends FragmentActivity {

    private FileChooserFragment fragment;
    private Button buttonShowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.fragment = (FileChooserFragment) fragmentManager.findFragmentById(R.id.fragment_fileChooser);

        this.buttonShowInfo = this.findViewById(R.id.button_showInfo);

        this.buttonShowInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("succes");
               showInfo();
            }
        });
    }

    private void showInfo()  {
        String path = this.fragment.getPath();
        Toast.makeText(this, "Path: " + path, Toast.LENGTH_LONG).show();
    }
}
