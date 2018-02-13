package livesun.io.mvp.simple;

import android.graphics.Bitmap;

import livesun.io.mvpmodule.base.BaseView;
import rx.Observable;

/**
 * 类描述：
 * 创建人：livesun
 * 创建时间：2018/2/11.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public class TestContract {
    //M
   interface Model{
        Observable<Bitmap> getImage();
    }

    //V

    interface View extends BaseView{
        void onLoading();
        void onError(String error);
        void onSucceed(Bitmap bitmap);
        void getImageSizeByDefaultPresenter(Long result);
    }

    //P
    interface Presenter{
        void getImage();
        void getImageSize();
    }
}
