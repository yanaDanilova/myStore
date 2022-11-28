import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MyStoreAppAuthSecurityTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void securityAccessAllowedTest(){
        mockMvc.perform(get("/api/v1/products")).andDo(print()).andExpect(status().isOk());

    }
}
