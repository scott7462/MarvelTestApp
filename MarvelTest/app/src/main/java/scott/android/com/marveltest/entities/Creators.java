
package scott.android.com.marveltest.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Creators implements Parcelable {

    @SerializedName("available")
    @Expose
    private int available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("creatorsItems")
    @Expose
    private List<CreatorsItem> creatorsItems = null;
    @SerializedName("returned")
    @Expose
    private int returned;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<CreatorsItem> getCreatorsItems() {
        return creatorsItems;
    }

    public void setCreatorsItems(List<CreatorsItem> creatorsItems) {
        this.creatorsItems = creatorsItems;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.available);
        dest.writeString(this.collectionURI);
        dest.writeList(this.creatorsItems);
        dest.writeInt(this.returned);
    }

    public Creators() {
    }

    protected Creators(Parcel in) {
        this.available = in.readInt();
        this.collectionURI = in.readString();
        this.creatorsItems = new ArrayList<CreatorsItem>();
        in.readList(this.creatorsItems, CreatorsItem.class.getClassLoader());
        this.returned = in.readInt();
    }

    public static final Parcelable.Creator<Creators> CREATOR = new Parcelable.Creator<Creators>() {
        @Override
        public Creators createFromParcel(Parcel source) {
            return new Creators(source);
        }

        @Override
        public Creators[] newArray(int size) {
            return new Creators[size];
        }
    };
}
