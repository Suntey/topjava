package ru.javawebinar.topjava.service.Rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Suntey on 18.04.2017.
 */
public class ShowAllMethodsTime implements TestRule {
    private static final Logger LOG = LoggerFactory.getLogger(ShowAllMethodsTime.class);
       @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                statement.evaluate();
                LOG.info("Return timing list of all methods");
                ShowMethodTimeRule.testTime.forEach(System.out::println);
            }
        };
    }
}
