package com.example.materialdialogx2;

import android.content.Context;
import android.graphics.Typeface;

public class DialogBuilder {

    private Context mContext;
    private String mTitle;
    private String mSubtitle;

    private int backgroundDrawable = 0;

    private Typeface titleTypeface, descriptionTypeface, buttonsFont, fontAll;

    private boolean mCancelable;
    private DialogClickListener mPositiveListener;
    private DialogClickListener mNegativeListener;

    private String mPositiveLabel, mNegativeLabel;
    private String mAnimation = null;

    private String icon;

    public static final String FROM_TOP = "FromTop";
    public static final String FROM_BOTTOM = "FromBottom";

    public static final String SUCCESS_ICON = "SUCCESS_ICON";
    public static final String WARNING_ICON = "WARNING_ICON";
    public static final String ERROR_ICON = "ERROR_ICON";

    public DialogBuilder(Context context) {
        this.mContext = context;
    }

    public DialogBuilder setIcon (String icon){
        this.icon = icon;
        return this;
    }

    public DialogBuilder setAnimation(String animation) {
        this.mAnimation = animation;
        return this;
    }

    public DialogBuilder setDialogBackgroundDrawable(int backgroundDrawable){
        this.backgroundDrawable = backgroundDrawable;
        return this;
    }

    public DialogBuilder setFontForAllViews(Typeface typeface) {
        this.fontAll = typeface;
        return this;
    }

    public DialogBuilder setTitleFont(Typeface typeface) {
        this.titleTypeface = typeface;
        return this;
    }

    public DialogBuilder setDescriptionFont(Typeface typeface) {
        this.descriptionTypeface = typeface;
        return this;
    }

    public DialogBuilder setButtonsFont(Typeface typeface) {
        this.buttonsFont = typeface;
        return this;
    }

    public DialogBuilder setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public DialogBuilder setDescription(String description) {
        this.mSubtitle = description;
        return this;
    }

    public DialogBuilder setCancelable(boolean cancelable) {
        this.mCancelable = cancelable;
        return this;
    }

    public DialogBuilder setNegativeListener(String negativeText, DialogClickListener listener) {
        this.mNegativeListener = listener;
        this.mNegativeLabel = negativeText;
        return this;
    }

    public DialogBuilder setPositiveListener(String positiveText, DialogClickListener listener) {
        this.mPositiveListener = listener;
        this.mPositiveLabel = positiveText;
        return this;
    }

    public CustomDialog build() {
        CustomDialog dialog = new CustomDialog(mContext, mTitle, mSubtitle, mCancelable,
                titleTypeface, descriptionTypeface, buttonsFont, fontAll, backgroundDrawable, icon);
        dialog.setAnimation(mAnimation);
        dialog.setNegative(mNegativeLabel, mNegativeListener);
        dialog.setPositive(mPositiveLabel, mPositiveListener);
        return dialog;
    }
}
