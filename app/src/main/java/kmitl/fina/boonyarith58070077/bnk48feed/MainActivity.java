package kmitl.fina.boonyarith58070077.bnk48feed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kmitl.fina.boonyarith58070077.bnk48feed.model.DisplayModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookSinglePost;
import kmitl.fina.boonyarith58070077.bnk48feed.model.member.Member;
import kmitl.fina.boonyarith58070077.bnk48feed.utils.Feed;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Feed.feedListener {

    private DisplayModel displayModel = new DisplayModel();
    private Feed feed = new Feed(this, 0);
    private List<String> allBookmarkIdList = new ArrayList<>();
    private PostAdapter adapter = new PostAdapter(this, this.allBookmarkIdList);

    @BindView(R.id.recyclerView)
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);

        this.feed.getAllFeed();
        display();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, FilterActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.feed) {
            if (this.displayModel.isFacebookDataListEmpty()) {
                this.feed.getAllFeed();
            }
            display();
        } else if (id == R.id.bookmark) {
            Intent intent = new Intent(this, BookmarkActivity.class);
            startActivityForResult(intent, 2);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void display() {
        this.list.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new PostAdapter(this, this.allBookmarkIdList);
        this.adapter.setData(this.displayModel.getFilteredFacebookDataList());
        this.list.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("www", "requestCode >> " + requestCode + " " + resultCode + " " + Activity.RESULT_OK);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                List<String> member_filter = Member.getMemberFilter();

                for (String member: member_filter) {
                    if (!Member.getMemberAlreadyGetData().contains(member)) {
                        this.feed.getFeed(member);
                    }
                }

                display();
            }
        } else if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                this.feed.getAllBookmark();
                display();
            }
        }
    }

    @Override
    public void feedIsReady() {
        this.displayModel.addFacebookDataListIsDone();
        display();
    }

    @Override
    public void feedIsLoad(FacebookModel facebookModel, List<String> bookmarkIdPostList) {
        this.displayModel.addFacebookDataList(facebookModel);
        this.allBookmarkIdList = bookmarkIdPostList;
        this.adapter.setAllBookmarkIdList(bookmarkIdPostList);
    }

    @Override
    public void feedIsLoad(FacebookSinglePost facebookSinglePost, List<String> bookmarkIdPostList) {
        this.allBookmarkIdList = bookmarkIdPostList;
        this.adapter.setAllBookmarkIdList(bookmarkIdPostList);
    }
}
