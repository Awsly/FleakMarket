package com.ct.fleamarket;

import com.ct.utils.FastDFSClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class FleamarketApplicationTests {

    @Test
    void testClientUtil() {
        FastDFSClient client = new FastDFSClient("D:\\WorkSpace\\CodeFile\\FleaMarket\\FleakMarket-Server\\src\\main\\resources\\fast_client.conf");
        String result = client.uploadFile("C:\\Users\\CHENTUO\\Pictures\\Camera Roll\\headerImg.jpg", "jpg");
        System.out.println("路径是-->"+result66666666666666666);
    }

}
