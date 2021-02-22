package com.resatkarakaya.javaworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.admin.DelegatedAdminReceiver;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.concurrent.TimeUnit;

import javax.xml.transform.Source;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data data = new Data.Builder().putInt("intKey",1).build();

        Constraints constraints = new Constraints.Builder()
                //.setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(false)
                .build();

        /* //Tek bir kere request yapar
        WorkRequest workRequest = new OneTimeWorkRequest.Builder(RefreshDatabase.class)
                .setConstraints(constraints)
                .setInputData()
                //.setInitialDelay(5, TimeUnit.MINUTES)
                //.addTag("myTag")
                .build();

        WorkManager.getInstance(this).enqueue(workRequest);
        */
         WorkRequest workRequest = new PeriodicWorkRequest.Builder(RefreshDatabase.class,15,TimeUnit.MINUTES) //15 dk da bir bu işi yap
                 .setConstraints(constraints)
                 .setInputData(data).build();

         WorkManager.getInstance(this).enqueue(workRequest);//uygulama kapalıyken veya alt taraftayken 15 dk da bir artması gerekiyor

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) { // durumlara göre kullanıcıya mesaj verir
                if(workInfo.getState() == WorkInfo.State.RUNNING){
                    System.out.println("running");
                }
                else if(workInfo.getState() == WorkInfo.State.SUCCEEDED){
                    System.out.println("succeded");
                }
                else if(workInfo.getState() == WorkInfo.State.FAILED){
                    System.out.println("failed");
                }
            }
        });

        // WorkManager.getInstance(this).cancelAllWork(); // fail olursa işlemi iptal eder

        //Chaining (arka arkaya iş yaptırmak istediğimizde)
        /*
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(RefreshDatabase)
                .setInputData(data)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).beginWith(oneTimeWorkRequest)
                .then(oneTimeWorkRequest)
                .then(oneTimeWorkRequest)
                .enqueue(); // birbirine bağlamış oldu */


    }
}