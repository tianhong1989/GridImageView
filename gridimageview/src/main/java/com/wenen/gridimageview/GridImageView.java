package com.wenen.gridimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/12/9.
 */

public class GridImageView extends RelativeLayout {
  public static int screenWidth;
  public static int screenHeight;
  public static int densityDpi;
  public static float density;
  private static final String TAG = "GridImageView";
  private int widthSize;
  private int heightSize;
  private int width;
  private int height;
  private int rowcount;
  private ImageView[] imageViews;
  private int margin;

  public GridImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public GridImageView(Context context) {
    super(context);
    init();
  }

  public GridImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public GridImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init() {
    screenWidth = getResources().getDisplayMetrics().widthPixels;
    screenHeight = getResources().getDisplayMetrics().heightPixels;
    densityDpi = getResources().getDisplayMetrics().densityDpi;
    density = getResources().getDisplayMetrics().density;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    widthSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    heightSize = MeasureSpec.getSize(heightMeasureSpec);
    if (widthMode == MeasureSpec.EXACTLY) {
      width = widthSize;
    } else if (widthMode == MeasureSpec.AT_MOST) {
      width = Math.min(screenWidth, widthSize);
    } else {
      width = widthSize;
    }
    if (heightMode == MeasureSpec.EXACTLY) {
      height = heightSize;
    } else if (heightMode == MeasureSpec.AT_MOST) {
      height = Math.min(screenHeight, heightSize);
    } else {
      height = screenHeight;
    }
    setMeasuredDimension(width, height);
    setImage();
  }
  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

  private void setImage() {
    for (int i = 1; i <= imageViews.length; i++) {
      LayoutParams params =
          new LayoutParams((width - 4 * margin) / rowcount, (width - 4 * margin) / rowcount);
      params.setMargins(margin, margin, 0, 0);
      params.addRule(RelativeLayout.BELOW, i - rowcount);
      if ((i - 1) % rowcount == 0) {
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
      } else {
        params.addRule(RelativeLayout.RIGHT_OF, i - 1);
      }
      if (imageViews[i - 1].getParent() == null) this.addView(imageViews[i - 1], i - 1, params);
    }
  }

  public ImageView[] setImageUrls(int length) {
    imageViews = new ImageView[length];
    for (int i = 0; i < length; i++) {
      ImageView imageView = new ImageView(getContext());
      imageView.setId(i + 1);
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageViews[i] = imageView;
    }
    margin =
        (screenWidth - 4 * (DensityUtil.dip2px(getContext(), 80)) - DensityUtil.dip2px(getContext(),
            8)) / 8;
    if (length <= 4 && length > 1) {
      rowcount = 2;
    } else if (length > 4) {
      rowcount = 3;
    } else {
      rowcount = 1;
    }
    return imageViews;
  }
}
