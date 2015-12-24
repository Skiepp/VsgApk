package com.vsg.app.vsgapp.Generic;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vsg.app.vsgapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skiepp93 on 18/12/2015.
 */
public class SwipeAdapter extends PagerAdapter{

    private List<Bitmap> bitmaps = new ArrayList<>();
    Context context;
    private LayoutInflater layoutInflater;
    private int finalCount;

    public SwipeAdapter(Context context) {
        this.context = context;
    }

    public void addBitmap(Bitmap b) {
        bitmaps.add(b);
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    public int getFinalCount() {
        return finalCount;
    }

    public void setFinalCount(int finalCount) {
        this.finalCount = finalCount;
    }

    public boolean endReached() {
        return finalCount==bitmaps.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        imageView.setImageBitmap(bitmaps.get(position));
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
