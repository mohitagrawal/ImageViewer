package imageviewer.imageviewer.model;

import java.io.Serializable;

/**
 * Created by magrawal on 9/6/2014.
 */
public class ImageItem implements Serializable {
    private int albumId;
    private int id;
    private String title;
    private String thumbnailUrl;
    private String url;

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAlbumId() {

        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
