package scott.android.com.marveltest.app.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.base.view.BaseSimpleAdapter;
import scott.android.com.marveltest.entities.CharacterItem;

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
public class CharacterAdapter extends BaseSimpleAdapter<CharacterItem, BaseSimpleAdapter.BaseViewHolder> {

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY_VIEW: {
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_characters_empty, parent, false));
            }
            default:
                return new CharacterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false));
        }
    }

    class CharacterHolder extends BaseViewHolder<CharacterItem> {

        @BindView(R.id.tVFrgCharacterTitle)
        TextView tVFrgCharacterTitle;

        public CharacterHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(CharacterItem item) {
            tVFrgCharacterTitle.setText(item.getName());
        }
    }

}
