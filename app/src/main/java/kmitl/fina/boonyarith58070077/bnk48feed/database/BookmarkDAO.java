package kmitl.fina.boonyarith58070077.bnk48feed.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BookmarkDAO {

    @Insert
    void insert(Bookmark money);

    @Query("SELECT * FROM bookmark ORDER BY datetime DESC;")
    List<Bookmark> allItem();

    @Query("DELETE FROM bookmark WHERE id_post = :id_post")
    void delete(String id_post);
}
