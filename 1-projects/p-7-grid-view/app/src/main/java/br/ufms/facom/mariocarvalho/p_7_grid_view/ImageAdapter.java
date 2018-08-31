package br.ufms.facom.mariocarvalho.p_7_grid_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private  int[] mList;

    public ImageAdapter(Context c , int[] list) {
        mContext = c;
        mList = list;
    }

    public int getCount() {
        return mList.length;
    }

    public Object getItem(int position) {
        return mList[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(mContext);

        imageView.setImageResource(mList[position]);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(8, 8, 8, 8);

        return imageView;
    }
}