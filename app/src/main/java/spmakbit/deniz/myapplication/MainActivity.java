   /*
  Bachelors Project for Mechanical Engineering
  Spring 2021
   @Author: Deniz Gunseren
   Correspondence:dgunseren@gmail.com


  */
package spmakbit.deniz.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    EditText mEditText;

    private static final String FILE_NAME_REAL = "incomingreal_depre.txt";
    private static final String FILE_NAME_IMG = "incomingreal_depre.txt";
    private static final String FILE_NAME_INIT = "FastFourierTransform.txt";
    private static final String FILE_NAME_DUMMY="incomingreal.txt";
    public static int line_number;
    double[] input_real;
    double[] input_img;
    double[] amp;
    String[] stringArray_real;
    String[] stringArray_img;
    String[] stringArray_real_ch;
    String[] stringArray_img_ch;
    String[] stringArray_amp;
    String[] stringArray_dummy;
    double[] dummy_series;
    double[] dummy_sine;
    double x=0;
    int i = 0;
    int encounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.edit_text);
    }
    public void blu(View v) {
        Intent intent_new = new Intent(this, blu_activity.class);
        startActivity(intent_new);
    }
    public void explore(View v){
        Intent intent_newer = new Intent(this, choose.class);
        startActivity(intent_newer);

    }
    public void LineCounter(View v) {
        FileInputStream fisr = null;
        encounter=1;
        try {
            fisr = openFileInput(FILE_NAME_DUMMY);
            InputStreamReader isr = new InputStreamReader(fisr);
            BufferedReader br = new BufferedReader(isr);
            line_number = 0;
            while (br.readLine() != null) {
                if(line_number==2048){break;}
                line_number = line_number + 1;
            }
            input_real = new double[line_number];
            input_img = new double[line_number];
            stringArray_real = new String[line_number];
            stringArray_img = new String[line_number];
            stringArray_real_ch = new String[line_number];
            stringArray_img_ch = new String[line_number];
            stringArray_amp = new String[line_number];
            amp = new double[line_number];
            Toast.makeText(this, "Data Set Consists of: " + line_number + " elements.", LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fisr != null) {
                try {
                    fisr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void dummy_operation(View v){
        line_number=2048;
        dummy_series= new double[2048];
        dummy_sine= new double[2048];
        stringArray_dummy= new String[2048];
        StringBuilder sb_dummy = new StringBuilder();
        stringArray_real= new String[2048];
        stringArray_img= new String[2048];
        input_real = new double[2048];
        input_img = new double[2048];
        stringArray_amp = new String[2048];
        amp = new double[2048];
        for (int i = 0; i < dummy_series.length; i++) {
            x=x+0.00048828125;
            dummy_series[i]=x;
        }
        for (int i=0;i<dummy_series.length;i++){
            dummy_sine[i]=Math.sin(dummy_series[i]*120)+Math.sin(dummy_series[i]*420)+Math.sin(dummy_series[i]*1080)+1.1*Math.sin(dummy_series[i]*716)+1.3*Math.sin(dummy_series[i]*1716)+2.3*Math.sin(dummy_series[i]*20563)+2.3*Math.sin(dummy_series[i]*163);
            stringArray_real[i]=String.valueOf(dummy_sine[i]);
            stringArray_img[i]=String.valueOf(dummy_sine[i]*0);
            input_real[i]=dummy_sine[i];
            input_img[i]=dummy_sine[i]*0;
            System.out.println(stringArray_real[i]);
           sb_dummy.append(stringArray_real[i]).append("\n");
        }
        mEditText.setText(sb_dummy.toString());
    }

    public void openActivity2(View v) {
        Intent intent = new Intent(this, plot.class);
        intent.putExtra("line_number", line_number);
        startActivity(intent);
    }
    public void save(View v) {
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME_INIT, MODE_PRIVATE);
            fos.write(text.getBytes());
            mEditText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME_INIT,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void load(View v) {
        FileInputStream fisr = null;
        try {
            fisr = openFileInput(FILE_NAME_DUMMY);
            InputStreamReader isr = new InputStreamReader(fisr);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            i=0;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
                if(text.length() > 0) {
                    if(i==2048){break;}
            input_real[i]=Double.valueOf(text);
            input_img[i]=Double.valueOf(0);

                }
                input_real[0]=0;
                i++;

            }
            mEditText.setText(sb.toString());
            Toast.makeText(this, "Data taken from:" + getFilesDir() + "/" + FILE_NAME_DUMMY, LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fisr != null) {
                try {
                    fisr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    double[] amp_sorted= new double[line_number/2];

    public void FFT_INIT(View v) {
        Fft.transform(input_real, input_img);
        Toast.makeText(this, "Fourier Transform Initialized", LENGTH_SHORT).show();
        for (int i = 0; i < line_number; i++) {
            Complex phasor = new Complex(input_real[i], input_img[i]);
            amp[i] = phasor.abs()/line_number;
            stringArray_amp[i] = Double.toString(amp[i]);
           // System.out.println(amp[i]);
        }
       amp_sorted= Freq.MaxFrequency(amp,line_number);
        mEditText.getText().clear();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line_number; i++) {
            sb.append(stringArray_amp[i]);
            sb.append("\n");
        }
        int[] index=new int[line_number/2];

        for(int s=line_number/2-1;s>=0;s--){
            for(int y=0;y<line_number/2-1;y++){
                if(amp_sorted[s]==amp[y]){
                    index[s]=y;
                    //peak_matrix[s]=peakORnot[y];
                }
            }
        }
        for(int i=0;i<line_number/2-1;i++)
        {
          // System.out.println(index[i]);

        }
        mEditText.setText(sb.toString());
    }
}
