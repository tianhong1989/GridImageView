package com.wenen.gridimageview;

import android.widget.ImageView;

/**
 * Created by Administrator on 2016/12/13.
 */

public interface LoadImageCallBack {
  void loadImage(ImageView imageView,String url,int index);
  void onClickResponse(ImageView imageView,String url,int index);
}
