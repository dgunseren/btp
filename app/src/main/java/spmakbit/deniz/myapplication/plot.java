package spmakbit.deniz.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class plot extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;
    private static final String FILE_NAME_INIT = "FastFourierTransform.txt";
    String[] stringArray_real;
    Double[] doubleArray_real;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_plot);
        int line_number = intent.getIntExtra("line_number", 0);
        stringArray_real = new String[line_number/2];
        doubleArray_real = new Double[line_number/2];
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
                    if(i>=stringArray_real.length){
                        break;
                    }
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
        for (int i = 0; i < (line_number/2); i++) {
            series.appendData(new DataPoint(x, doubleArray_real[i]), true, line_number / 2);
            x = 2*i;
        }
        graph.addSeries(series);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(1028);


        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);

    }


    public void openActivityMain(View v) {
        Intent intent_1 = new Intent(this, MainActivity.class);
        startActivity(intent_1);
    }

}