package com.example.camera_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b;
    ImageView i;
    public static final int RC_PIC_CODE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.capturebtn);
        i=findViewById(R.id.image);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capture();
            }
        });
    }

    public void capture() {
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,RC_PIC_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_PIC_CODE){
            if(resultCode==RESULT_OK){
                Bitmap b= (Bitmap) data.getExtras().get("data");
                i.setScaleType(ImageView.ScaleType.FIT_CENTER);
                i.setImageBitmap(b);
            }
            else if (resultCode==RESULT_CANCELED){
                Toast.makeText(this,"cancelled",Toast.LENGTH_SHORT).show();

            }
        }
    }
}

