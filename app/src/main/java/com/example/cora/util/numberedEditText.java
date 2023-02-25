package com.example.cora.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

public class numberedEditText extends androidx.appcompat.widget.AppCompatEditText {
    private Rect rect;
    private Paint paint;

    private int lineNumberMarginGap = 16;
    protected int LINE_NUMBER_TEXTSIZE_GAP = 2;
    private boolean lineNumberVis = true;

    public numberedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        rect = new Rect();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.LTGRAY);
        paint.setTextSize(getTextSize() - LINE_NUMBER_TEXTSIZE_GAP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (lineNumberVis) {
            paint.setTextSize(getTextSize() - LINE_NUMBER_TEXTSIZE_GAP);

            int baseLine = getBaseline();
            String t = "";
            for (int i = 0; i < getLineCount(); i++) {
                t = "" + (i + 1);
                canvas.drawText(t, rect.left, baseLine, paint);
                baseLine += getLineHeight();
            }

            // set padding again, adjusting only the left padding
            setPadding((int)paint.measureText(t) + lineNumberMarginGap, getPaddingTop(),
                    getPaddingRight(), getPaddingBottom());
        }
        super.onDraw(canvas);
    }

    public int getLineNumberMarginGap() {
        return lineNumberMarginGap;
    }

    public void setLineNumberMarginGap(int lineNumberMarginGap) {
        this.lineNumberMarginGap = lineNumberMarginGap;
    }

    public int getLINE_NUMBER_TEXTSIZE_GAP() {
        return LINE_NUMBER_TEXTSIZE_GAP;
    }

    public void setLINE_NUMBER_TEXTSIZE_GAP(int LINE_NUMBER_TEXTSIZE_GAP) {
        this.LINE_NUMBER_TEXTSIZE_GAP = LINE_NUMBER_TEXTSIZE_GAP;
    }

    public boolean isLineNumberVis() {
        return lineNumberVis;
    }

    public void setLineNumberVis(boolean lineNumberVis) {
        this.lineNumberVis = lineNumberVis;
    }

    public void setLineNumberTextColor(int textColor){
        paint.setColor(textColor);
    }

    public int getLineNumberTextColor(){
        return paint.getColor();
    }
}
