package com.tutor93.nikahin.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.tutor93.nikahin.R;
import com.tutor93.nikahin.ui.base.BaseActivity;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_container, HomeFragment.newInstance())
                    .commit();
        }
    }
}
