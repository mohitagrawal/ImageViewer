package imageviewer.imageviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import imageviewer.imageviewer.callbacks.ActivityCallback;

/**
 * Created by Mohit on 9/6/2014.
 */
public class GalleryFragment extends Fragment {
    private GridView gridView;
    private GridViewAdapter customGridAdapter;
    ActivityCallback _callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.gallery_fragment, container, false);
        gridView = (GridView) root.findViewById(R.id.gridView);
        customGridAdapter = new GridViewAdapter(getActivity(), R.layout.row_grid, _callback.getImagesData(),_callback);
        gridView.setAdapter(customGridAdapter);
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof ActivityCallback) {
            _callback = (ActivityCallback) getActivity();
        } else {
            new IllegalArgumentException("Parent activity of " +
                    GalleryFragment.class.getName() + " should implement " + ActivityCallback.class.getName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _callback = null;
    }
}

