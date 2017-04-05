package apk.zeffect.cn.filemanager.orm;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;

/**
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/04/05
 *      desc    ：
 *      version:：1.0
 * </pre>
 *
 * @author zzx
 */

public class FileOrm {
    private static LiteOrm liteOrm;

    public static void init(Context pContext) {
        DataBaseConfig tempBaseConfig = new DataBaseConfig(pContext, "file.db");
        LiteOrm.newSingleInstance(tempBaseConfig);
    }

    public static LiteOrm getLiteOrm() {
        return liteOrm;
    }
}
