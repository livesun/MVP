package livesun.io.mvpmodule.base;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import livesun.io.mvpmodule.InjectModel;

/**
 * 类描述：基类Presenter
 * 创建人：livesun
 * 创建时间：2018/2/11.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public class BasePresenter < V extends BaseView,M extends BaseModel> {
    private SoftReference<V> mVieweference;
    //初始化 V M
    private V mProxyView;
    private M mModel;

     void attach( V view){
        mVieweference=new SoftReference<V>(view);

        //通过动态代理
        mProxyView= (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(mVieweference==null||mVieweference.get()==null){

                    return null;
                }

                return method.invoke(mVieweference.get(),args);
            }
        });

        //创建Model对象,采用反射动态创建
        try {
            //获取父类的Type
            Type genType = getClass().getGenericSuperclass();
            //获取泛型信息
            Type[] typeArguments = ((ParameterizedType) genType).getActualTypeArguments();
            //反射创建
            mModel = (M) ((Class) typeArguments[1]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //多Model处理
        Field[] fields = getClass().getDeclaredFields();
        if(fields!=null){
            for (Field field : fields) {
                InjectModel injectModel = field.getAnnotation(InjectModel.class);
                if(injectModel!=null){
                    Class<? extends BaseModel> modelClazz = (Class<? extends BaseModel>) field.getType();
                    if(!BaseModel.class.isAssignableFrom(modelClazz)){
                        throw new IllegalArgumentException("注入类型错误，注入的不是Model类型，而是"+field.getType().getName());
                    }
                    try {
                        BaseModel baseModel = modelClazz.newInstance();
                        field.setAccessible(true);
                        field.set(this,baseModel);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

     void detach(){
        mVieweference.clear();
        mProxyView=null;
    }

    protected V getView(){
        return mProxyView;
    }
    protected M getModel(){
        return mModel;
    }
}
