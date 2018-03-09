package org.bearfly.selenium.tools;

import java.util.List;

import org.bearfly.selenium.models.TestCase;

public class TestSuite {
    public static void main(String[] args) {
        List<TestCase> tCase = TestCaseLoader.getTestcases();
        for(TestCase tc : tCase) {
            tc.run();
        }
    }
}
