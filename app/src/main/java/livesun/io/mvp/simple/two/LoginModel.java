package livesun.io.mvp.simple.two;

import livesun.io.mvp.retrofit.RetrofitClient;
import livesun.io.mvpmodule.base.BaseModel;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 类描述：
 * 创建人：livesun
 * 创建时间：2018/2/11.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public class LoginModel extends BaseModel implements LoginContract.Model {

    @Override
    public Observable<Long> getImageSize() {
        return RetrofitClient.getServiceApi()
                .loging()
                .map(new Func1<ResponseBody, Long>() {
                    @Override
                    public Long call(ResponseBody responseBody) {
                        long contentLength = responseBody.contentLength();
                        return contentLength;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
