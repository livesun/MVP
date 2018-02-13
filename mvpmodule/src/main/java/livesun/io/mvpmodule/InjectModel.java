package livesun.io.mvpmodule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：注入Model
 * 创建人：livesun
 * 创建时间：2018/2/11.
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectModel {
}
