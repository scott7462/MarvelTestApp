package scott.android.com.marveltest.app.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.base.view.BaseFilterSimpleAdapter;
import scott.android.com.marveltest.base.view.BaseSimpleAdapter;
import scott.android.com.marveltest.entities.Comic;

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
public class ComicAdapter extends BaseFilterSimpleAdapter<Comic, BaseSimpleAdapter.BaseViewHolder> {


    @Override
    protected boolean searchCondition(Comic item, String query) {
        if (query.length() <= 3) return false;
        return item.getTitle().toLowerCase().contains(query.toLowerCase());
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY_VIEW: {
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic_empty, parent, false));
            }
            case LOADING_VIEW: {
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic_loading, parent, false));
            }
            default:
                return new ComicHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent, false));
        }
    }

    class ComicHolder extends BaseViewHolder<Comic> {

        @BindView(R.id.iVFrgComicImage)
        ImageView iVFrgComicImage;

        @BindView(R.id.tVFrgComicTitle)
        TextView tVFrgComicTitle;

        @BindView(R.id.tVFrgComicPrice)
        TextView tVFrgComicPrice;


        public ComicHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(Comic item) {
            tVFrgComicTitle.setText(item.getTitle());
            tVFrgComicPrice.setText(item.getFinalPrice(tVFrgComicPrice.getContext()));
            if (item.getThumbnail() != null)
                Glide.with(iVFrgComicImage.getContext())
                        .load(iVFrgComicImage.getContext().getString(R.string.image_url,
                                item.getThumbnail().getPath(),
                                item.getThumbnail().getExtension()))
                        .centerCrop()
                        .into(iVFrgComicImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callItemListenerByPosition(getAdapterPosition());
                }
            });
        }
    }

}
