package com.resatkarakaya.javaworkmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDatabase extends Worker {

    Context myContext;

    public RefreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.myContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData(); // girdiyi al scanf
        int myNumber = data.getInt("intKey",0);
        refreshDatabase(myNumber);
        return Result.success();
    }
    private void refreshDatabase(int myNumber){
        SharedPreferences sharedPreferences = myContext.getSharedPreferences("com.resatkarakaya.javaworkmanager",Context.MODE_PRIVATE); //db
        int mySavedNumber = sharedPreferences.getInt("myNumber",0); //sayı başlangıc
        mySavedNumber = mySavedNumber + 1; // sayıyı artıracak
        System.out.println(mySavedNumber);
        sharedPreferences.edit().putInt("myNumber",mySavedNumber).apply();
    }
}
