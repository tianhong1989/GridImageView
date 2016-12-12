package com.wenen.gridimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/9.
 */

public class GridImageView extends ScrollView {
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
  private int clucount;
  private ImageView[] imageViews;
  private TextView[] textViews;
  private int margin;
  private int length;
  private RelativeLayout relativeLayout;
  private RelativeLayout.LayoutParams params;

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
      Log.e(TAG, "onMeasure: "+"height"+heightSize+"screenHeight:"+screenHeight);
    } else {
      height = heightSize;
    }
    setMeasuredDimension(width, height);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    setLayout();
  }

  private void setLayout() {
    if (relativeLayout == null) {
      relativeLayout = new RelativeLayout(getContext());
      Log.e(TAG, "setLayout: "+(width - 4 * margin) / rowcount*clucount);
      relativeLayout.setLayoutParams(
          new RelativeLayout.LayoutParams(width,(width - 4 * margin) / rowcount*clucount));
    }
    if (height>(width - 4 * margin) / rowcount*clucount&&(width - 4 * margin) / rowcount*clucount>0){
      this.setLayoutParams(new RelativeLayout.LayoutParams(width,(width - 4 * margin) / rowcount*clucount+margin*clucount));
    }
    if (relativeLayout.getParent() == null){
       this.addView(relativeLayout);
    }
    for (int i = 1; i <= length; i++) {
      params = new RelativeLayout.LayoutParams((width - 4 * margin) / rowcount, (width - 4 * margin) / rowcount);
      params.setMargins(margin, margin, 0, 0);
      params.addRule(RelativeLayout.BELOW, i - rowcount);
      if ((i - 1) % rowcount == 0) {
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
      } else {
        params.addRule(RelativeLayout.RIGHT_OF, i - 1);
      }
      if (imageViews != null) {
        if (imageViews[i - 1].getParent() == null) {
          relativeLayout.addView(imageViews[i - 1], i - 1, params);
        }
      }
      if (textViews != null) {
        if (textViews[i - 1].getParent() == null) {
          relativeLayout.addView(textViews[i - 1], i - 1, params);
        }
      }
    }
  }

  public ImageView[] setImageCount(int length) {
    this.length = length;
    imageViews = new ImageView[length];
    for (int i = 0; i < length; i++) {
      imageViews[i] = new ImageView(getContext());
      imageViews[i].setId(i + 1);
      imageViews[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
    margin =
        (screenWidth - 4 * (DensityUtil.dip2px(getContext(), 80)) - DensityUtil.dip2px(getContext(),
            8)) / 8;
    if (length <= 4 && length > 1) {
      rowcount = 2;
      clucount=length/2+length%2;
    } else if (length > 4) {
      rowcount = 3;
      clucount=length/3+length%3;
    } else {
      rowcount = 1;
      clucount=1;
    }
    return imageViews;
  }

  public TextView[] setTextViewCount(int length) {
    this.length = length;
    margin =
        (screenWidth - 4 * (DensityUtil.dip2px(getContext(), 80)) - DensityUtil.dip2px(getContext(),
            8)) / 8;
    if (length <= 4 && length > 1) {
      rowcount = 2;
      clucount=length/2+length%2;
    } else if (length > 4) {
      rowcount = 3;
      clucount=length/3+length%3;
    } else {
      rowcount = 1;
      clucount=1;
    }
    textViews = new TextView[length];
    for (int i = 0; i < length; i++) {
      textViews[i] = new TextView(getContext());
      textViews[i].setGravity(Gravity.CENTER);
      textViews[i].setTextSize(width - margin / rowcount);
      textViews[i].setId(i + 1);
    }
    return textViews;
  }
}
