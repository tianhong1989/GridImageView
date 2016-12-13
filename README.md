## GridImageView

[中文文档](/ChineseReadMe.md)

<img src="https://img.shields.io/badge/build-passing-green.svg"/>
<img src="https://img.shields.io/badge/release-1.0.3-yellow.svg"/>

[<img src="https://img.shields.io/badge/made%20by-Wenen-blue.svg"/>](http://wenen.site/)

Provide a grid ImageView, according to the image of the incoming address automatically load. Unlimited image loading frame, you can switch to any one of the ways you like.

<img src="/img/ezgif.com-gif-maker.gif"/>


## usage

<code><pre>
compile 'com.wenen:gridimageview:1.0.3'
</pre></code>

Load image in the Activity：
<code><pre>
GridImageView relativeLayout = (GridImageView) findViewById(R.id.images);
ImageView[] imageViews = relativeLayout.setImageCount(list.size());
    for (int i = 0; i < imageViews.length; i++) {
      Glide.with(this).load(list.get(i)).into(imageViews[i]);
    }
</pre></code>

XML：
```
<com.wenen.gridimageview.GridImageView
      android:id="@+id/images"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
/>
```
Load image in the Activity and set a zoom animation：
<code><pre>
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
</pre></code>

XML（tips：if you want to set a zoom animation,the xml root must be FrameLayout）：
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









