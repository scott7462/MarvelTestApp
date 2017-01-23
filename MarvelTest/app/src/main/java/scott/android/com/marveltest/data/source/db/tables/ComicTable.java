package scott.android.com.marveltest.data.source.db.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

import scott.android.com.marveltest.data.source.db.DBConstraints;
import scott.android.com.marveltest.entities.Comic;


/**
 * Created by Pedro Scott on 1/10/17.
 */
@DatabaseTable(tableName = DBConstraints.TABLE_COMIC_USER)
public class ComicTable {

    @DatabaseField(columnName = DBConstraints.TABLE_COMIC_ID, id = true)
    private int id;
    @DatabaseField(columnName = DBConstraints.TABLE_COMIC_TITLE)
    private String title;
    @DatabaseField(columnName = DBConstraints.TABLE_COMIC_DESCRIPTION)
    private String description;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = DBConstraints.TABLE_COMIC_THUMBNAIL)
    private ThumbnailTable thumbnail;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThumbnailTable getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailTable thumbnail) {
        this.thumbnail = thumbnail;
    }

    public static ComicTable transformComic(Comic comic) {
        return new ComicTable()
                .withId(comic.getId())
                .withTitle(comic.getTitle())
                .withDescription(comic.getDescription())
                .withThumbnail(ThumbnailTable.transformTumb(comic.getThumbnail()));
    }

    private ComicTable withThumbnail(ThumbnailTable thumbnailTable) {
        setThumbnail(thumbnailTable);
        return this;
    }

    private ComicTable withDescription(String description) {
        setDescription(description);
        return this;
    }

    private ComicTable withId(int id) {
        setId(id);
        return this;
    }

    private ComicTable withTitle(String title) {
        setTitle(title);
        return this;
    }


    public static List<Comic> tansforListComic(List<ComicTable> comicTables) {
        List<Comic> comics = new ArrayList<>();
        for (ComicTable comicTable : comicTables) {
            comics.add(ComicTable.transformComicTable(comicTable));
        }

        return comics;
    }

    private static Comic transformComicTable(ComicTable comicTable) {
        return new Comic()
                .withId(comicTable.getId())
                .withDescription(comicTable.getDescription())
                .withTitle(comicTable.getTitle());
    }
}
