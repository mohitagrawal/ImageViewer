package imageviewer.imageviewer.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import imageviewer.imageviewer.callbacks.SingleImageDownloadCallback;

/**
 * Created by Mohit on 9/6/2014.
 */

public class DownloadImageTask extends AsyncTask<Object, Void, Bitmap> {
    String imageUrl;
    SingleImageDownloadCallback listener;

    public DownloadImageTask() {
    }

    public DownloadImageTask(String data,SingleImageDownloadCallback listener) {
        this.listener = listener;
        this.imageUrl=data;
    }

    @Override
    protected Bitmap doInBackground(Object... args) {
        URL url = null;
        Bitmap bmp = null;
        try {
            url = new URL(imageUrl);

        bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result!=null){
            listener.imageDownloaded(result);
        }else{
            listener.errorOccured();
        }
    }
}

