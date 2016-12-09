package com.wenen.gridimageviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.wenen.gridimageview.GridImageView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  private ArrayList<String> list = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    GridImageView relativeLayout = (GridImageView) findViewById(R.id.images);
    list.add("http://58.250.126.213:81/scripts/phone/banner01.jpg");
    list.add("http://58.250.126.213:81/scripts/phone/banner02.jpg");
    list.add("http://58.250.126.213:81/scripts/phone/banner03.jpg");
    list.add("http://58.250.126.213:81/scripts/phone/banner01.jpg");
    list.add("http://58.250.126.213:81/scripts/phone/banner02.jpg");
    list.add("http://58.250.126.213:81/scripts/phone/banner03.jpg");
    list.add("http://58.250.126.213:81/scripts/phone/banner01.jpg");
    list.add("http://58.250.126.213:81/scripts/phone/banner02.jpg");
    list.add("http://58.250.126.213:81/scripts/phone/banner03.jpg");
    ImageView[] imageViews = relativeLayout.setImageUrls(list.size());
    for (int i = 0; i < imageViews.length; i++) {
      Glide.with(this).load(list.get(i)).into(imageViews[i]);
    }
  }
}
