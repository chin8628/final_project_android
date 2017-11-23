package kmitl.fina.boonyarith58070077.bnk48feed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookData;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookSinglePost;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.SubattachmentData;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.Subattachments;
import kmitl.fina.boonyarith58070077.bnk48feed.utils.DatabasePortal;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private Activity activity;
    private List<FacebookData> facebookDataList;
    private final List<String> type_filter = new ArrayList<>(Arrays.asList("photo", "video_inline", "album"));

    public PostAdapter(Activity activity) {
        this.activity = activity;
        facebookDataList = new ArrayList<>();
    }

    public void setData(List<FacebookData> facebookDataList) {
        this.facebookDataList = facebookDataList;
    }

    public void setData(List<FacebookSinglePost> facebookSinglePosts, int flag) {
        if (flag == 1) {
            List<FacebookData> facebookDataList = new ArrayList<>();

            for (FacebookSinglePost facebookSinglePost: facebookSinglePosts) {
                FacebookData facebookData = new FacebookData();

                facebookData.setFacebookProfile(facebookSinglePost.getFacebookProfile());
                facebookData.setAttachments(facebookSinglePost.getAttachments());
                facebookData.setCreatedTime(facebookSinglePost.getCreatedTime());
                facebookData.setId(facebookSinglePost.getId());
                facebookData.setPermalinkUrl(facebookSinglePost.getPermalinkUrl());
                facebookData.setMessage(facebookSinglePost.getMessage());

                facebookDataList.add(facebookData);
            }

            this.facebookDataList = facebookDataList;
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.facebook_layout, parent, false);

        return new Holder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(Holder holder, @SuppressLint("RecyclerView") final int position) {
        String type = this.facebookDataList.get(position).getAttachments().getData().get(0).getType();

        if (facebookDataList.get(position).getAttachments() != null && type_filter.contains(type)) {
            String profileImageUrl = facebookDataList.get(position).getFacebookProfile().getPhotos().getData().get(0).getPicture();
            String time_string = facebookDataList.get(position).getCreatedTime();

            List<Banner> banners = new ArrayList<>();
            if (type_filter.indexOf(type) == 2) {
                Subattachments subattachments = facebookDataList.get(position).getAttachments().getData().get(0).getSubattachments();
                for(SubattachmentData subattachmentData: subattachments.getData()) {
                    String image_url = subattachmentData.getMedia().getImage().getSrc();
                    banners.add(new RemoteBanner(image_url));
                }
            } else {
                String image_url = facebookDataList.get(position).getAttachments().getData().get(0).getMedia().getImage().getSrc();
                banners.add(new RemoteBanner(image_url));
            }
            holder.imageView.setBanners(banners);

            holder.name.setText(facebookDataList.get(position).getFacebookProfile().getName());
            Glide.with(activity).load(profileImageUrl).into(holder.profileImage);
            holder.message.setText(facebookDataList.get(position).getMessage());
//            holder.bookmark.setImageIcon(R.id);

            switch (type_filter.indexOf(type)) {
                case 0:
                    holder.tag.setText("Photo");
                    break;
                case 1:
                    holder.tag.setText("Video");
                    break;
                case 2:
                    holder.tag.setText("Album");
                    break;
            }

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            sdf.setTimeZone(TimeZone.getDefault());

            Date time = new Date();
            try {
                time = sdf.parse(time_string);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String timeago = (String) DateUtils.getRelativeDateTimeString(BNK48Feed.getAppContext(), time.getTime(),5000, DateUtils.WEEK_IN_MILLIS,1);
            holder.time.setText(timeago);

            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareUrl(facebookDataList.get(position).getPermalinkUrl());
                }
            });

            holder.browser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openOnBrowser(facebookDataList.get(position).getPermalinkUrl());
                }
            });

            holder.bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatabasePortal().addBookmark(facebookDataList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return facebookDataList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        BannerSlider imageView;
        @BindView(R.id.profile_image)
        ImageView profileImage;
        @BindView(R.id.profile_name)
        TextView name;
        @BindView(R.id.message)
        TextView message;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.shareBtn)
        ImageButton share;
        @BindView(R.id.browserBtn)
        ImageButton browser;
        @BindView(R.id.tag_content)
        TextView tag;
        @BindView(R.id.bookmarkBtn)
        ImageButton bookmark;

        private Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void shareUrl(String url) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        share.putExtra(Intent.EXTRA_TEXT, url);

        activity.startActivity(Intent.createChooser(share, "Share!"));
    }

    private void openOnBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(browserIntent);
    }
}
