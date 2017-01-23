package scott.android.com.marveltest.data.source.db.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import scott.android.com.marveltest.data.source.db.DBConstraints;
import scott.android.com.marveltest.entities.Thumbnail;


/**
 * Created by Pedro Scott on 1/10/17.
 */
@DatabaseTable(tableName = DBConstraints.TABLE_THUMBNAIL)
public class ThumbnailTable {

    @DatabaseField(columnName = DBConstraints.TABLE_THUMBNAIL_ID, id = true)
    private String path;
    @DatabaseField(columnName = DBConstraints.TABLE_THUMBNAIL_EXTENSION)
    private String extension;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static ThumbnailTable transformTumb(Thumbnail thumbnail) {
        return new ThumbnailTable()
                .withPath(thumbnail.getPath())
                .withExtension(thumbnail.getExtension());

    }

    private ThumbnailTable withExtension(String extension) {
        setExtension(extension);
        return this;
    }

    private ThumbnailTable withPath(String path) {
        setPath(path);
        return this;
    }
}
