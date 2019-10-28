package engine;

import annotations.Autowire;
import annotations.Bean;
import annotations.Component;
import annotations.Service;
import res.BeanScope;
import res.Simple;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DIEngine {

    private HashMap<Class, Object> singleton = new HashMap<>();
    private DependencySupplier supplier = new DependencySupplier();

    private BeanScope getScope(Class cls) {
        Bean beanAnn = (Bean)cls.getAnnotation(Bean.class);
        Service serviceAnn = (Service)cls.getAnnotation(Service.class);
        Component componentAnn = (Component)cls.getAnnotation(Component.class);

        if (beanAnn != null) {
            return beanAnn.scope();
        } else if (serviceAnn != null) {
            return BeanScope.singleton;
        } else {
            return BeanScope.prototype;
        }
    }

    public Object init(Class cls) {

        BeanScope scope = getScope(cls);

        if (scope.equals(BeanScope.singleton)) {
            if (singleton.containsKey(cls))
                return singleton.get(cls);
        }


        try {
            Constructor constructor;

            if (cls.isInterface()) {
                constructor = supplier.getImplementation(cls).getDeclaredConstructor();
            } else {
                constructor = cls.getDeclaredConstructor();
            }

            Object o = constructor.newInstance();

            for (Field f : cls.getDeclaredFields()) {
                Autowire wired = f.getAnnotation(Autowire.class);
                if (wired != null) {
                    f.setAccessible(true);
                    Object inject = init(f.getType());
                    f.set(o, inject);

                    if (wired.verbose()) {
                        System.out.println("Initialized " + f.getType() + " " + f.getName() + " in " + cls.getName() + " on " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + " with " + inject.hashCode());
                    }
                }
            }

            if (scope.equals(BeanScope.singleton)) {
                singleton.put(cls, o);
            }

            return o;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
