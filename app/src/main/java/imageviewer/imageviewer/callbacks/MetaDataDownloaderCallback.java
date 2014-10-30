package imageviewer.imageviewer.callbacks;

import java.util.ArrayList;

import imageviewer.imageviewer.model.ImageItem;

/**
 * Created by magrawal on 9/6/2014.
 */
public interface MetaDataDownloaderCallback {
    void metaDataDownloaded(ArrayList<ImageItem> imagesArray);
    void errorOccured();
}
