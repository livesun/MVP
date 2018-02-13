package livesun.io.mvpmodule.base;

/**
 * 类描述：
 * 创建人：livesun
 * 创建时间：2018/2/13.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public interface IMvpProxy {
    /**
     * 绑定Presenter
     */
   BasePresenter bindPresenter();

    /**
     * 解绑Presenter
     */
   void unBindPresenter();
}
