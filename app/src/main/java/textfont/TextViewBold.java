package textfont;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;


/**
 * Created by Admin on 12/4/2018.
 */

public class TextViewBold extends androidx.appcompat.widget.AppCompatTextView {

    public TextViewBold(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
        this.setTypeface(face);
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
        this.setTypeface(face);
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf");
        this.setTypeface(face);
    }

//    protected void onDraw (Canvas canvas) {
//        super.onDraw(canvas);
//
//
//    }
}
