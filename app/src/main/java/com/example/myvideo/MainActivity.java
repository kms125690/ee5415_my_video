package com.example.myvideo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final String LOGTAG = "video";
    private VideoView videoView;
    private TextView webUrl;
    private Button resVideoButton, sdVideoButton, webVideoButton;

    public static String[] storge_permissions = { Manifest.permission.READ_EXTERNAL_STORAGE };
//    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
//    public static String[] storge_permissions_33 = {
//            Manifest.permission.READ_MEDIA_AUDIO,
//            Manifest.permission.READ_MEDIA_VIDEO
//    };
    public static String[] permissions() {
        String[] p;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            p = storge_permissions_33;
//        } else {
//            p = storge_permissions;
//        }
        p = storge_permissions;
        return p;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, permissions(), REQUEST_CODE);
        resVideoButton = (Button) findViewById(R.id.button1);
        sdVideoButton = (Button) findViewById(R.id.button2);
        webVideoButton = (Button) findViewById(R.id.button3);
        webUrl = (EditText) findViewById(R.id.editText1);
        videoView = (VideoView) findViewById(R.id.videoView1);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        videoView.setMediaController(mc);
        resVideoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });

        sdVideoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                File file = new File(Environment.getExternalStorageDirectory(), "videoSD.mp4");
                File file = new File("/storage/emulated/0/Download/videoSD.mp4");
                Uri uri = Uri.fromFile(file);
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });

        webVideoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse(webUrl.getText().toString());
                videoView.setVideoURI(uri);
                videoView.start();
            }
        });

    }
}