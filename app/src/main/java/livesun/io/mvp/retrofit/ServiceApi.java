package livesun.io.mvp.retrofit;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hcDarren on 2017/12/16.
 * 请求后台访问数据的 接口类
 */
public interface ServiceApi {
    @GET("0304shoko2-1.jpg")
    Observable<ResponseBody> getImage();

    @GET("0304shoko2-1.jpg")
    Observable<ResponseBody> loging();
}
