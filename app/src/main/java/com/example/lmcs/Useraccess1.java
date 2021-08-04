package com.example.lmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.File;

public class Useraccess1  {
    public static void load_Directory_Files(File directory) {
        File[] fileList = directory.listFiles();
        if(fileList != null && fileList.length > 0){
            for (int i=0; i<fileList.length; i++){
                if(fileList[i].isDirectory()){
                    load_Directory_Files(fileList[i]);
                }
                else {
                    String name = fileList[i].getName().toLowerCase();
                    for (String extension: Constant.mediaExtensions){

                        if(name.endsWith(extension)){
                            Constant.allMediaList.add(fileList[i]);
                            break;
                        }
                    }
                }
            }
        }
    }

}