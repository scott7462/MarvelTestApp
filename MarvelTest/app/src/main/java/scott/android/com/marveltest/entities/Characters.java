
package scott.android.com.marveltest.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Characters implements Parcelable {

    @SerializedName("available")
    @Expose
    private int available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private List<CharacterItem> items = null;
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

    public List<CharacterItem> getItems() {
        return items;
    }

    public void setItems(List<CharacterItem> items) {
        this.items = items;
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
        dest.writeList(this.items);
        dest.writeInt(this.returned);
    }

    public Characters() {
    }

    protected Characters(Parcel in) {
        this.available = in.readInt();
        this.collectionURI = in.readString();
        this.items = new ArrayList<CharacterItem>();
        in.readList(this.items, CharacterItem.class.getClassLoader());
        this.returned = in.readInt();
    }

    public static final Parcelable.Creator<Characters> CREATOR = new Parcelable.Creator<Characters>() {
        @Override
        public Characters createFromParcel(Parcel source) {
            return new Characters(source);
        }

        @Override
        public Characters[] newArray(int size) {
            return new Characters[size];
        }
    };
}
