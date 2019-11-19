package com.example.gabriel.inventorylocater;

import android.view.View;

import android.view.ViewGroup;

public class ImageClickListener implements View.OnClickListener {

    private int type;

    public ImageClickListener(int type) {
        this.type = type;
    }

    @Override
    public void onClick(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();

        if (type == 1) {
            viewGroup.getChildAt(1).setVisibility(View.VISIBLE);
        } else if (type == 2) {
            ((ViewGroup) viewGroup.getParent().getParent()).removeView((ViewGroup) viewGroup.getParent());
        } else if (type == 3) {
            ((ViewGroup) viewGroup.getParent()).getChildAt(1).setVisibility(View.GONE);
        }
    }
}
