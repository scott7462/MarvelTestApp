package scott.android.com.marveltest.app.ui.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.app.ui.detail.presenter.ComicPresenter;
import scott.android.com.marveltest.app.ui.detail.presenter.ComicPresenterListener;
import scott.android.com.marveltest.base.view.BaseActivity;
import scott.android.com.marveltest.base.view.BaseFragmentMVP;
import scott.android.com.marveltest.bus.event.EventSnackBar;
import scott.android.com.marveltest.entities.Comic;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 1/16/17.
 *          <p>
 *          Copyright (C) 2015 The Android Open Source Project
 *          <p/>
 *          Licensed under the Apache License, Version 2.0 (the "License");
 *          you may not use this file except in compliance with the License.
 *          You may obtain a copy of the License at
 *          <p/>
 * @see <a href = "http://www.aprenderaprogramar.com" /> http://www.apache.org/licenses/LICENSE-2.0 </a>
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class DetailsFragment extends BaseFragmentMVP<ComicPresenter> implements ComicPresenterListener {

    @BindView(R.id.tVFrgDetailsTitle)
    TextView tVFrgDetailsTitle;
    @BindView(R.id.tVFrgDetailsDescription)
    TextView tVFrgDetailsDescription;
    @BindView(R.id.tVFrgDetailsPrice)
    TextView tVFrgDetailsPrice;
    @BindView(R.id.tVFrgDetailDate)
    TextView tVFrgDetailDate;
    @BindView(R.id.tVFrgDetailNumberPages)
    TextView tVFrgDetailNumberPages;
    @BindView(R.id.tVFrgDetailSeries)
    TextView tVFrgDetailSeries;
    @BindView(R.id.iVFrgDetailsImage)
    ImageView iVFrgDetailsImage;


    @BindView(R.id.rVFrgDetailCharacters)
    RecyclerView rVFrgDetailCharacters;
    @BindView(R.id.rVFrgDetailCreators)
    RecyclerView rVFrgDetailCreators;
    private Comic comic;
    CharacterAdapter characterAdapter = new CharacterAdapter();
    CreatorsAdapter creatorsAdapter = new CreatorsAdapter();
    private boolean isFavorite = false;

    public static DetailsFragment newInstance(Comic comic) {
        Bundle args = new Bundle();
        args.putParcelable(Comic.COMIC_PARAM, comic);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_details, container, false);
    }

    @Override
    protected void initVars() {
        comic = getArguments().getParcelable(Comic.COMIC_PARAM);
        setHasOptionsMenu(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setPresenter(new ComicPresenter());
        getPresenter().doGetDetails(comic.getId());
        getPresenter().doCheckFavorite(comic.getId());
    }

    @Override
    protected void initViews() {
        tVFrgDetailsTitle.setText(comic.getTitle());
        tVFrgDetailsDescription.setVerticalScrollBarEnabled(true);
        tVFrgDetailsDescription.setMovementMethod(new ScrollingMovementMethod());
        tVFrgDetailsDescription.setText(comic.getDescription());
        tVFrgDetailsPrice.setText(comic.getFinalPrice(getActivity()));
        tVFrgDetailNumberPages.setText(getString(R.string.frg_details_num_pages, comic.getPageCount()));
        if (comic.getThumbnail() != null)
            Glide.with(this)
                    .load(getString(R.string.image_url,
                            comic.getThumbnail().getPath(),
                            comic.getThumbnail().getExtension()))
                    .centerCrop()
                    .into(iVFrgDetailsImage);

        if (comic.getSeries() != null)
            tVFrgDetailSeries.setText(getString(R.string.frg_details_series, comic.getSeries().getName()));
        if (comic.getCharacters() != null)
            characterAdapter.cleanItemsAndUpdate(comic.getCharacters().getItems());

        if (comic.getCreators() != null)
            creatorsAdapter.cleanItemsAndUpdate(comic.getCreators().getCreatorsItems());
        rVFrgDetailCharacters.setLayoutManager(new LinearLayoutManager(getActivity()));
        rVFrgDetailCharacters.setAdapter(characterAdapter);

        rVFrgDetailCreators.setLayoutManager(new LinearLayoutManager(getActivity()));
        rVFrgDetailCreators.setAdapter(creatorsAdapter);


    }

    @Override
    protected void initListeners() {

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_details, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_favorite).setChecked(isFavorite);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                getPresenter().doFavoriteComic(comic);
                break;
            default:
                getActivity().onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onGetDetailsComic(Comic comic) {
        this.comic = comic;
        initViews();
    }

    @Override
    public void onCheckFavorite(Comic comic) {
        getPresenter().doCheckFavorite(comic.getId());
    }

    @Override
    public void onSetFavorite(Boolean aBoolean) {
        isFavorite = aBoolean;
        EventBus.getDefault().post(new EventSnackBar().withMessage(aBoolean ? getString(R.string.frag_details_added_favorite)
                : getString(R.string.frag_details_remove)));
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onGetFavorities(List<Comic> comics) {

    }
}
