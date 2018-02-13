package livesun.io.mvp.simple.two;

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
public class LoginContract {
    public interface Model{
        Observable<Long> getImageSize();
    }

    public interface View extends BaseView{
        void getImageSizeSucess(Long result);
    }

    public interface Presenter{
        void getImageSize();
    }
}
