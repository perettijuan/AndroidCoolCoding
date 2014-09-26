package com.jpp.roundedimages;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentAndrew extends Fragment implements IFragmentListener {


    RoundedImageView ivRounded;
    ImageView ivBattery;
    ImageView ivHeartbeat;
    ImageView ivStatus;
    ImageView ivMicStatus;
    ImageView ivPActivity;

    View textContainer;
    TextView txtName;
    TextView txtLocation;

    public static final String TAG = FragmentAndrew.class.getName();

    public FragmentAndrew() {

    }

    public static FragmentAndrew newInstance() {
        return new FragmentAndrew();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.main_fragment, container, false);
        ivRounded = (RoundedImageView) fView.findViewById(R.id.iv_rounded);
        ivBattery = (ImageView) fView.findViewById(R.id.iv_battery);
        ivHeartbeat = (ImageView) fView.findViewById(R.id.iv_heartbeat);
        ivStatus = (ImageView) fView.findViewById(R.id.iv_status);
        ivMicStatus = (ImageView) fView.findViewById(R.id.iv_mic_satus);
        ivPActivity = (ImageView) fView.findViewById(R.id.iv_p_activity);

        textContainer = fView.findViewById(R.id.text_container);
        txtName = (TextView) fView.findViewById(R.id.txt_name);
        txtLocation = (TextView) fView.findViewById(R.id.txt_location);

        return fView;
    }

    @Override
    public void onShow() {
        Drawable dr = getResources().getDrawable(R.drawable.andrew);
        ivRounded.setImageDrawable(dr);
        ivRounded.onGray();
        ivBattery.setImageResource(R.drawable.ic_battery_50);
        ivHeartbeat.setImageResource(R.drawable.ic_heartbeat_gray);
        ivStatus.setImageResource(R.drawable.ic_busy);
        ivPActivity.setImageResource(R.drawable.ic_car);
        txtName.setText("Andrew Doe");
        txtLocation.setText("@LA");
        changeVisibility(View.VISIBLE, ivBattery, ivHeartbeat, ivStatus, textContainer, ivPActivity);
    }

    @Override
    public void onDismiss() {
        changeVisibility(View.GONE, ivBattery, ivHeartbeat, ivStatus, textContainer, ivPActivity);

    }

    private void changeVisibility(int visibility, View... view) {
        for (View v : view) {
            v.setVisibility(visibility);
        }
    }
}
