## MVP
动态创建Presenter和Model，并支持多Presenter多Model，不用关心P和M的创建，处理了P和V的解绑

### 效果
 
 
### 使用
  #### 依赖
 ```
 allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ```
 ```
 dependencies {
	        compile 'com.github.livesun:MVP:v1.1'
	}
  
 ```
 #### Presenter
  默认Presenter 为mDefaultPresenter
  动态注入 @InjectPresenter
  例如：
 ```
 @InjectPresenter
 LoginPresenter mLoginPresenter;
 
 ```
  #### Model
  默认Model 为getModel();
  动态注入 @InjectModel
  例如：
 ```
@InjectModel
 LoginModel mLoginModel;
 
 ```
 #### View
 获取View getView();
 
 #### 详看demo代码
 
