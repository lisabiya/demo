package com.example.administrator.myapplication.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.HashMap;


public class AnimationUtil {
    public enum AnimationType {
        PRESS, ROTATION, MOVE_X
    }

    //存放View 是否在执行动画中
    private static HashMap<String, Boolean> animationMap = new HashMap<>();

    //按下动画
    public static void doAnimation(View view, AnimationType type) {
        String name = view.getId() + type.name();
        if (animationMap.get(name) == null || animationMap.get(name)) {
            switch (type) {
                case PRESS:
                    doYAnimation(view);
                    break;
                case ROTATION:
                    doRotationAnimation(view);
                    break;
                case MOVE_X:
                    doXAnimation(view, 100);
                    break;
            }
        }
    }

    //上下滑动
    private static void doYAnimation(final View view) {
        float y = view.getY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "y", y, y + 30, y);
        animator.setDuration(500);
        animator.setRepeatCount(0);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animationMap.put(view.getId() + AnimationType.PRESS.name(), false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationMap.put(view.getId() + AnimationType.PRESS.name(), true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    //旋转
    private static void doRotationAnimation(final View view) {
        float shack = 8f;
        float[] floats = new float[]{0f, shack, -shack, shack, -shack, 0f};
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", floats);
        animator.setDuration(500);
        animator.setRepeatCount(0);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animationMap.put(view.getId() + AnimationType.ROTATION.name(), false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationMap.put(view.getId() + AnimationType.ROTATION.name(), true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }


    //清空缓存
    public static void onDestroy() {
        animationMap.clear();
    }


    //上下滑动
    public static void doXAnimation(final View view, int width) {
        float x = view.getX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "x", x, x - width);
        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animationMap.put(view.getId() + AnimationType.MOVE_X.name(), false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationMap.put(view.getId() + AnimationType.MOVE_X.name(), true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

    public static void doXAnimation2(final View view, int width) {
        float x = view.getX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "x", width, x);
        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animationMap.put(view.getId() + AnimationType.MOVE_X.name(), false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationMap.put(view.getId() + AnimationType.MOVE_X.name(), true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }

    public static void doYAnimation2(final View view) {
        float y = view.getY();
        float[] values = {y, y - 30, y + 10, y - 10, y + 30, y};
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "y", values);
        animator.setDuration(4000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                animationMap.put(view.getId() + AnimationType.PRESS.name(), false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animationMap.put(view.getId() + AnimationType.PRESS.name(), true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
