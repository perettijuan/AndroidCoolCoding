package com.observer.pattern.ui;

import com.observer.pattern.ui.fragment.FetchUserDataFragment;
import com.observer.pattern.ui.fragment.LoginFragment;
import com.observer.pattern.ui.fragment.UpdateUserDataFragment;

public enum Section {

    LOGIN(LoginFragment.TAG),
    FETCH_USER_DATA(FetchUserDataFragment.TAG),
    UPDATE_USER_DATA(UpdateUserDataFragment.TAG);


    private String mSectionTag;

    private Section(String tag) {
        mSectionTag = tag;
    }

    public String getTag() {
        return mSectionTag;
    }
}
