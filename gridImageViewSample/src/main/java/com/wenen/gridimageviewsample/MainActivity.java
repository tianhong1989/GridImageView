package com.wenen.gridimageviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.wenen.gridimageview.GridImageView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  private ArrayList<String> list = new ArrayList<>();
  private static final String TAG = "MainActivity";
  private ImageView imageView;
  private GridImageView relativeLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    relativeLayout = (GridImageView) findViewById(R.id.images);
    imageView = (ImageView) findViewById(R.id.expanded_image);
    list.add("http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add("http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add("http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add("http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add("http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add("http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add("http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add("http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add("http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add("http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add("http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    ImageView[] imageViews = relativeLayout.setImageCount(list.size());
    for (int i = 0; i < imageViews.length; i++) {
      Glide.with(this).load(list.get(i)).into(imageViews[i]);
      final int finalI = i;
      imageViews[i].setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          Glide.with(MainActivity.this).load(list.get(finalI)).into(imageView);
          relativeLayout.zoomImageFromThumb(view,imageView,
              (FrameLayout) findViewById(R.id.activity_main));
        }
      });
    }
  }
}
