package com.example.fooddelivery;

import android.app.Application;

public class MyApp extends Application {
    private String mGlobalVariable="";

    public String getmGlobalVariable(){
        return mGlobalVariable;
    }
    public void setGlobalVarValue(String str){
        mGlobalVariable += str+"\n";
    }
    public void SetGlobalClear(){
        mGlobalVariable = "";
    }

}
