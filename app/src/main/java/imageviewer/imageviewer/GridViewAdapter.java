package imageviewer.imageviewer;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import imageviewer.imageviewer.callbacks.ActivityCallback;
import imageviewer.imageviewer.model.ImageItem;
import imageviewer.imageviewer.model.WebImageView;

/**
 * Created by Mohit on 9/6/2014.
 */
public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();
    private ActivityCallback listener;

    public GridViewAdapter(Context context, int layoutResourceId,
                           ArrayList data, ActivityCallback listener) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.listener = listener;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        holder = new ViewHolder();
        holder.imageTitle = (TextView) row.findViewById(R.id.text);
        holder.image = (WebImageView) row.findViewById(R.id.image);
        row.setTag(holder);

        holder.image.setPlaceholderImage(R.drawable.ic_launcher);
        //set item click listeners
        ItemClick itemClick = new ItemClick(position);
        holder.imageTitle.setOnClickListener(itemClick);
        holder.image.setOnClickListener(itemClick);

        ImageItem item = (ImageItem) data.get(position);
        holder.imageTitle.setText(item.getTitle());

        //set image url to thumbnail url for gridview
        holder.image.setImageUrl(item.getThumbnailUrl());
        row.setTag(holder);
        return row;
    }

    private class ItemClick implements View.OnClickListener {
        int position;

        public ItemClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            listener.pushPager(position);
        }
    }

    static class ViewHolder {
        TextView imageTitle;
        WebImageView image;
    }
}
