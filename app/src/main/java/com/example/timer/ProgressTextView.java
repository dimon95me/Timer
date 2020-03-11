package com.example.timer;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;

public class ProgressTextView extends androidx.appcompat.widget.AppCompatTextView {
    private int mMaxValue = 100;

    public ProgressTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ProgressTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressTextView(Context context) {
        super(context);
    }

    public void setMaxValue(int maxValue) {
        mMaxValue = maxValue;
    }

    public synchronized void setValue(int value) {
        this.setText(String.valueOf(value));

        LayerDrawable background = (LayerDrawable) this.getBackground();

        ClipDrawable barValue = (ClipDrawable) background.getDrawable(1);

        int newClipLevel = (int) (value * 10000 / mMaxValue);
        barValue.setLevel(newClipLevel);

        drawableStateChanged();
    }
}