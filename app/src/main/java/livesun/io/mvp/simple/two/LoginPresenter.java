package livesun.io.mvp.simple.two;

import livesun.io.mvpmodule.base.BasePresenter;
import rx.functions.Action1;

/**
 * 类描述：
 * 创建人：livesun
 * 创建时间：2018/2/11.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public class LoginPresenter extends BasePresenter<LoginContract.View,LoginModel> implements LoginContract.Presenter{

    //获取图片大小
    @Override
    public void getImageSize() {
        getModel().getImageSize()
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long size) {
                        getView().getImageSizeSucess(size);
                    }
                });
    }
}
