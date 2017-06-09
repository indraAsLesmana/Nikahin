package com.tutor93.nikahin.ui.historylist;

import android.os.Bundle;

import com.tutor93.nikahin.R;
import com.tutor93.nikahin.ui.base.BaseActivity;

public class HistorylistActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historylist);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.historylist_container, HistorylistFragment.newInstance())
                    .commit();
        }
    }
}
