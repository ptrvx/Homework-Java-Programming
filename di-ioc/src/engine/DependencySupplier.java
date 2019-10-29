package engine;

import res.InterTest;
import res.Simple;

import java.util.HashMap;

public class DependencySupplier {
    private HashMap<Class, Class> dependencies = new HashMap<>();

    public DependencySupplier() {
        dependencies.put(InterTest.class, Simple.class);
    }

    public Class getImplementation(Class cls) {
        return dependencies.get(cls);
    }

}
