package kmitl.fina.boonyarith58070077.bnk48feed;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.feed.FacebookFeedData;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.profile.FacebookProfileModel;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private Activity activity;
    private List<FacebookFeedData> facebookFeedData;
    private FacebookProfileModel facebookProfileModel;

    public PostAdapter(Activity activity) {
        this.activity = activity;
        facebookFeedData = new ArrayList<FacebookFeedData>();
    }

    public void setData(List<FacebookFeedData> facebookFeedData, FacebookProfileModel facebookProfileModel) {
        this.facebookFeedData = facebookFeedData;
        this.facebookProfileModel = facebookProfileModel;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.facebook_layout, parent, false);

        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if ("photo".equals(facebookFeedData.get(position).getAttachments().getData().get(0).getType())) {
            String imageUrl = facebookFeedData.get(position).getAttachments().getData().get(0).getMedia().getImage().getSrc();
            String profileImageUrl = facebookProfileModel.getPhotos().getData().get(0).getPicture();

            holder.name.setText(facebookProfileModel.getName());
            Glide.with(activity).load(profileImageUrl).into(holder.profileImage);
            holder.message.setText(facebookFeedData.get(position).getMessage());
            Glide.with(activity).load(imageUrl).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return facebookFeedData.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView profileImage;
        TextView name;
        TextView message;

        public Holder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.profile_name);
            profileImage = itemView.findViewById(R.id.profile_image);
            imageView = itemView.findViewById(R.id.imageView);
            message = itemView.findViewById(R.id.message);
        }
    }
}
