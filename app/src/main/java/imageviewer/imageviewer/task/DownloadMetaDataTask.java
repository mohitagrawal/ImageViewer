package imageviewer.imageviewer.task;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import imageviewer.imageviewer.callbacks.MetaDataDownloaderCallback;
import imageviewer.imageviewer.model.ImageItem;

public class DownloadMetaDataTask extends AsyncTask<Object, Void, Void> {

    Context context;
    private MetaDataDownloaderCallback listener;
    private boolean errorOccurred;
    ArrayList<ImageItem> imagesArray;

    public DownloadMetaDataTask(Context context,MetaDataDownloaderCallback listener) {
        this.listener = listener;
        this.context = context;
    }

    protected Void doInBackground(Object... paras) {
        //context = (Context) paras[0];

        HttpGet get = new HttpGet(
                "http://jsonplaceholder.typicode.com/photos");
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setUseExpectContinue(params, false);
        get.setParams(params);

        try {
            DefaultHttpClient client = new DefaultHttpClient();
            String responsex = client.execute(get, new BasicResponseHandler());
            JSONArray array = new JSONArray(responsex);
            imagesArray = new ArrayList<ImageItem>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject imageObject = array.getJSONObject(i);
                ImageItem object = new ImageItem();
                object.setThumbnailUrl(imageObject.getString("thumbnailUrl"));
                object.setTitle(imageObject.getString("title"));
                object.setAlbumId(imageObject.getInt("albumId"));
                object.setId(imageObject.getInt("id"));
                object.setUrl(imageObject.getString("url"));
                imagesArray.add(object);
            }

        } catch (Exception e) {
            // handle this somehow
            e.printStackTrace();
            errorOccurred = true;
        }
        return null;
    }

    protected void onPostExecute(Void result) {
        if (errorOccurred) {
            listener.errorOccured();
        }else{
            listener.metaDataDownloaded(imagesArray);
        }
    }

}
