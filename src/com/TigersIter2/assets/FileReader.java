package com.TigersIter2.assets;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by slichtenheld on 3/6/2016.
 */
public class FileReader {

    //reads in Text as one long string
    public static String fileToString(String filePath){
        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
            String line;
            while((line = br.readLine())!= null) //while haven't hit end of file
                builder.append(line + "\n");

            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static int stringToInt(String stringNum){
        try{
            return Integer.parseInt(stringNum);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}
