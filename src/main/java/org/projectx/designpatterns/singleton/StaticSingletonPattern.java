package org.projectx.designpatterns.singleton;

/**
 * INSTANCE get initialized when invoke the getInstanceMethod
 * it's hard to track exception
 */
public class StaticSingletonPattern {

    private StaticSingletonPattern() {

    }

    private static class SingletonHelper {
        private static final StaticSingletonPattern INSTANCE = new StaticSingletonPattern();
    }

    public static StaticSingletonPattern getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
