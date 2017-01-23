package scott.android.com.marveltest.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.facebook.GraphResponse;

import org.json.JSONObject;

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


public class User implements Parcelable {

    private String id;
    private String name;
    private String email;
    private String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User withName(String name) {
        setName(name);
        return this;
    }


    public User withEmail(String email) {
        setEmail(email);
        return this;
    }

    public User withPhoto(String photo) {
        setPhoto(photo);
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.photo);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.photo = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public static User getUserToJson(GraphResponse response) {
        User user = new User();
        try {
            JSONObject data = response.getJSONObject();
            if (data.has("picture")) {
                user.setPhoto(data.getJSONObject("picture").getJSONObject("data").getString("url"));
            }
            user.setEmail(data.getString("email"));
            user.setName(data.getString("name"));
            user.setId(data.getString("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
