package ru.javawebinar.topjava.service.Rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suntey on 18.04.2017.
 */
public class ShowMethodTimeRule implements TestRule{
    private static final Logger LOG = LoggerFactory.getLogger(ShowMethodTimeRule.class);
    public static final List<String> testTime = new ArrayList<>();
    @Override
    public Statement apply(Statement statement, Description description) {

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                LOG.info(description.getMethodName());

                long before = System.nanoTime();
                statement.evaluate();
                long after = System.nanoTime();
                LOG.info("Время выполнения {} мс \n", (after-before)/1000000);
                testTime.add(String.format("%s - %d мс", description.getMethodName(), (after-before)/1000000));
            }
        };
    }
}
