package imageviewer.imageviewer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import imageviewer.imageviewer.model.ImageItem;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

	private ArrayList<ImageItem> itemData;

	public FragmentPagerAdapter(FragmentManager fm,
                                ArrayList<ImageItem> itemData) {
		super(fm);
		this.itemData = itemData;
	}

	@Override
	public int getCount() {
		return itemData.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}

	@Override
	public Fragment getItem(int position) {
		ImageViewFragment f = ImageViewFragment.newInstance();
        f.setImageItem(itemData.get(position));
		return f;
	}
}