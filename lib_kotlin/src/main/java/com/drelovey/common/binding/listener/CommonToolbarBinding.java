package com.drelovey.common.binding.listener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.drelovey.common.utils.LibUtils;


@SuppressWarnings("Convert2Lambda")
public class CommonToolbarBinding {
    //点击返回键
    public static BindingGenericity<View> backCommand = new BindingGenericity<View>() {
        @SuppressLint("NewApi")
        @Override
        public void call(View view) {
            final Activity activity = LibUtils.getActivityFromView(view);
            if (activity != null) {
                activity.onBackPressed();
            }
        }
    };
    //关闭activity
    public static BindingGenericity<View> finishCommand = new BindingGenericity<View>() {
        @Override
        public void call(View view) {
            final Activity activity = LibUtils.getActivityFromView(view);
            if (activity != null) {
                activity.finish();
            }
        }
    };

}
