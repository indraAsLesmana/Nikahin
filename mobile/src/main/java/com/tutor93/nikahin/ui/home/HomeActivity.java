package com.tutor93.nikahin.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.tutor93.core.data.model.History;
import com.tutor93.nikahin.R;
import com.tutor93.nikahin.ui.base.BaseActivity;

import java.io.Serializable;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public class HomeActivity extends BaseActivity {

    private static final String EXTRA_DETAIL_INVITATION = "historyInvitation";

    public static Intent newStartIntent(Context context, History.Invitation invitation) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(EXTRA_DETAIL_INVITATION, invitation);
        return intent;
    }

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
