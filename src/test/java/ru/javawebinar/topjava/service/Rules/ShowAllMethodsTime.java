package ru.javawebinar.topjava.service.Rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Suntey on 18.04.2017.
 */
public class ShowAllMethodsTime implements TestRule {
       @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                statement.evaluate();
                System.out.println("-------------------");
                ShowMethodTimeRule.testTime.forEach(System.out::println);
                System.out.println("-------------------\n");
            }
        };
    }
}
