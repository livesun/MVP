package livesun.io.mvpmodule.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类描述：基类Fragment
 * 创建人：livesun
 * 创建时间：2018/2/13.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public abstract class BaseFragment <P extends BasePresenter> extends Fragment implements BaseView,View.OnClickListener {

    private View mRoot;
    private MvpProxyImp mMvpProxyImp;
    protected  P mDefaultPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMvpProxyImp=createMvpPorxy();
        mDefaultPresenter= (P) mMvpProxyImp.bindPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRoot==null){
            mRoot = inflater.inflate(getResId(), container, false);
            initView();
            initData();
        }
        return mRoot;
    }

    private MvpProxyImp createMvpPorxy() {
        if(mMvpProxyImp==null){
            mMvpProxyImp=new MvpProxyImp(this);
        }
        return mMvpProxyImp;
    }
    protected <T extends View> T findView(@IdRes int resId){
        return mRoot.findViewById(resId);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getResId();

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMvpProxyImp.unBindPresenter();
    }
}
