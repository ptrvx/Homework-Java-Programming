package res;

import annotations.Autowire;
import annotations.Bean;
import annotations.Component;
import annotations.Service;


//@Bean(scope = BeanScope.singleton)
@Service
//@Component
public class Simple implements InterTest {
    public String messages;

    public String message() {
        return "Hello world!";
    }
}
