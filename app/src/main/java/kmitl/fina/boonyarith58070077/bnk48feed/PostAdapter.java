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

import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.feed.Datum;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private Activity activity;
    private List<Datum> data;

    public PostAdapter(Activity activity) {
        this.activity = activity;
        data = new ArrayList<Datum>();
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.facebook_layout, parent, false);

        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if ("photo".equals(data.get(position).getAttachments().getData().get(0).getType())) {
            String imageUrl = data.get(position).getAttachments().getData().get(0).getMedia().getImage().getSrc();
            holder.message.setText(data.get(position).getMessage());
            Glide.with(activity).load(imageUrl).into(holder.imageView);

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView message;

        public Holder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            message = itemView.findViewById(R.id.message);
        }
    }
}
