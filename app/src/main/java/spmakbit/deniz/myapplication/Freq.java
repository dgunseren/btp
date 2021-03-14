package spmakbit.deniz.myapplication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;


/**
 *
 * @author DenizGunseren
 */
public class Freq {
    public static int[] ListFrequencies(Integer line_number) {
        int sampling_rate=0;
        int sampling_domain=0;
        sampling_rate=1/line_number;
        sampling_domain=line_number/2;
        int[] frequency=new int[sampling_domain];
        frequency[0]=0;

        for(int i=1;i<sampling_domain;i++){
            frequency[i]=i;
        }
        return frequency;
    }
    public static double[] MaxFrequency(double[] FourierString,int line_number){
        double[] maxes=new double[line_number/2];
        double[] modified=new double[line_number/2];
        for(int i=0;i<maxes.length;i++){
            maxes[i]=FourierString[i];
        }
        Arrays.sort(maxes);

        return maxes;
    }
}
