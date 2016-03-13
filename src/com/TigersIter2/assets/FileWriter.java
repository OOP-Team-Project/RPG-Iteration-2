package com.TigersIter2.assets;

import java.io.*;

/**
 * Created by slichtenheld on 3/12/2016.
 */
public class FileWriter {

    public static void stringToFile(String fileName, String fileContent){

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"))){
            writer.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String intToString(int val){
        try{
            return Integer.toString(val);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return "-1";
        }
    }
}

