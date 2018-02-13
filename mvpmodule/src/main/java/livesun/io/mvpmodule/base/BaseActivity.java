package livesun.io.mvpmodule.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 类描述：基类Activity
 * 创建人：livesun
 * 创建时间：2018/2/11.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView,View.OnClickListener{
    private MvpProxyImp mMvpProxyImp;
    protected  P mDefaultPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        mMvpProxyImp = createMvpPorxy();
        mDefaultPresenter= (P) mMvpProxyImp.bindPresenter();
        initView();
        initData();
    }

    private MvpProxyImp createMvpPorxy() {
        if(mMvpProxyImp==null){
            mMvpProxyImp=new MvpProxyImp(this);
        }
        return mMvpProxyImp;
    }

    protected <T extends View> T findView(@IdRes int resId){
        return findViewById(resId);
    }

    protected abstract void initData();


    protected abstract void initView();


    protected abstract void setContentView();

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMvpProxyImp.unBindPresenter();
    }
}
