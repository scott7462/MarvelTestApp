package scott.android.com.marveltest.app.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.base.view.BaseSimpleAdapter;
import scott.android.com.marveltest.entities.CreatorsItem;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 1/19/17.
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
public class CreatorsAdapter extends BaseSimpleAdapter<CreatorsItem, BaseSimpleAdapter.BaseViewHolder> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY_VIEW: {
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_creators_empty, parent, false));
            }
            default:
                return new CreatorsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_creator, parent, false));
        }
    }

    class CreatorsHolder extends BaseViewHolder<CreatorsItem> {

        @BindView(R.id.tVFrgCreatorName)
        TextView tVFrgCreatorName;

        @BindView(R.id.tVFrgCreatorRole)
        TextView tVFrgCreatorRole;


        public CreatorsHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(CreatorsItem item) {
            tVFrgCreatorName.setText(item.getName());
            tVFrgCreatorRole.setText(item.getRole());
        }
    }

}
