## GridImageView

[中文说明](/ChineseReadMe.md)

<img src="https://img.shields.io/badge/release-1.0.7-yellow.svg"/>

[<img src="https://img.shields.io/badge/made%20by-Wenen-blue.svg"/>](http://wenen.site/)

Provide a grid ImageView, according to the image of the incoming address automatically load. Unlimited image loading frame, you can switch to any one of the ways you like.

<img src="/img/ezgif.com-gif-maker.gif"/>


## usage

<code><pre>
compile 'com.wenen:gridimageview:1.0.7'
</pre></code>

#### 1. implements the callback in the Activity：
<pre>
public class MainActivity extends AppCompatActivity implements LoadImageCallBack{
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
</pre>
#### 2. get the view:
<pre>
gridImageView = (GridImageView) findViewById(R.id.images);
</pre>

#### 3. set the list size of ImageUrl
<pre>
gridImageView.setImage(list.size(), this,context);
</pre>

#### 4. Override the callback to load image and add a listener
<pre>
@Override
  public void loadImage(ImageView imageView,int index) {
    //load your image
  }
  @Override
  public void onClickResponse(ImageView view,int index) {
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
#### Load image in the Activity and set a zoom animation：
<code><pre>
  @Override
  public void onClickResponse(ImageView view, int index) {
    Glide.with(this).load(list.get(index)).into(imageView);
    gridImageView.zoomImageFromThumb(view, imageView,
        (FrameLayout) findViewById(R.id.activity_main));
  }
</pre></code>

#### XML（tips：if you want to set a zoom animation,the xml root must be FrameLayout）：
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









