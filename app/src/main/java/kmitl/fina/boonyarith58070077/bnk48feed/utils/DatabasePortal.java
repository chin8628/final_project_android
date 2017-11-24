package kmitl.fina.boonyarith58070077.bnk48feed.utils;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kmitl.fina.boonyarith58070077.bnk48feed.BNK48Feed;
import kmitl.fina.boonyarith58070077.bnk48feed.database.Bookmark;
import kmitl.fina.boonyarith58070077.bnk48feed.database.BookmarkDB;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookData;

public class DatabasePortal {

    private DatabasePortal.bookmarkDBListener bookmarkDBListener;
    private static BookmarkDB bookmarkDB = Room.databaseBuilder(
            BNK48Feed.getAppContext(),
            BookmarkDB.class,
            "bookmark"
    ).allowMainThreadQueries().build();

    public interface bookmarkDBListener {
        void onGetBookmark(List<Bookmark> bookmarks);
    }

    public DatabasePortal() { }

    public DatabasePortal(DatabasePortal.bookmarkDBListener bookmarkDBListener) {
        this.bookmarkDBListener = bookmarkDBListener;
    }

    @SuppressLint("StaticFieldLeak")
    public void addBookmark(FacebookData facebookData) {
        final Bookmark insertBookmark = new Bookmark();
        insertBookmark.setId_post(facebookData.getId());
        insertBookmark.setType(facebookData.getAttachments().getData().get(0).getType());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        insertBookmark.setDatetime(dateFormat.format(date));

        new AsyncTask<Void, Void, List<Bookmark>>() {
            @Override
            protected List<Bookmark> doInBackground(Void... voids) {
                return bookmarkDB.bookmarkDAO().allItem();
            }

            @Override
            protected void onPostExecute(List<Bookmark> bookmarkList) {
                boolean willBeInsert = true;

                for (Bookmark bookmark : bookmarkList) {
                    if (bookmark.getId_post().equals(insertBookmark.getId_post())) {
                        willBeInsert = false;
                    }
                }

                if (willBeInsert) {
                    bookmarkDB.bookmarkDAO().insert(insertBookmark);
                }
            }

            @Override
            protected void onProgressUpdate(Void... values) {
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void getAllBookmark() {
        new AsyncTask<Void, Void, List<Bookmark>>() {
            @Override
            protected List<Bookmark> doInBackground(Void... voids) {
                return bookmarkDB.bookmarkDAO().allItem();
            }

            @Override
            protected void onPostExecute(List<Bookmark> bookmarks) {
                bookmarkDBListener.onGetBookmark(bookmarks);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void removeBookmark(final String id_post) {
        new AsyncTask<Void, Void, List<Bookmark>>() {
            @Override
            protected List<Bookmark> doInBackground(Void... voids) {
                return bookmarkDB.bookmarkDAO().allItem();
            }

            @Override
            protected void onPostExecute(List<Bookmark> bookmarks) {
                bookmarkDB.bookmarkDAO().delete(id_post);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
            }
        }.execute();
    }
}
