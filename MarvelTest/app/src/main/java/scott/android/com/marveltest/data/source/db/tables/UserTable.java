package scott.android.com.marveltest.data.source.db.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import scott.android.com.marveltest.data.source.db.DBConstraints;
import scott.android.com.marveltest.entities.User;


/**
 * Created by Pedro Scott on 1/10/17.
 */
@DatabaseTable(tableName = DBConstraints.TABLE_NAME_USER)
public class UserTable {

    @DatabaseField(columnName = DBConstraints.TABLE_USER_ID, id = true)
    private String id;
    @DatabaseField(columnName = DBConstraints.TABLE_USER_NAME)
    private String name;
    @DatabaseField(columnName = DBConstraints.TABLE_USER_EMAIL)
    private String email;
    @DatabaseField(columnName = DBConstraints.TABLE_USER_PHOTO)
    private String photo;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public UserTable withName(String name) {
        setName(name);
        return this;
    }

    public UserTable withEmail(String email) {
        setEmail(email);
        return this;
    }

    public UserTable withPhoto(String photo) {
        setPhoto(photo);
        return this;
    }

    public static UserTable transformUserToUserTable(User user) {
        return new UserTable()
                .withName(user.getName())
                .withEmail(user.getEmail())
                .withPhoto(user.getPhoto());
    }

    public static User transformUserTableListToUser(UserTable userTable) {
        return new User()
                .withName(userTable.getName())
                .withEmail(userTable.getEmail())
                .withPhoto(userTable.getPhoto());
    }

}
