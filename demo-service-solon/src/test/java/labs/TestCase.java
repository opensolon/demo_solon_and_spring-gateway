package labs;

import org.junit.jupiter.api.Test;
import org.noear.solon.net.http.HttpUtils;

/**
 * @author noear 2024/8/11 created
 */
public class TestCase {
    @Test
    public void case_post_json() throws Exception {
        String rst = HttpUtils.http("http://localhost:8000/demo/model/add")
                .bodyJson("{\"modelId\":2}")
                .post();

        System.out.println(rst);

        assert "2".equals(rst);
    }

    @Test
    public void case_post_form() throws Exception {
        String rst = HttpUtils.http("http://localhost:8000/demo/model/add")
                .data("modelId", "3")
                .post();

        System.out.println(rst);

        assert "3".equals(rst);
    }

    @Test
    public void case_get() throws Exception {
        String rst = HttpUtils.http("http://localhost:8000/demo/model/add?modelId=4")
                .get();

        System.out.println(rst);

        assert "4".equals(rst);
    }
}
