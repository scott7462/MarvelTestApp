
package scott.android.com.marveltest.entities;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import scott.android.com.marveltest.R;
import scott.android.com.marveltest.data.source.db.tables.ComicTable;
import scott.android.com.marveltest.data.source.db.tables.ThumbnailTable;

public class Comic implements Parcelable {

    public static final String COMIC_PARAM = Comic.class.getName();
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("pageCount")
    @Expose
    private int pageCount;
    @SerializedName("series")
    @Expose
    private Series series;
    @SerializedName("dates")
    @Expose
    private List<Date> dates = null;
    @SerializedName("prices")
    @Expose
    private List<Price> prices = null;
    @SerializedName("creators")
    @Expose
    private Creators creators;
    @SerializedName("characters")
    @Expose
    private Characters characters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Creators getCreators() {
        return creators;
    }

    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinalPrice(Context context) {
        return (getPrices() == null || getPrices().size() == 0)
                ? context.getString(R.string.frg_comic_not_price) :
                ((getPrices().get(0).getPrice() == 0) ? context.getString(R.string.frg_comic_not_price)
                        : context.getString(R.string.frg_comic_price,
                        String.valueOf(getPrices().get(0).getPrice())));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.pageCount);
        dest.writeParcelable(this.series, flags);
        dest.writeTypedList(this.dates);
        dest.writeTypedList(this.prices);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeParcelable(this.creators, flags);
        dest.writeParcelable(this.characters, flags);
    }

    public Comic() {
    }

    protected Comic(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.pageCount = in.readInt();
        this.series = in.readParcelable(Series.class.getClassLoader());
        this.dates = in.createTypedArrayList(Date.CREATOR);
        this.prices = in.createTypedArrayList(Price.CREATOR);
        this.thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        this.creators = in.readParcelable(Creators.class.getClassLoader());
        this.characters = in.readParcelable(Characters.class.getClassLoader());
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel source) {
            return new Comic(source);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };




    public Comic withDescription(String description) {
        setDescription(description);
        return this;
    }

    public Comic withId(int id) {
        setId(id);
        return this;
    }

    public Comic withTitle(String title) {
        setTitle(title);
        return this;
    }


}
