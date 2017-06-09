package com.tutor93.nikahin.ui.invitationlist;

import android.os.Bundle;

import com.tutor93.nikahin.R;
import com.tutor93.nikahin.ui.base.BaseActivity;

public class InvitationlistActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historylist);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.historylist_container, InvitationlistFragment.newInstance())
                    .commit();
        }
    }
}
