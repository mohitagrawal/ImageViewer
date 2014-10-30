package imageviewer.imageviewer.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import imageviewer.imageviewer.task.DownloadImageTask;
import imageviewer.imageviewer.callbacks.SingleImageDownloadCallback;

/**
 * Created by Mohit on 9/6/2014.
 */
public class WebImageView extends ImageView implements SingleImageDownloadCallback {

    private Drawable placeholder, image;

    public WebImageView(Context context) {
        super(context);
        init();
    }

    public WebImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public WebImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        this.setFocusable(false);
        this.setFocusableInTouchMode(false);
    }


    public void setPlaceholderImage(int resid) {
        placeholder = getResources().getDrawable(resid);
        if (image == null) {
            setImageDrawable(placeholder);
        }
    }

    public void setImageUrl(String url) {
        if (image == null) {
            DownloadImageTask task = new DownloadImageTask(url, this);
            task.execute(url);
        } else {
            setImageDrawable(image);
        }
    }

    @Override
    public void imageDownloaded(Bitmap bitmap) {
        image = new BitmapDrawable(bitmap);
        if (image != null) {
            setImageDrawable(image);
        }
        // setImageDrawable(bitmap);
    }

    @Override
    public void errorOccured() {
        //TODO
    }

}
