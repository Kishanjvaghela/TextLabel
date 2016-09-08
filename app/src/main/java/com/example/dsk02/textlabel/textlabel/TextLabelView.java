package com.example.dsk02.textlabel.textlabel;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * http://stackoverflow.com/a/30617116/3758898
 * Created by DSK02 on 9/8/2016.
 */

public class TextLabelView extends View {
  private Layout mLayout;

  public TextLabelView(Context context) {
    this(context, null);
  }

  public TextLabelView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TextLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setFocusable(true);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.save();
    if (mLayout != null) {
      canvas.translate(getPaddingLeft(), getPaddingTop());
      mLayout.draw(canvas);
    }
    canvas.restore();
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    long t1 = System.currentTimeMillis();
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (mLayout != null) {
      setMeasuredDimension(getPaddingLeft() + getPaddingRight() + mLayout.getWidth(),
          getPaddingTop() + getPaddingBottom() + mLayout.getHeight());
    }
  }

  public void setTextLayout(Layout layout) {
    mLayout = layout;
    requestLayout();
  }

  public static class Builder {

    private String text;
    private int textSize;
    private int textColor;

    /**
     * set text content
     */
    public Builder setText(String text) {
      this.text = text;
      return this;
    }

    /**
     * Set the text size. This value must be > 0
     */
    public Builder setTextSize(int textSize) {
      this.textSize = textSize;
      return this;
    }

    public Layout build() {
      TextPaint textPaint = new TextPaint();
      textPaint.setTextSize(textSize);
      textPaint.setColor(textColor);
      return new StaticLayout(text, textPaint, (int) textPaint.measureText(text),
          Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true);
    }

    public void setTextColor(int textColor) {
      this.textColor = textColor;
    }
  }
}
