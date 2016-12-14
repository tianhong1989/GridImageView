## GridImageView

<img src="https://img.shields.io/badge/release-1.0.7-yellow.svg"/>

[<img src="https://img.shields.io/badge/made%20by-Wenen-blue.svg"/>](http://wenen.site/)

提供一个网格图片控件，可以根据图片数量自动适配加载，当只有一张图片时，自适应当前控件大小。采用接口的方式加载图片以及给每张图片设置监听事件，因此，可以无缝切换至任何一种你喜欢的图片加载方式。控件内置一个ZoomAnimation,用于图片的点击放大

<img src="/img/ezgif.com-gif-maker.gif"/>


## usage

<code><pre>
compile 'com.wenen:gridimageview:1.0.7'
</pre></code>

#### 1. Activity必须实现LoadImageCallBack接口，用于加载图片以及设置监听：
<pre>
public class MainActivity extends AppCompatActivity implements LoadImageCallBack{
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
</pre>
#### 2. 先获取控件:
<pre>
gridImageView = (GridImageView) findViewById(R.id.images);
</pre>

#### 3. 将图片地址的总数以及LoadImageCallBack的具体实现传递给GridImageView：
<pre>
gridImageView.setImage(list.size, this,context);
</pre>

#### 4. 重写LoadImageCallBack的方法：
<pre>
@Override
  public void loadImage(ImageView imageView, int index) {
    //load your image
  }
  @Override
  public void onClickResponse(ImageView view, int index) {
    //add the ClickListener of your ImageView
  }
</pre>

#### XML：
```
<com.wenen.gridimageview.GridImageView
      android:id="@+id/images"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
/>
```
#### 给每个图片设置事件：
<code><pre>
  @Override
  public void onClickResponse(ImageView view, int index) {
    Glide.with(this).load(list.get(index)).into(imageView);
    gridImageView.zoomImageFromThumb(view, imageView,
        (FrameLayout) findViewById(R.id.activity_main));
  }
</pre></code>

#### XML（如果你想要给每个图片使用内置的Zoom效果，XML的根布局必须为FrameLayout）：
```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.wenen.gridimageviewsample.MainActivity"
    >
    <com.wenen.gridimageview.GridImageView
        android:id="@+id/images"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
  <ImageView
      android:id="@+id/expanded_image"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:contentDescription="@string/description_zoom_touch_close"
      android:visibility="invisible"
      />
</FrameLayout>
```









