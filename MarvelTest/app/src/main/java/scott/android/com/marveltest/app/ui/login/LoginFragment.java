package scott.android.com.marveltest.app.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.app.ui.login.presenter.SessionPresenter;
import scott.android.com.marveltest.app.ui.login.presenter.SessionPresenterListener;
import scott.android.com.marveltest.app.ui.main.ActivityMain;
import scott.android.com.marveltest.base.view.BaseFragmentMVP;
import scott.android.com.marveltest.bus.event.EventSnackBar;
import scott.android.com.marveltest.entities.User;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 1/15/17.
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


public class LoginFragment extends BaseFragmentMVP<SessionPresenter> implements SessionPresenterListener {

    @BindView(R.id.lBFrgLoginFacebook)
    LoginButton lBFrgLoginFacebook;
    private CallbackManager callbackManager;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_login, container, false);
    }

    @Override
    protected void initVars() {
        facebookInit();
        setPresenter(new SessionPresenter());
    }

    @Override
    protected void initViews() {
        facebookManger();
        getPresenter().doGetComic();
    }

    @Override
    protected void initListeners() {

    }

    @OnClick(R.id.tVFrgLoginFacebookButton)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tVFrgLoginFacebookButton: {
                lBFrgLoginFacebook.performClick();
                break;
            }
        }
    }

    private void facebookInit() {
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    private void facebookManger() {
        lBFrgLoginFacebook.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday"));
        lBFrgLoginFacebook.setFragment(this);
        lBFrgLoginFacebook.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Bundle params = new Bundle();
                        params.putString("fields", "name,id,email,cover,picture.type(large)");
                        new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        if (response != null) {
                                            getPresenter().doLoginWithFacebook(response);
                                        }
                                    }
                                }).executeAsync();
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        EventBus.getDefault().post(new EventSnackBar().withMessage(getString(R.string.error_facebook_connection)));
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onUserLogin(User user) {
        EventBus.getDefault().post(new EventSnackBar().withMessage(getString(R.string.frg_login_welcome, user.getName())));
        ActivityMain.newInstance(getActivity());
        getActivity().finish();
    }

    @Override
    public void onUserLogout() {

    }
}
