package com.example.chapter3.homework;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.airbnb.lottie.LottieAnimationView;

public class Homework1Activity extends AppCompatActivity {

    private static final int PAGE_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework1);
        ViewPager pager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        final LottieAnimationView lottieAnimationView = findViewById(R.id.animation_view);
        final FriendsFragment friendsFragment = new FriendsFragment();
        final MyFriendsFragment myFriendsFragment = new MyFriendsFragment();
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if (i == 0){
                    return friendsFragment;
                }
                else {
                    return myFriendsFragment;
                }
            }

            @Override
            public int getCount() {
                return PAGE_COUNT;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0){
                    return "好友列表";
                }
                else return "我的好友";
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // This method will be invoked when the ViewPager is scrolled, but before the current page changes.
            }

            @Override
            public void onPageSelected(int position) {
                // This method will be invoked when a new page becomes selected.
                if (position == 0) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.hide(friendsFragment);
                    transaction.commit();
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    lottieAnimationView.playAnimation();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 使用淡入淡出动画显示实际列表
                            AlphaAnimation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
                            fadeInAnimation.setDuration(1000); // 设置动画持续时间为 1 秒

                            // 使用 FragmentTransaction 添加 FriendsFragment
                            getSupportFragmentManager().beginTransaction()
                                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                    .show(friendsFragment)
                                    .commit();

                            // 隐藏 Lottie 动画
                            lottieAnimationView.setVisibility(View.GONE);
                        }
                    }, 5000); // 5000 毫秒为 5 秒
                } else {
                    lottieAnimationView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // This method will be invoked when the scroll state changes.
            }
        });

        tabLayout.setupWithViewPager(pager);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(friendsFragment);
        transaction.commit();
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 使用淡入淡出动画显示实际列表
                AlphaAnimation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
                fadeInAnimation.setDuration(1000); // 设置动画持续时间为 1 秒

                // 使用 FragmentTransaction 添加 FriendsFragment
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .show(friendsFragment)
                        .commit();

                // 隐藏 Lottie 动画
                lottieAnimationView.setVisibility(View.GONE);
            }
        }, 5000); // 5000 毫秒为 5 秒
    }

}
