package kmitl.fina.boonyarith58070077.bnk48feed.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import kmitl.fina.boonyarith58070077.bnk48feed.api.FacebookApi;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookSinglePost;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private Api.apiListener apiListener;
    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    public interface apiListener{
        void onFetchSuccess(String member_name, FacebookModel facebookModel);
        void onFetchSuccess(String member_name, FacebookSinglePost facebookSinglePost);
        void onFetchFailed(String member_name);
    }

    public Api(Api.apiListener apiListener) {
        this.apiListener = apiListener;
    }

    public void callFeedApi(final String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FacebookApi.BASE)
                .client(this.client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FacebookApi api = retrofit.create(FacebookApi.class);
        api.getFeed(name).enqueue(new retrofit2.Callback<FacebookModel>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<FacebookModel> call, @NonNull retrofit2.Response<FacebookModel> response) {
                Log.d("www", "onResponse: " + name);;
                onSuccess(name, response.body());
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<FacebookModel> call, @NonNull Throwable t) {
                Log.d("www", "onFailure " + name + " >> " + t.toString());
                onFailed(name);
                call.clone().enqueue(this);
            }
        });
    }

    public void callSinglePostApi(final String id_post) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FacebookApi.BASE)
                .client(this.client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FacebookApi api = retrofit.create(FacebookApi.class);
        api.getSinglePost(id_post).enqueue(new retrofit2.Callback<FacebookSinglePost>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<FacebookSinglePost> call, @NonNull retrofit2.Response<FacebookSinglePost> response) {
                Log.d("www", "onResponse: " + id_post);;
                onSuccess(id_post, response.body());
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<FacebookSinglePost> call, @NonNull Throwable t) {
                Log.d("www", "onFailure " + id_post + " >> " + t.toString());
                onFailed(id_post);
                call.clone().enqueue(this);
            }
        });
    }

    private void onFailed(String name) {
        this.apiListener.onFetchFailed(name);
    }

    private void onSuccess(String name, FacebookModel facebookModel) {
        this.apiListener.onFetchSuccess(name, facebookModel);
    }

    private void onSuccess(String id_post, FacebookSinglePost facebookSinglePost) {
        this.apiListener.onFetchSuccess(id_post, facebookSinglePost);
    }
}
