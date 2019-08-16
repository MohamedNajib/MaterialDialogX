package com.example.materialdialogx2;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import static com.example.materialdialogx2.DialogBuilder.ERROR_ICON;
import static com.example.materialdialogx2.DialogBuilder.FROM_BOTTOM;
import static com.example.materialdialogx2.DialogBuilder.FROM_TOP;
import static com.example.materialdialogx2.DialogBuilder.SUCCESS_ICON;
import static com.example.materialdialogx2.DialogBuilder.WARNING_ICON;


public class CustomDialog {

    private ImageView imageViewIcon;
    private TextView dialogButtonOk, dialogButtonNo;
    private TextView title_lbl, subtitle_lbl;
    private ConstraintLayout dialog_Container;
    private View separator;
    private boolean negativeExist;

    private DialogClickListener positiveListener;
    private DialogClickListener negativeListener;

    private String mAnimation = null;

    private Dialog mDialog;
    private Animation mFromTop, mFromBottom;
    private WindowManager.LayoutParams mLayoutParams;

    public CustomDialog(Context context, String title, String subtitle, boolean cancelable, Typeface typeFace, Typeface descriptionTypeface,
                        Typeface buttonsFont, Typeface fontAll, int backgroundDrawable, String icon) {

        negativeExist = false;
        createDialog(context, cancelable, title, subtitle, typeFace, descriptionTypeface, buttonsFont, fontAll, backgroundDrawable, icon);
        initEvents();
    }

    private void createDialog(Context context, boolean cancelable, String title, String subtitle, Typeface typeFace, Typeface descriptionTypeface,
                              Typeface buttonsFont, Typeface fontAll, int backgroundDrawable, String icon) {
        mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_layout);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setCancelable(cancelable);

        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.copyFrom(mDialog.getWindow().getAttributes());
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        mFromTop = AnimationUtils.loadAnimation(context, R.anim.fromtop);
        mFromBottom = AnimationUtils.loadAnimation(context, R.anim.frombottom);

        initViews();
        setFontForAllViews(fontAll);
        setTitleTypefaces(typeFace);
        setDescriptionTypefaces(descriptionTypeface);
        setButtonsTypefaces(buttonsFont);
        setDialogBackgroundDrawable(context, backgroundDrawable);
        setTitleText(title);
        setDescriptionText(subtitle);
        setIcon(context, icon);

    }

    private void setIcon(Context context, String icon) {
        if (icon != null) {
            if (icon.equals(SUCCESS_ICON)) {
                setImageVisible();
                imageViewIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_done));

            } else if (icon.equals(ERROR_ICON)) {
                setImageVisible();
                imageViewIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_clear));

            } else if (icon.equals(WARNING_ICON)) {
                setImageVisible();
                imageViewIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_priority));
            }
        }
    }

    private void setImageVisible(){
        imageViewIcon.setVisibility(View.VISIBLE);
    }

    private void setFontForAllViews(Typeface fontAll) {
        if (fontAll != null) {
            title_lbl.setTypeface(fontAll);
            subtitle_lbl.setTypeface(fontAll);
            dialogButtonOk.setTypeface(fontAll);
            dialogButtonNo.setTypeface(fontAll);
        }
    }

    private void setTitleText(String title) {
        if (title != null) {
            title_lbl.setText(title);
        }
    }

    private void setDescriptionText(String title) {
        if (title != null) {
            subtitle_lbl.setText(title);
        }
    }

    private void setTitleTypefaces(Typeface appleFont) {
        if (appleFont != null) {
            title_lbl.setTypeface(appleFont);
        }
    }

    private void setDescriptionTypefaces(Typeface appleFont) {
        if (appleFont != null) {
            subtitle_lbl.setTypeface(appleFont);
        }
    }

    private void setButtonsTypefaces(Typeface appleFont) {
        if (appleFont != null) {
            dialogButtonOk.setTypeface(appleFont);
            dialogButtonNo.setTypeface(appleFont);
        }
    }

    private void setDialogBackgroundDrawable(Context context, int backgroundDrawable) {
        if (backgroundDrawable != 0) {
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                dialog_Container.setBackgroundDrawable(ContextCompat.getDrawable(context, backgroundDrawable));
            } else {
                dialog_Container.setBackground(ContextCompat.getDrawable(context, backgroundDrawable));
            }
        }
    }

    private void initViews() {
        imageViewIcon = mDialog.findViewById(R.id.icon);
        title_lbl = mDialog.findViewById(R.id.title);
        subtitle_lbl = mDialog.findViewById(R.id.subtitle);
        dialogButtonOk = mDialog.findViewById(R.id.dialogButtonOK);
        dialogButtonNo = mDialog.findViewById(R.id.dialogButtonNO);
        separator = mDialog.findViewById(R.id.separator);
        dialog_Container = mDialog.findViewById(R.id.dialog_Container);

        imageViewIcon.setVisibility(View.GONE);
    }

    public void show() {
        if (!negativeExist) {
            dialogButtonNo.setVisibility(View.GONE);
            separator.setVisibility(View.GONE);
        }
        setAnimationFrom(mAnimation);

        mDialog.show();
        mDialog.getWindow().setAttributes(mLayoutParams);
    }

    private void setAnimationFrom(String mAnimation) {
        if (mAnimation != null) {
            if (mAnimation.equals(FROM_TOP)) {
                dialog_Container.setAnimation(mFromTop);

            } else if (mAnimation.equals(FROM_BOTTOM)) {
                dialog_Container.setAnimation(mFromBottom);
            }
        }
    }

    public void setPositive(String positive, DialogClickListener listener) {
        positiveListener = listener;
        dismiss();
        dialogButtonOk.setText(positive);
    }

    public void setNegative(String negative, DialogClickListener listener) {
        if (listener != null) {
            negativeListener = listener;
            dismiss();
            negativeExist = true;
            dialogButtonNo.setText(negative);
        }
    }

    private void initEvents() {
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positiveListener != null) {
                    positiveListener.onClick(CustomDialog.this);
                }
            }
        });
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (negativeListener != null) {
                    negativeListener.onClick(CustomDialog.this);
                }
            }
        });
    }

    public void setAnimation(String animation) {
        this.mAnimation = animation;
    }

    public void dismiss() {
        mDialog.dismiss();
    }


}
