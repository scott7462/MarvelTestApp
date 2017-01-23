package scott.android.com.marveltest.app.ui.profile;

import android.content.DialogInterface;
import android.os.Bundle;
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

import butterknife.BindView;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.app.ui.launch.ActivityLaunch;
import scott.android.com.marveltest.app.ui.login.presenter.SessionPresenter;
import scott.android.com.marveltest.app.ui.login.presenter.SessionPresenterListener;
import scott.android.com.marveltest.base.view.BaseActivity;
import scott.android.com.marveltest.base.view.BaseFragmentMVP;
import scott.android.com.marveltest.bus.event.EventAlterDialog;
import scott.android.com.marveltest.entities.User;
import scott.android.com.marveltest.utils.CropCircleTransformation;

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

public class ProfileFragment extends BaseFragmentMVP<SessionPresenter> implements SessionPresenterListener {

    @BindView(R.id.tVFrgProfileName)
    TextView tVFrgProfileName;
    @BindView(R.id.tVFrgProfileEmail)
    TextView tVFrgProfileEmail;
    @BindView(R.id.iVFrgProfile)
    ImageView iVFrgProfile;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_profile, container, false);
    }

    @Override
    protected void initVars() {
        setHasOptionsMenu(true);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setPresenter(new SessionPresenter());
    }

    @Override
    protected void initViews() {
        getPresenter().doGetProfile();

    }

    @Override
    protected void initListeners() {

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                EventBus.getDefault().post(new EventAlterDialog()
                        .withMessage(getString(R.string.frg_profile_are_logout))
                        .withPositveButton(getString(R.string.action_ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        getPresenter().doLogout();
                                    }
                                }));
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
    public void onUserLogin(User user) {
        tVFrgProfileName.setText(user.getName());
        tVFrgProfileEmail.setText(user.getEmail());
        Glide.with(getActivity())
                .load(user.getPhoto())
                .bitmapTransform(new CropCircleTransformation(getActivity()))
                .into(iVFrgProfile);
    }

    @Override
    public void onUserLogout() {
        ActivityLaunch.newInstance(getActivity());
        getActivity().finish();
    }
}
