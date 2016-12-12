## GridImageView

提供一个类似于微信朋友圈、 QQ空间的九宫格图片控件，根据传入的图片地址自动适配加载。不限定图片加载框架，可切换至任意一种你喜欢的方式。

<img src="/img/device-2016-12-12-142146.png" width="30%"/>
<img src="/img/device-2016-12-12-150758.png" width="30%"/>
<img src="/img/device-2016-12-12-150834.png" width="30%"/>


## 用法

<code><pre>
compile 'com.wenen:gridimageview:1.0.1'
</pre></code>

Activity中：
<code><pre>
GridImageView relativeLayout = (GridImageView) findViewById(R.id.images);
ImageView[] imageViews = relativeLayout.setImageUrls(list.size());
    for (int i = 0; i < imageViews.length; i++) {
      Glide.with(this).load(list.get(i)).into(imageViews[i]);
    }
</pre></code>

因为返回的是一个ImageView数组，所以你也可以对这个数组进行循环，做你想做的任何事情，包括给ImageView添加动画、点击监听等。

XML中：
```
<com.wenen.gridimageview.GridImageView
      android:id="@+id/images"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
/>
```








