package com.rane.mov.adapter;

import android.view.View;

public class ClickItemAdapter implements View.OnClickListener {
    private onCallback onCallback;

    public ClickItemAdapter(onCallback onCallback) {
        this.onCallback = onCallback;
    }

    @Override
    public void onClick(View v) { onCallback.onItemClicked(v);}

    public interface onCallback{
        void onItemClicked(View view);
    }
}
