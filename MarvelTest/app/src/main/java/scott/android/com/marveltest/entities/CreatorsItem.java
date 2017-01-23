
package scott.android.com.marveltest.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatorsItem implements Parcelable {

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resourceURI);
        dest.writeString(this.name);
        dest.writeString(this.role);
    }

    public CreatorsItem() {
    }

    protected CreatorsItem(Parcel in) {
        this.resourceURI = in.readString();
        this.name = in.readString();
        this.role = in.readString();
    }

    public static final Parcelable.Creator<CreatorsItem> CREATOR = new Parcelable.Creator<CreatorsItem>() {
        @Override
        public CreatorsItem createFromParcel(Parcel source) {
            return new CreatorsItem(source);
        }

        @Override
        public CreatorsItem[] newArray(int size) {
            return new CreatorsItem[size];
        }
    };
}
