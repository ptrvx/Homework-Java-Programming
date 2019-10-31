package res;

import annotations.Autowire;
import annotations.Bean;
import annotations.Component;

@Bean(scope = BeanScope.prototype)
public class Third {

    @Autowire
    public Simple simple;

    @Autowire
    public Second second;

    @Autowire
    public Second secondtoo;

    @Autowire
    public InterTest interTest;
}
