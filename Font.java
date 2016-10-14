package com.example.jian.myapplication.util;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import java.util.Arrays;

/**
 * Created by jian on 2016/10/14.
 */
public final class Font extends SpannableString {

    public static Font compose(@NonNull CharSequence... fonts) {
        return compose(Arrays.asList(fonts));
    }

    private static Font compose(Iterable<? extends CharSequence> fontIterable) {
        final SpannableStringBuilder builder = new SpannableStringBuilder();
        for (CharSequence font : fontIterable) {
            builder.append(SpannableString.valueOf(font));
        }

        return Font.valueOf(builder);
    }

    public static Font valueOf(CharSequence source) {
        if (source instanceof Font) {
            return (Font) source;
        } else {
            return new Font(source);
        }
    }

    public Font(@NonNull CharSequence content) {
        super(content);
    }

    /**
     * 设置颜色
     */
    public Font color(Context context, @ColorRes int res) {
        applySpan(new ForegroundColorSpan(context.getResources().getColor(res)));
        return this;
    }

    /**
     * 粗体
     */
    public Font bold() {
        applySpan(new StyleSpan(Typeface.BOLD));
        return this;
    }

    /**
     * 粗 斜体
     */
    public Font boldItalic() {
        applySpan(new StyleSpan(Typeface.BOLD_ITALIC));
        return this;
    }

    /**
     * 斜体
     */
    public Font italic() {
        applySpan(new StyleSpan(Typeface.ITALIC));
        return this;
    }

    /**
     * 设置颜色
     */
    public Font color(int color) {
        applySpan(new ForegroundColorSpan(color));
        return this;
    }

    public Font image(Drawable drawable) {
        applySpan(new ImageSpan(drawable));
        return this;
    }

    public Font image(Context context, @DrawableRes int resId) {
        applySpan(new ImageSpan(context, resId));
        return this;
    }

    /**
     * 设置相对大小
     */
    public Font relativeSize(float size) {
        applySpan(new RelativeSizeSpan(size));
        return this;
    }

    public Font strikethrough() {
        applySpan(new StrikethroughSpan());
        return this;
    }

    public Font underline() {
        applySpan(new UnderlineSpan());
        return this;
    }

    /**
     * 可点击的
     */
    public Font clickableWithUnderline(final View.OnClickListener onClickListener, final int linkColor) {
        applySpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickListener.onClick(widget);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(linkColor);
                ds.setUnderlineText(true);
            }
        });
        return this;
    }

    /**
     * 可点击的
     */
    public Font clickable(final View.OnClickListener onClickListener) {
        applySpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickListener.onClick(widget);
            }
        });
        return this;
    }

    private void applySpan(CharacterStyle style) {
        setSpan(style, 0, length(), SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
