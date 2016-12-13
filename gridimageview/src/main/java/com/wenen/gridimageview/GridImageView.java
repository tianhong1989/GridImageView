package com.wenen.gridimageview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/9.
 */

public class GridImageView extends ScrollView implements View.OnClickListener {
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
  private int margin;
  private int length;
  private RelativeLayout relativeLayout;
  private RelativeLayout.LayoutParams params;
  private Animator mCurrentAnimator;
  private LoadImageCallBack myLoadImageCallBack;
  private ArrayList<String> listUrl;
  /**
   * The system "short" animation time duration, in milliseconds. This duration is ideal for
   * subtle animations or animations that occur very frequently.
   */
  private int mShortAnimationDuration;

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
    mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
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
      Log.e(TAG, "onMeasure: " + "height" + heightSize + "screenHeight:" + screenHeight);
    } else {
      height = heightSize;
    }
    setMeasuredDimension(width, height);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    this.setVerticalScrollBarEnabled(false);
    setLayout();
  }

  private void setLayout() {
    int realWidth = (width - 4 * margin) / rowcount;
    if (relativeLayout == null) {
      relativeLayout = new RelativeLayout(getContext());
      Log.e(TAG, "setLayout: " + realWidth * clucount);
      relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(width, realWidth * clucount));
    }
    if (height > realWidth * clucount && realWidth * clucount > 0) {
      ViewGroup.LayoutParams params = this.getLayoutParams();
      params.height = realWidth * clucount + margin * clucount;
      this.requestLayout();
    }
    if (relativeLayout.getParent() == null) {
      this.addView(relativeLayout);
    }
    //  if (length > 9) {
    for (int i = 0; i < length; i++) {
      params = new RelativeLayout.LayoutParams(realWidth, realWidth);
      params.setMargins(margin, margin, 0, 0);
      params.addRule(RelativeLayout.BELOW, i + 1 - rowcount);
      if (i % rowcount == 0) {
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
      } else {
        params.addRule(RelativeLayout.RIGHT_OF, i);
      }
      if (imageViews != null) {
        if (imageViews[i].getParent() == null) {
          relativeLayout.addView(imageViews[i], i, params);
        }
      }
      //  if (i == 8) {
      //    TextView textView = new TextView(getContext());
      //    textView.setTextSize(DensityUtil.px2sp(getContext(), realWidth / 2));
      //    textView.setTextColor(getResources().getColor(R.color.textColor));
      //    textView.setText(length - 9 + ">>");
      //    relativeLayout.addView(textView, i, params);
      //  }
      //}
    }
  }

  public void setImage(ArrayList<String> list, LoadImageCallBack loadImageCallBack) {
    this.myLoadImageCallBack = loadImageCallBack;
    this.length = list.size();
    this.listUrl=list;
    imageViews = new ImageView[length];
    for (int i = 0; i < length; i++) {
      imageViews[i] = new ImageView(getContext());
      imageViews[i].setId(i + 1);
      imageViews[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
      myLoadImageCallBack.loadImage(imageViews[i], list.get(i));
      imageViews[i].setOnClickListener(this);
    }
    margin =
        (screenWidth - 4 * (DensityUtil.dip2px(getContext(), 90)) - DensityUtil.dip2px(getContext(),
            8)) / 8;
    if (length > 1) {
      rowcount = 3;
      clucount = length / 3 + length % 3;
    } else {
      rowcount = 1;
      clucount = 1;
    }
  }

  public void zoomImageFromThumb(final View thumbView, final ImageView expandedImageView,
      FrameLayout frameLayout) {
    // If there's an animation in progress, cancel it
    // immediately and proceed with this one.
    if (mCurrentAnimator != null) {
      mCurrentAnimator.cancel();
    }
    // Calculate the starting and ending bounds for the zoomed-in image.
    // This step involves lots of math. Yay, math.
    final Rect startBounds = new Rect();
    final Rect finalBounds = new Rect();
    final Point globalOffset = new Point();

    // The start bounds are the global visible rectangle of the thumbnail,
    // and the final bounds are the global visible rectangle of the container
    // view. Also set the container view's offset as the origin for the
    // bounds, since that's the origin for the positioning animation
    // properties (X, Y).
    thumbView.getGlobalVisibleRect(startBounds);
    frameLayout.getGlobalVisibleRect(finalBounds, globalOffset);
    startBounds.offset(-globalOffset.x, -globalOffset.y);
    finalBounds.offset(-globalOffset.x, -globalOffset.y);

    // Adjust the start bounds to be the same aspect ratio as the final
    // bounds using the "center crop" technique. This prevents undesirable
    // stretching during the animation. Also calculate the start scaling
    // factor (the end scaling factor is always 1.0).
    float startScale;
    if ((float) finalBounds.width() / finalBounds.height()
        > (float) startBounds.width() / startBounds.height()) {
      // Extend start bounds horizontally
      startScale = (float) startBounds.height() / finalBounds.height();
      float startWidth = startScale * finalBounds.width();
      float deltaWidth = (startWidth - startBounds.width()) / 2;
      startBounds.left -= deltaWidth;
      startBounds.right += deltaWidth;
    } else {
      // Extend start bounds vertically
      startScale = (float) startBounds.width() / finalBounds.width();
      float startHeight = startScale * finalBounds.height();
      float deltaHeight = (startHeight - startBounds.height()) / 2;
      startBounds.top -= deltaHeight;
      startBounds.bottom += deltaHeight;
    }

    // Hide the thumbnail and show the zoomed-in view. When the animation
    // begins, it will position the zoomed-in view in the place of the
    // thumbnail.
    thumbView.setAlpha(0f);
    expandedImageView.setVisibility(View.VISIBLE);

    // Set the pivot point for SCALE_X and SCALE_Y transformations
    // to the top-left corner of the zoomed-in view (the default
    // is the center of the view).
    expandedImageView.setPivotX(0f);
    expandedImageView.setPivotY(0f);

    // Construct and run the parallel animation of the four translation and
    // scale properties (X, Y, SCALE_X, and SCALE_Y).
    AnimatorSet set = new AnimatorSet();
    set.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left, finalBounds.left))
        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
        .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
        .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
    set.setDuration(mShortAnimationDuration);
    set.setInterpolator(new DecelerateInterpolator());
    set.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(Animator animation) {
        mCurrentAnimator = null;
      }

      @Override public void onAnimationCancel(Animator animation) {
        mCurrentAnimator = null;
      }
    });
    set.start();
    mCurrentAnimator = set;

    // Upon clicking the zoomed-in image, it should zoom back down
    // to the original bounds and show the thumbnail instead of
    // the expanded image.
    final float startScaleFinal = startScale;
    expandedImageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (mCurrentAnimator != null) {
          mCurrentAnimator.cancel();
        }

        // Animate the four positioning/sizing properties in parallel,
        // back to their original values.
        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
            .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
            .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
            .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            thumbView.setAlpha(1f);
            expandedImageView.setVisibility(View.GONE);
            mCurrentAnimator = null;
          }

          @Override public void onAnimationCancel(Animator animation) {
            thumbView.setAlpha(1f);
            expandedImageView.setVisibility(View.GONE);
            mCurrentAnimator = null;
          }
        });
        set.start();
        mCurrentAnimator = set;
      }
    });
  }

  @Override public void onClick(View view) {
    myLoadImageCallBack.onClickResponse(imageViews[view.getId()-1],listUrl.get(view.getId()-1));
  }
}
