package engine;

import res.InterTest;
import res.Simple;

import java.util.HashMap;

public class DependencySupplier {
    private HashMap<Class, Class> dependecies = new HashMap<>();

    public DependencySupplier() {
        dependecies.put(InterTest.class, Simple.class);
    }

    public Class getImplementation(Class cls) {
        return dependecies.get(cls);
    }

}
