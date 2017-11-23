package kmitl.fina.boonyarith58070077.bnk48feed.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Bookmark.class}, version = 1)
public abstract class BookmarkDB extends RoomDatabase {
    public abstract BookmarkDAO bookmarkDAO();
}