package com.zfx.mvvmtvshows.utilities;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BindingAdapters {
    @BindingAdapter("android:imageURL")
    public static void setImageURL(ImageView imageView, String URL){
        try {
            imageView.setAlpha(0f);
//            load() 方法用于指定要加载的图片的 URL
//            noFade() 方法用于禁用淡入淡出效果，也就是图片加载完成后立即显示，而不是有一个渐变的过程。
//            最后，into() 方法用于指定图片要显示到哪个 ImageView 中

//            Picasso.get().load(URL).noFade().into 的作用是从指定的 URL 加载一张图片，
//            并将其显示到指定的 ImageView 中，同时禁用淡入淡出效果。
            Picasso.get().load(URL).noFade().into(imageView, new Callback() {
                @Override
                public void onSuccess() {

//                    imageView.animate() 获取一个 ViewPropertyAnimator 对象，它可以用于对指定的 View 执行动画。
//                    setDuration(300) 方法用于设置动画的持续时间，单位为毫秒。在这个例子中，持续时间为 300 毫秒，也就是动画会在 0.3 秒内完成。
//                    alpha(1f) 方法用于设置 View 在动画结束时的透明度，取值范围为 0（完全透明）到 1（完全不透明）。在这个例子中，透明度被设置为 1，也就是动画结束后 View 完全不透明。
//                    start() 方法用于开始执行动画，使设置的动画效果生效。

//                    综合起来，imageView.animate().setDuration(300).alpha(1f).start()
//                    的作用是对指定的 ImageView 执行一个 300 毫秒的淡入动画，使其从完全透明变为完全不透明。

                    imageView.animate().setDuration(300).alpha(1f).start();
                }

                @Override
                public void onError(Exception e) {
                    Log.d("onError", "setImageURL: " + e.getMessage());
                }
            });
        }
        catch (Exception exception){
            Log.d("Exception", "setImageURL: " + exception.getMessage());
        }
    }
}
