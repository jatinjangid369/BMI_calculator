package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ImageView logo = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView2);

        // Load the fade-in animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Start the animation
        logo.startAnimation(fadeIn);
        textView.startAnimation(fadeIn);

        // Transition to the main activity after the animation ends
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                // Start your main activity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish(); // Finish the splash activity
            }

            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        getSupportActionBar().hide();
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(5000);

                }catch(Exception e){
                    e.printStackTrace();

                }finally {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class );
                            startActivity(intent);

                }
            }
        };thread.start();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}