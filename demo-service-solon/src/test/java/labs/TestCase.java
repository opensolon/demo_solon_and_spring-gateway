package labs;

import org.junit.jupiter.api.Test;
import org.noear.solon.net.http.HttpUtils;

/**
 * @author noear 2024/8/11 created
 */
public class TestCase {
    @Test
    public void case1() throws Exception {
        String rst = HttpUtils.http("http://localhost:8000/demo/model/add")
                .bodyJson("{\"modelId\":2}")
                .post();

        System.out.println(rst);

        assert "2".equals(rst);
    }

    @Test
    public void case2() throws Exception {
        String rst = HttpUtils.http("http://localhost:8000/demo/model/add")
                .data("modelId", "3")
                .post();

        System.out.println(rst);

        assert "3".equals(rst);
    }
}
