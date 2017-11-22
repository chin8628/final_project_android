package kmitl.fina.boonyarith58070077.bnk48feed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    public void onBindViewHolder(Holder holder, int position) {
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
        }
    }

    @Override
    public int getItemCount() {
        return facebookDataList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView profileImage;
        TextView name;
        TextView message;
        TextView time;

        public Holder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.profile_name);
            profileImage = itemView.findViewById(R.id.profile_image);
            imageView = itemView.findViewById(R.id.imageView);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
        }
    }
}
