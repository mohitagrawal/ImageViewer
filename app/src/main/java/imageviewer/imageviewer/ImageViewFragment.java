package imageviewer.imageviewer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import imageviewer.imageviewer.model.ImageItem;
import imageviewer.imageviewer.task.DownloadImageTask;
import imageviewer.imageviewer.callbacks.SingleImageDownloadCallback;

public class ImageViewFragment extends Fragment implements SingleImageDownloadCallback {
    private Bitmap myBitmap;
    public ImageItem imageItem;
    private ImageView ivImage;
    private TextView loadingTV;
    private RelativeLayout loadingView;

    public static ImageViewFragment newInstance() {
        ImageViewFragment f = new ImageViewFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.imageview, container, false);
        ivImage = (ImageView) root.findViewById(R.id.ivImageView);
        loadingTV = (TextView) root.findViewById(R.id.imageTitle);
        loadingView = (RelativeLayout) root.findViewById(R.id.loadingView);
        loadingTV.setText("Loading " + imageItem.getTitle() + "...");
        downloadImage();
        return root;
    }

    public void downloadImage() {
        loadingView.setVisibility(View.VISIBLE);
        DownloadImageTask task = new DownloadImageTask(imageItem.getUrl(), this);
        task.execute(this);
    }

    public void setImageItem(ImageItem imageItem) {
        this.imageItem = imageItem;
    }

    public void setImageInViewPager(Bitmap myBitmap) {
        try {
            //myBitmap = BitmapFactory.decodeResource(getResources(), itemData,
            // options);
            // myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            //TODO myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream(),2,options);
            if (myBitmap != null) {
                try {
                    if (ivImage != null) {
                        ivImage.setImageBitmap(myBitmap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (myBitmap != null) {
            myBitmap.recycle();
            myBitmap = null;
        }
    }

    @Override
    public void imageDownloaded(Bitmap bitmap) {
        loadingView.setVisibility(View.GONE);
        ivImage.setVisibility(View.VISIBLE);
        setImageInViewPager(bitmap);
    }

    @Override
    public void errorOccured() {
        loadingTV.setText("Loading " + imageItem.getTitle() + " failed.");
    }
}
