package imageviewer.imageviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import imageviewer.imageviewer.callbacks.ActivityCallback;
import imageviewer.imageviewer.model.ImageItem;

/**
 * Created by Mohit on 9/6/2014.
 */
public class ImagePagerFragment extends Fragment implements
        View.OnClickListener, ViewPager.OnPageChangeListener {

    private Button btnImagePrevious, btnImageNext;
    private int position, totalImages;
    private ViewPager viewPage;
    private FragmentPagerAdapter adapter;
    private ArrayList<ImageItem> images;
    ActivityCallback _callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.imageview_page, container, false);

        viewPage = (ViewPager) rootView.findViewById(R.id.viewPager);
        btnImagePrevious = (Button) rootView.findViewById(R.id.btnImagePrevious);
        btnImageNext = (Button) rootView.findViewById(R.id.btnImageNext);
        position = getArguments().getInt("Position");
        images = _callback.getImagesData();
        setupImagesAdapter();
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof ActivityCallback) {
            _callback = (ActivityCallback) getActivity();
        } else {
            new IllegalArgumentException("Parent activity of " +
                    ImagePagerFragment.class.getName() + " should implement " + ActivityCallback.class.getName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _callback = null;
    }

    public void setupImagesAdapter() {
        totalImages = images.size();
        setPage(position);

        adapter = new FragmentPagerAdapter(getChildFragmentManager(),
                images);
        viewPage.setAdapter(adapter);
        viewPage.setCurrentItem(position);
        viewPage.setOnPageChangeListener(this);
        btnImagePrevious.setOnClickListener(this);
        btnImageNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnImagePrevious) {
            position--;
            viewPage.setCurrentItem(position);
        } else if (v == btnImageNext) {
            position++;
            viewPage.setCurrentItem(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        setPage(position);
    }

    private void setPage(int page) {
        if (page == 0 && totalImages > 0) {
            btnImageNext.setVisibility(View.VISIBLE);
            btnImagePrevious.setVisibility(View.INVISIBLE);
        } else if (page == totalImages - 1 && totalImages > 0) {
            btnImageNext.setVisibility(View.INVISIBLE);
            btnImagePrevious.setVisibility(View.VISIBLE);
        } else {
            btnImageNext.setVisibility(View.VISIBLE);
            btnImagePrevious.setVisibility(View.VISIBLE);
        }
    }
}
