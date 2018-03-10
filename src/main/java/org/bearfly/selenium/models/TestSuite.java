package org.bearfly.selenium.models;

import java.util.List;

import org.bearfly.selenium.tools.TestCaseLoader;

public class TestSuite {
    public static void main(String[] args) {
        List<TestCase> tCase = TestCaseLoader.getTestcases();
        for(TestCase tc : tCase) {
            tc.run();
        }
    }
}
