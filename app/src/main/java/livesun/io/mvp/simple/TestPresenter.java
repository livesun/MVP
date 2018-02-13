package livesun.io.mvp.simple;

import android.graphics.Bitmap;

import livesun.io.mvp.simple.two.LoginModel;
import livesun.io.mvpmodule.base.BasePresenter;
import livesun.io.mvpmodule.InjectModel;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * 类描述：
 * 创建人：livesun
 * 创建时间：2018/2/11.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public class TestPresenter extends BasePresenter<TestContract.View,TestModel> implements TestContract.Presenter {

    //注入的多Model
    @InjectModel
    LoginModel mLoginModel;

    @Override
    public void getImage() {
        getView().onLoading();
        //默认的Model
        getModel().getImage().subscribe(new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().onError(e.getMessage());
            }

            @Override
            public void onNext(Bitmap bitmap) {
                getView().onSucceed(bitmap);
            }
        });
    }

    @Override
    public void getImageSize() {
        mLoginModel.getImageSize()
        .subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                getView().getImageSizeByDefaultPresenter(aLong);
            }
        });
    }


}
