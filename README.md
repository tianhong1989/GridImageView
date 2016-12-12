## GridImageView

提供一个类似于微信朋友圈、 QQ空间的九宫格控件，根据传入的图片地址或者文字自动适配加载。不限定图片加载框架，可切换至任意一种你喜欢的方式。

<img src="/img/device-2016-12-12-142146.png" width="30%"/>
<img src="/img/device-2016-12-12-150758.png" width="30%"/>
<img src="/img/device-2016-12-12-150834.png" width="30%"/>
<img src="/img/device-2016-12-12-180412.png" width="30%"/>


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

Activity中设置文字：

<pre>
 String string = "蜀道难，难于上青天！";
    char[] chars = string.toCharArray();
    TextView[] textViews = relativeLayout.setTextViewCount(chars.length);
    for (int i = 0; i < textViews.length; i++) {
      textViews[i].setText(String.valueOf(chars[i]));
    }
</pre>

XML中：
```
<com.wenen.gridimageview.GridImageView
      android:id="@+id/images"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
/>
```








