# MaterialDialogX

[![](https://jitpack.io/v/MohamedNajib/MaterialDialogX.svg)](https://jitpack.io/#MohamedNajib/MaterialDialogX)

MaterialDialogX is a simple and beautiful Base Dialog which allows to create simple dialogs

### MaterialDialogX library
---
![s](https://user-images.githubusercontent.com/50467719/63168395-4e89d900-c034-11e9-9d73-72348250c551.PNG)![e](https://user-images.githubusercontent.com/50467719/63168420-5e092200-c034-11e9-9d8f-00a5cc1e419c.PNG)![w](https://user-images.githubusercontent.com/50467719/63168537-b2ac9d00-c034-11e9-9d89-f20f878e3f2c.PNG)![n](https://user-images.githubusercontent.com/50467719/63168438-6b261100-c034-11e9-99de-5c0439e24c75.PNG)

### How to integrate the ToastMessag library in your app?
---

##### Step 1. Add it in your root `build.gradle` at the end of repositories:
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
##### Step 2.
##### For `AndroidX` Add the dependency in your `build.gradle` file
  * make sure the version matches the JitPack badge above
```java
dependencies {
        implementation 'com.github.MohamedNajib:MaterialDialogX:0.1.0'
}
```
##### If you still using `Android support library` in your android apps Add the dependency in your `build.gradle` file
```java
dependencies {
	      implementation 'com.github.MohamedNajib:MaterialDialog:0.1.0'
}
```


### Usage
---
```javascript
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


```
