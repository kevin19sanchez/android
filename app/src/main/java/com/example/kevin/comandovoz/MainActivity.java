package com.example.kevin.comandovoz;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.speech.RecognizerResultsIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView img1;
    Button btn;
    TextView tv;
    private Integer[] imgid = {
            R.drawable.movil2,
            R.drawable.movil4
    };
    int indice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.image1);
        btn = (Button) findViewById(R.id.btnenceder);
        tv = (TextView) findViewById(R.id.tv1);

        img1.setImageResource(imgid[indice]);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "HABLA");

                try {
                    startActivityForResult(intent, 100);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Lo siento no es soportado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK && intent != null) {
                    ArrayList<String> result = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tv.setText(result.get(0));

                    if (result.get(0).equals("siguiente")) {
                        indice++;

                        if (indice > imgid.length) {
                            Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                            indice=0;
                        }else{
                            img1.setImageResource(imgid[indice]);
                        }
                    } else {
                        if (result.get(0).equals("anterior")) {
                            indice--;

                            if (indice < 0) {
                                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                                indice=0;
                            }
                            else{
                                img1.setImageResource(imgid[indice]);
                            }
                        } else {
                            if (result.get(0).equals("información")) {
                                Uri uri = Uri.parse("http://www.andreaardions.com/");
                                Intent in1 = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(in1);
                            }else {

                            }

                        }

                    }
                    break;
                }
        }
    }
}
//ArrayList<MainActivity> img = new ArrayList<MainActivity>();
//img.add(new MainActivity();