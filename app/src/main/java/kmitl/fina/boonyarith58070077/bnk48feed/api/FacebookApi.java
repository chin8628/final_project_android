package kmitl.fina.boonyarith58070077.bnk48feed.api;

import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookModel;
import kmitl.fina.boonyarith58070077.bnk48feed.model.facebook.FacebookSinglePost;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FacebookApi {

    String BASE = "http://www.cloudian.in.th";

    @GET("/bnk48/{user}")
    Call<FacebookModel> getFeed(@Path("user") String user);

    @GET("/bnk48/post/{id_post}")
    Call<FacebookSinglePost> getSinglePost(@Path("id_post") String id_post);

}
