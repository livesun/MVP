package livesun.io.mvp.simple;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import livesun.io.mvp.R;
import livesun.io.mvp.simple.two.LoginContract;
import livesun.io.mvp.simple.two.LoginPresenter;
import livesun.io.mvpmodule.base.BaseActivity;
import livesun.io.mvpmodule.InjectPresenter;

/**
 * 类描述：
 * 创建人：livesun
 * 创建时间：2018/2/11.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
public class TestActivity  extends BaseActivity<TestPresenter> implements TestContract.View,LoginContract.View{

    private Button mGetImag;
    private Button mGetImageSize1;
    private Button mGetImageSize2;

    private ImageView mImageView;
    private TextView mTextView;
    private ProgressBar mBar;
    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mGetImag = findView(R.id.testBtn1);
        mGetImageSize1=findView(R.id.testBtn2);
        mGetImageSize2 = findView(R.id.testBtn3);
        mImageView = findView(R.id.testIv);
        mTextView = findView(R.id.testTv);
        mBar = findView(R.id.pb);
        mGetImag.setOnClickListener(this);
        mGetImageSize1.setOnClickListener(this);
        mGetImageSize2.setOnClickListener(this);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v==mGetImag){
            mDefaultPresenter.getImage();
        }else if(v==mGetImageSize1){
            //注入的Presenter
            mLoginPresenter.getImageSize();
        }else if(v==mGetImageSize2){
            //默认的Presenter
            mDefaultPresenter.getImageSize();
        }
    }

    @Override
    public void onLoading() {
        mBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String error) {
        mBar.setVisibility(View.GONE);
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onSucceed(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
        mBar.setVisibility(View.GONE);
    }

    @Override
    public void getImageSizeByDefaultPresenter(Long result) {
        Toast.makeText(this,"图片大小："+result,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void getImageSizeSucess(Long result) {
        mTextView.setText("图片大小："+result);
    }
}
