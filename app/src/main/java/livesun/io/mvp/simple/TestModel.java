package livesun.io.mvp.simple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
public class TestModel extends BaseModel implements TestContract.Model {
    @Override
    public Observable<Bitmap> getImage() {
        return RetrofitClient.getServiceApi()
                .getImage()
                .map(new Func1<ResponseBody, Bitmap>() {
                    @Override
                    public Bitmap call(ResponseBody responseBody) {
                        return BitmapFactory.decodeStream(responseBody.byteStream());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
