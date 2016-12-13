## GridImageView

<img src="https://img.shields.io/badge/build-passing-green.svg"/>
<img src="https://img.shields.io/badge/release-1.0.2-yellow.svg"/>

[<img src="https://img.shields.io/badge/made%20by-Wenen-blue.svg"/>](http://wenen.site/)

提供一个类似于微信朋友圈、 QQ空间的九宫格控件，根据传入的图片地址或者文字自动适配加载。不限定图片加载框架，可切换至任意一种你喜欢的方式。

<img src="/img/ezgif.com-gif-maker.gif"/>


## 用法

<code><pre>
compile 'com.wenen:gridimageview:1.0.2'
</pre></code>

Activity中设置图片：
<code><pre>
GridImageView relativeLayout = (GridImageView) findViewById(R.id.images);
ImageView[] imageViews = relativeLayout.setImageCount(list.size());
    for (int i = 0; i < imageViews.length; i++) {
      Glide.with(this).load(list.get(i)).into(imageViews[i]);
    }
</pre></code>

XML中：
```
<com.wenen.gridimageview.GridImageView
      android:id="@+id/images"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
/>
```
Activity中设置图片并且设置点击放大的动画：
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

XML中（注意：如果需要设置点击放大的动画，xml根布局务必采用FrameLayout）：
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





