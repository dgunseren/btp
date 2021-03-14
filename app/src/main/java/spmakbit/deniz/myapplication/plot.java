package spmakbit.deniz.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class plot extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;
    private static final String FILE_NAME_INIT = "FastFourierTransform.txt";
    String[] stringArray_real;
    double[] doubleArray_real;
    double[] amp_sorted;
    double[] doubleArray;
    double[] freqArray;
    double[][] frequency_matrix;
    double [] peak_matrix;
    double[] peakORnot;
    double[] malfunction;



    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_plot);
        int line_number = intent.getIntExtra("line_number", 0);
        stringArray_real = new String[line_number];
        doubleArray_real = new double[line_number];
        doubleArray = new double[line_number];
        freqArray=new double[line_number];
        frequency_matrix=new double[line_number/2][2];
        peak_matrix=new double[line_number/2];
        peakORnot=new double[line_number];
        malfunction=new double[5];
        //amp_sorted= new double[line_number/2];
        GraphView graph = (GraphView) findViewById(R.id.graph);
        FileInputStream fisr = null;
        try {
            int i = 0;
            fisr = openFileInput(FILE_NAME_INIT);
            InputStreamReader isr = new InputStreamReader(fisr);
            BufferedReader br = new BufferedReader(isr);
            while ((text = br.readLine()) != null) {
                if(text.length() > 0) {
                    stringArray_real[i] = text;
                    doubleArray_real[i] =Double .parseDouble(stringArray_real[i]);
                    i++;
                }
            }
            Toast.makeText(this, "Fourier Transform is plotted.", Toast.LENGTH_LONG).show();

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
        double x;

        x = 0;
        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < doubleArray_real.length/2; i++) {
            series.appendData(new DataPoint(x, doubleArray_real[i]), true, line_number );
            if(i<doubleArray_real.length/4){
                doubleArray[i]=doubleArray_real[i];
                freqArray[i]=x;
               // System.out.println(doubleArray[i]);
            }
            x = 2*i;
        }

        peakORnot[0]=0;
        peakORnot[line_number-1]=0;

        for(int h=1;h<doubleArray.length-1;h++){
            if (doubleArray[h-1]<doubleArray[h] && doubleArray[h]>doubleArray[h+1]){
                peakORnot[h]=1;
            }else {peakORnot[h]=0;}
        }
       amp_sorted=Freq.MaxFrequency(doubleArray,line_number);
        int[] index=new int[line_number/2];

        for(int s=line_number/2-1;s>=0;s--){
            for(int y=0;y<line_number/2-1;y++){
                if(amp_sorted[s]==doubleArray[y]){
                    index[s]=y;
                  peak_matrix[s]=peakORnot[y];
                }
            }
        }
        int k;
        for( k=0;k<line_number/2;k++){
            frequency_matrix[k][0]=index[k];
            frequency_matrix[k][1]=peak_matrix[k];
        }
int gh=0;
        for(int le=line_number/2-1;0<=le;le--){
            if(gh==5){break;}
if(frequency_matrix[le][1]!=0){
    malfunction[gh]=frequency_matrix[le][0]*2;
    gh++;

}
        }
        for(int ss=0;ss<5;ss++){
            System.out.println(malfunction[ss]);

        }

       //System.out.println(Arrays.deepToString(malfunction).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

//        int[] index=new int[amp_sorted.length];
////
//        for(int s=line_number/2-1;s>=0;s--){
//            for(int y=0;y<line_number/2-1;y++){
//                if(amp_sorted[s]==doubleArray[y]){
//                    index[s]=y;
////                    // peak_matrix[s]=peakORnot[y];
//                   // System.out.println(index[s]);
//                }
//            }
//        }
        graph.addSeries(series);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(1020);


        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);

    }


    public void openActivityMain(View v) {
        Intent intent_1 = new Intent(this, MainActivity.class);
        startActivity(intent_1);
    }
    public void openError(View v) {
        Intent intent_2 = new Intent(this, error.class);
        intent_2.putExtra("malfunction", malfunction);
        startActivity(intent_2);
    }




}