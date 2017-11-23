package kmitl.fina.boonyarith58070077.bnk48feed.model.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import kmitl.fina.boonyarith58070077.bnk48feed.api.FacebookApi;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookModel;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private Api.apiListener apiListener;

    public interface apiListener{
        void onFetchSuccess(String member_name, FacebookModel facebookModel);
        void onFetchFailed(String member_name);
    }

    public Api(Api.apiListener apiListener) {
        this.apiListener = apiListener;
    }

    public void callApi(final String name) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FacebookApi.BASE)
                .client(client)
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

    private void onFailed(String name) {
        this.apiListener.onFetchFailed(name);
    }

    private void onSuccess(String name, FacebookModel facebookModel) {
        this.apiListener.onFetchSuccess(name, facebookModel);
    }
}
