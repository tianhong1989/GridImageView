package com.wenen.gridimageviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.wenen.gridimageview.GridImageView;
import com.wenen.gridimageview.LoadImageCallBack;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoadImageCallBack {
  private ArrayList<String> list = new ArrayList<>();
  private static final String TAG = "MainActivity";
  private ImageView imageView;
  private GridImageView gridImageView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    gridImageView = (GridImageView) findViewById(R.id.images);
    imageView = (ImageView) findViewById(R.id.expanded_image);
    list.add(
        "http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add(
        "http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add(
        "http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add(
        "http://a.hiphotos.baidu.com/zhidao/pic/item/5366d0160924ab18fffea7dd31fae6cd7a890b6b.jpg");
    list.add("http://pic1.win4000.com/wallpaper/c/5493ddddbd3f3.jpg");
    list.add("http://pic1.win4000.com/wallpaper/c/53d715df6274d.jpg");
    list.add(
        "http://r002.joyme.com/r002/image/2013/03/61/d1847040-d17f-4065-a746-a84d61326fb6.jpg");
    list.add(
        "http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add(
        "http://g.hiphotos.baidu.com/zhidao/pic/item/503d269759ee3d6d735fb9cf40166d224f4ade1c.jpg");
    list.add("http://imgsrc.baidu.com/forum/pic/item/d4c907f3d7ca7bcbb5c421cabe096b63f424a8e4.jpg");
    list.add("http://pic65.nipic.com/file/20150420/8684504_003711453149_2.jpg");
    list.add(
        "http://f.hiphotos.baidu.com/zhidao/pic/item/8b82b9014a90f60326b707453b12b31bb051eda9.jpg");
    list.add(
        "http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add(
        "http://c.hiphotos.baidu.com/zhidao/pic/item/b03533fa828ba61e9a43845d4734970a304e5916.jpg");
    list.add(
        "http://a.hiphotos.baidu.com/zhidao/pic/item/5366d0160924ab18fffea7dd31fae6cd7a890b6b.jpg");
    list.add("http://pic1.win4000.com/wallpaper/c/5493ddddbd3f3.jpg");
    list.add("http://pic1.win4000.com/wallpaper/c/53d715df6274d.jpg");
    list.add(
        "http://r002.joyme.com/r002/image/2013/03/61/d1847040-d17f-4065-a746-a84d61326fb6.jpg");
    list.add(
        "http://e.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=75aaa91fa444ad342eea8f83e59220c2/0bd162d9f2d3572cf556972e8f13632763d0c388.jpg");
    list.add(
        "http://g.hiphotos.baidu.com/zhidao/pic/item/503d269759ee3d6d735fb9cf40166d224f4ade1c.jpg");
    list.add("http://imgsrc.baidu.com/forum/pic/item/d4c907f3d7ca7bcbb5c421cabe096b63f424a8e4.jpg");
    gridImageView.setImage(list, this, getApplicationContext());
    MyApplication.getRefWatcher(getApplicationContext()).watch(gridImageView, "gridImageView溢出");
    MyApplication.getRefWatcher(getApplicationContext()).watch(list, "list溢出");
  }
  @Override public void loadImage(ImageView imageView, String url, int index) {
    Glide.with(this).load(url).into(imageView);
  }
  @Override public void onClickResponse(ImageView view, String url, int index) {
    Glide.with(MainActivity.this).load(url).into(imageView);
    gridImageView.zoomImageFromThumb(view, imageView,
        (FrameLayout) findViewById(R.id.activity_main));
    Log.e(TAG, "onClickResponse: "+index);
  }
  @Override protected void onDestroy() {
    super.onDestroy();
    list.clear();
    list = null;
    imageView=null;
    gridImageView = null;
  }
}
