package kmitl.fina.boonyarith58070077.bnk48feed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookData;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private Activity activity;
    private List<FacebookData> facebookDataList;

    public PostAdapter(Activity activity) {
        this.activity = activity;
        facebookDataList = new ArrayList<FacebookData>();
    }

    public void setData(List<FacebookData> facebookDataList) {
        this.facebookDataList = facebookDataList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.facebook_layout, parent, false);

        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        if (facebookDataList.get(position).getAttachments() != null &&
                "photo".equals(facebookDataList.get(position).getAttachments().getData().get(0).getType())) {
            String imageUrl = facebookDataList.get(position).getAttachments().getData().get(0).getMedia().getImage().getSrc();
            String profileImageUrl = facebookDataList.get(position).getFacebookProfile().getPhotos().getData().get(0).getPicture();

            holder.name.setText(facebookDataList.get(position).getFacebookProfile().getName());
            Glide.with(activity).load(profileImageUrl).into(holder.profileImage);
            holder.message.setText(facebookDataList.get(position).getMessage());
            Glide.with(activity).load(imageUrl).into(holder.imageView);

            String time_string = facebookDataList.get(position).getCreatedTime();

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            sdf.setTimeZone(TimeZone.getDefault());

            Date time = new Date();
            try {
                time = sdf.parse(time_string);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String timeago = (String) DateUtils.getRelativeDateTimeString(
                    BNK48Feed.getAppContext(),
                    time.getTime(),
                    5000,
                    DateUtils.WEEK_IN_MILLIS,
                    1
            );

            holder.time.setText(timeago);

            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareUrl(facebookDataList.get(position).getPermalinkUrl());
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
        ImageView imageView;
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

        public Holder(View itemView) {
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
}
