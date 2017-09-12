package me.syncify.unittestandroid;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Created by adarshpandey on 9/10/17.
 */

public class PrintRules implements MethodRule {
    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("======Starting =======");
                base.evaluate();
                System.out.println("======Stop ===========");
            }
        };
    }
}
