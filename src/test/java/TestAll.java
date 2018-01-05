import com.java1234.dao.BaseInfoJPA;
import com.java1234.entity.BaseInfo;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/1/5.
 */
public class TestAll extends SpringTest {

    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private BaseInfoJPA baseInfoJPA;

    @Test
    public void test() {
        BaseInfo info = baseInfoJPA.findOne(250);
        Context context = new Context();
        context.setVariable("info", info);
        System.out.println(templateEngine.process("template_detail", context));
    }


}
