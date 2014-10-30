package imageviewer.imageviewer.callbacks;

import java.util.ArrayList;

import imageviewer.imageviewer.model.ImageItem;

/**
 * Created by Mohit on 9/6/2014.
 */
public interface ActivityCallback {
    ArrayList<ImageItem> getImagesData();
    void pushPager(int position);
}
