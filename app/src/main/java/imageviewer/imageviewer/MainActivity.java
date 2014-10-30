package imageviewer.imageviewer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import imageviewer.imageviewer.callbacks.ActivityCallback;
import imageviewer.imageviewer.model.ImageItem;
import imageviewer.imageviewer.task.DownloadMetaDataTask;
import imageviewer.imageviewer.callbacks.MetaDataDownloaderCallback;


public class MainActivity extends ActionBarActivity implements MetaDataDownloaderCallback, ActivityCallback {
    public static final String IMAGES_DATA = "ImagesData";
    ProgressDialog progressDialog;
    private ArrayList<ImageItem> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            images = (ArrayList<ImageItem>) savedInstanceState.getSerializable(IMAGES_DATA);
        }
        if (images != null) {
            pushGalleryFragment();
        } else {
            fetchImagesData();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(IMAGES_DATA, images);
    }

    public void fetchImagesData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Downloading Data ...");
        progressDialog.show();
        DownloadMetaDataTask fetchTopics = new DownloadMetaDataTask(this, this);
        fetchTopics.execute(this);

    }

    @Override
    public void metaDataDownloaded(ArrayList<ImageItem> imageItems) {
        progressDialog.dismiss();
        Toast.makeText(this, "Images Downloaded" + imageItems.size(),
                Toast.LENGTH_LONG).show();
        images = imageItems;
        pushGalleryFragment();
    }

    protected void pushGalleryFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new GalleryFragment()).addToBackStack(GalleryFragment.class.getName())
                .commit();
    }

    @Override
    public void errorOccured() {
        progressDialog.dismiss();
        Toast.makeText(this, "Error occurred",
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public ArrayList<ImageItem> getImagesData() {
        return images;
    }

    @Override
    public void pushPager(int position) {
        Bundle args = new Bundle();
        args.putInt("Position", position);
        ImagePagerFragment newFragment = new ImagePagerFragment();
        newFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, newFragment)
                .addToBackStack(ImagePagerFragment.class.getName())
                .commit();
    }
}
