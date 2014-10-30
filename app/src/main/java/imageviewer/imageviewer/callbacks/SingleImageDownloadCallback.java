package imageviewer.imageviewer.callbacks;

import android.graphics.Bitmap;

/**
 * Created by Mohit on 9/6/2014.
 */
public interface SingleImageDownloadCallback {
    void imageDownloaded(Bitmap bitmap);
    void errorOccured();
}
