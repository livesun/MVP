package livesun.io.mvpmodule.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import livesun.io.mvpmodule.InjectPresenter;

/**
 * 类描述：创建Presenter的代理类
 * 创建人：livesun
 * 创建时间：2018/2/13.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public class MvpProxyImp implements IMvpProxy {

    private BaseView mView;
    private BasePresenter mDefaultPresenter;
    private List<BasePresenter> mBasePresenters;
     MvpProxyImp(BaseView view){
        mView=view;
        mBasePresenters=new ArrayList<>();
    }

    @Override
    public BasePresenter bindPresenter() {
        //动态创建Presenter
        try {
            Type genType = mView.getClass().getGenericSuperclass();
            Type[] typeArguments = ((ParameterizedType) genType).getActualTypeArguments();
            mDefaultPresenter= (BasePresenter) ((Class)typeArguments[0]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //绑定Presenter
        mDefaultPresenter.attach(mView);
        //其余的Presenter
        Field[] fields = mView.getClass().getDeclaredFields();
        if(fields!=null){
            for (Field field : fields) {
                InjectPresenter annotation = field.getAnnotation(InjectPresenter.class);
                if(annotation!=null){
                    Class<? extends BasePresenter> presenterClazz = (Class<? extends BasePresenter>) field.getType();
                    if(!BasePresenter.class.isAssignableFrom(presenterClazz)){
                        throw new IllegalArgumentException("类型注入错误，注入的不是Presenter类型，而是"+field.getType().getName());
                    }
                    try {
                        BasePresenter basePresenter = presenterClazz.newInstance();
                        basePresenter.attach(mView);
                        field.setAccessible(true);
                        field.set(mView,basePresenter);
                        mBasePresenters.add(basePresenter);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return mDefaultPresenter;
    }

    @Override
    public void unBindPresenter() {
        for (BasePresenter presenter : mBasePresenters) {
            presenter.detach();
        }
        mDefaultPresenter.detach();
        mView=null;
    }
}
