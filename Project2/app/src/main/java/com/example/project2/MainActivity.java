package com.example.project2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static Button button;
    public static TextView textView;
    public static ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

        button = findViewById(R.id.buttonV1);

        textView = findViewById(R.id.textV1);


        img1 = findViewById(R.id.imageV1);
        img2 = findViewById(R.id.imageV2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();
                int num1 = random.nextInt(6);
                Random random1 = new Random();
                int num2 = random.nextInt(6);

                if (num1 > num2) {
                    textView.setText("Kazanan : Oyuncu");
                } else if (num2 > num1) {
                    textView.setText("Kazanan : BOT");
                } else {
                    textView.setText("Sonuç : Berabere");
                }
                img1.setImageResource(dice[num1]);
                img2.setImageResource(dice[num2]);
            }
        });
    }
}
