package com.example.materialdialogx;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.materialdialogx2.CustomDialog;
import com.example.materialdialogx2.DialogBuilder;
import com.example.materialdialogx2.DialogClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DialogBuilder(this)
                .setTitle(getResources().getString(R.string.app_name))  // Dialog Title
                .setDescription("Test Android Custom Dialog")           // Dialog Description
                .setCancelable(false)                                   // Cancelable (true OR false)
                .setFontForAllViews(Typeface.createFromAsset(getAssets(), "vip_rawy_regular.otf")) // Change Typeface Font For All Views
                .setTitleFont(Typeface.createFromAsset(getAssets(), "vip_rawy_regular.otf")) // Change Typeface Font For Title
                .setDescriptionFont(Typeface.createFromAsset(getAssets(), "vip_rawy_regular.otf")) // Change Typeface Font For Description
                .setButtonsFont(Typeface.createFromAsset(getAssets(), "vip_rawy_regular.otf")) // Change Typeface Font For Buttons
                .setDialogBackgroundDrawable(R.drawable.dialog_background)// Change Dialog Background Drawable
                .setIcon(DialogBuilder.WARNING_ICON) // set Icon(DialogBuilder.SUCCESS_ICON || DialogBuilder.WARNING_ICON || DialogBuilder.ERROR_ICON)
                .setAnimation(DialogBuilder.FROM_BOTTOM)// set Animation (DialogBuilder.FROM_BOTTOM || DialogBuilder.FROM_TOP)
                .setPositiveListener("OK", new DialogClickListener() { // Positive Button
                    @Override
                    public void onClick(CustomDialog dialog) {
                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeListener("NO", new DialogClickListener() { // Negative Button
                    @Override
                    public void onClick(CustomDialog dialog) {
                        Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .build().show();

    }
}
