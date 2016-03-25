import com.qiniu.http.Response;
import org.apache.commons.io.IOUtils;
import org.express.util.QiniuUtil;

/**
 * Created by bruce on 2016/3/3.
 */
public class QiniuUtilTest {

    public static void main(String[] args) {
        try {
            byte[] file = IOUtils.toByteArray("F:\\Worlk\\Java\\kufangwuyou\\server\\frontend\\src\\main\\webapp\\images\\entrust_004.png");
            Response s = QiniuUtil.upload(file, "t_0001.png");
            System.out.println(s.address);
        }catch (Exception e){
            System.out.printf(e.getMessage());
        }
    }

}
