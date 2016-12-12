package com.wenen.gridimageviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.wenen.gridimageview.GridImageView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  private ArrayList<String> list = new ArrayList<>();
  private static final String TAG = "MainActivity";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    GridImageView relativeLayout = (GridImageView) findViewById(R.id.images);
    //list.add("http://58.250.126.213:81/scripts/phone/banner01.jpg");
    //list.add("http://58.250.126.213:81/scripts/phone/banner02.jpg");
    //list.add("http://58.250.126.213:81/scripts/phone/banner03.jpg");
    //
    //ImageView[] imageViews = relativeLayout.setImageCount(list.size());
    //for (int i = 0; i < imageViews.length; i++) {
    //  Glide.with(this).load(list.get(i)).into(imageViews[i]);
    //  final int finalI = i;
    //  imageViews[i].setOnClickListener(new View.OnClickListener() {
    //    @Override public void onClick(View view) {
    //      Toast.makeText(MainActivity.this," 点击了第"+ finalI+"个ImageView", Toast.LENGTH_SHORT).show();
    //    }
    //  });
    //}
    String string = "蜀道难，难于上青天！";
    char[] chars = string.toCharArray();
    TextView[] textViews = relativeLayout.setTextViewCount(chars.length);
    for (int i = 0; i < textViews.length; i++) {
      textViews[i].setText(String.valueOf(chars[i]));
    }
  }
}
