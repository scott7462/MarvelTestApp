
package scott.android.com.marveltest.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterItem implements Parcelable {

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resourceURI);
        dest.writeString(this.name);
    }

    public CharacterItem() {
    }

    protected CharacterItem(Parcel in) {
        this.resourceURI = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CharacterItem> CREATOR = new Parcelable.Creator<CharacterItem>() {
        @Override
        public CharacterItem createFromParcel(Parcel source) {
            return new CharacterItem(source);
        }

        @Override
        public CharacterItem[] newArray(int size) {
            return new CharacterItem[size];
        }
    };
}
