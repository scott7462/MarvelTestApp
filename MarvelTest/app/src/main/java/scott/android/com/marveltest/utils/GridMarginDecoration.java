package scott.android.com.marveltest.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import scott.android.com.marveltest.R;
import scott.android.com.marveltest.app.App;


/**
 * Created by Rafael on 4/5/16.
 */
public class GridMarginDecoration extends RecyclerView.ItemDecoration {

    private int margin;

    public GridMarginDecoration() {
        margin = App.getGlobalContext().getResources().getDimensionPixelSize(R.dimen.default_small_size);
    }

    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = margin;
        outRect.right = margin;
        outRect.bottom = margin;
        outRect.top = margin;
    }

    public GridMarginDecoration(int margin) {
        this.margin = margin;
    }
}
