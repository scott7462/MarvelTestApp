package scott.android.com.marveltest.app.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.app.ui.main.presenter.MainPresenter;
import scott.android.com.marveltest.app.ui.main.presenter.MainPresenterListener;
import scott.android.com.marveltest.base.view.BaseActivity;
import scott.android.com.marveltest.base.view.BaseFragmentMVPList;
import scott.android.com.marveltest.base.view.BaseSimpleAdapter;
import scott.android.com.marveltest.entities.Comic;
import scott.android.com.marveltest.utils.GridMarginDecoration;

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

public class MainFragment extends BaseFragmentMVPList<MainPresenter, Comic, ComicAdapter> implements MainPresenterListener {

    @BindView(R.id.rVFrgHome)
    RecyclerView rVFrgHome;
    @BindView(R.id.sRFrgHome)
    SwipeRefreshLayout sRFrgHome;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_main, container, false);
    }

    @Override
    protected void initVars() {
        setHasOptionsMenu(true);
        setAdapter(new ComicAdapter());
        setPresenter(new MainPresenter());
        getPresenter().doGetComic();
        getAdapter().showLoadingState(true);
        getAdapter().addClickListener(new BaseSimpleAdapter.onItemClickListener<Comic>() {
            @Override
            public void onItemViewsClick(Comic item, int position) {
                ((ActivityMain) getActivity()).goToDetail(item);
            }
        });
    }

    @Override
    protected void initViews() {
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        rVFrgHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        rVFrgHome.addItemDecoration(new GridMarginDecoration());
        setRecyclerView(rVFrgHome);
        setSwipeRefresh(sRFrgHome);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() >= 3)
                    getAdapter().filterItems(s);
                else
                    getAdapter().filterItems("");
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                ((ActivityMain) getActivity()).goToProfile();
                break;
            case R.id.action_favorites:
                ((ActivityMain) getActivity()).goToFavorites();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void initListeners() {

    }


    @Override
    protected void onDoLoadItems() {
        getPresenter().doGetComic();
    }

    @Override
    protected void onDoLoadMoreItems() {

    }

    @Override
    protected void onItemsLoaded(List<Comic> comics) {
        getAdapter().cleanItemsAndUpdate(comics);
    }

    @Override
    protected void onMoreItemsLoaded(List<Comic> newItems) {

    }
}
