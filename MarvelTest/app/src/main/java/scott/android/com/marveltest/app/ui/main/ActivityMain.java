package scott.android.com.marveltest.app.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.app.ui.detail.DetailsFragment;
import scott.android.com.marveltest.app.ui.favorite.FavoriteFragment;
import scott.android.com.marveltest.app.ui.profile.ProfileFragment;
import scott.android.com.marveltest.base.view.BaseActivity;
import scott.android.com.marveltest.entities.Comic;

public class ActivityMain extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        setToolbar(toolbar);
        navigateMainContent(MainFragment.newInstance(), getString(R.string.app_name));
    }


    public static void newInstance(Activity activity) {
        activity.startActivity(new Intent(activity, ActivityMain.class));
    }

    public void goToProfile() {
        navigateLowContent(ProfileFragment.newInstance(),
                getString(R.string.frg_profile_title));
    }

    public void goToDetail(Comic comic) {
        navigateLowContent(DetailsFragment.newInstance(comic), comic.getTitle());
    }

    public void goToFavorites() {
        navigateLowContent(FavoriteFragment.newInstance(),getString(R.string.frag_favorite));
    }
}
