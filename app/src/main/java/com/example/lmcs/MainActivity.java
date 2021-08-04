package com.example.lmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.internal.Storage;

import java.io.File;
import java.lang.reflect.Method;


public class MainActivity extends AppCompatActivity {

    private File storage;
    private String[]  allpath;
    private static int SPLASH_SCREEN =1900;


    Animation top,bot;
    ImageView crp;
    TextView txt;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_main);
        allpath = StorageUtil.getStorageDirectories(this);
        top = AnimationUtils.loadAnimation(this,R.anim.top_a);
        bot = AnimationUtils.loadAnimation(this,R.anim.bot_a);

        for (String path: allpath){
            storage = new File(path);
            Useraccess1.load_Directory_Files(storage);
        }


        crp = findViewById(R.id.crp);
        txt = findViewById(R.id.txt);

        crp.setAnimation(top);
        txt.setAnimation(bot);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);
//                startActivity(intent);
//                finish();
                Pair[] pairs = new Pair[2];
                pairs[0]= new Pair<View,String>(crp,"logoimg");
                pairs[1]= new Pair<View,String>(txt,"moto");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(intent,options.toBundle());
            }
        },SPLASH_SCREEN);


    }
}