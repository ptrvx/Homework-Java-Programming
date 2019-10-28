package res;

import annotations.Autowire;
import annotations.Component;

@Component
public class Second {
    public String message;

    @Autowire
    public Simple simple;

    @Autowire
    public Simple simpletoo;

    @Autowire
    public InterTest inter;
}
