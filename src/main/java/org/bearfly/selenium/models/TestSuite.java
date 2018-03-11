package org.bearfly.selenium.models;

import java.util.List;

import org.bearfly.selenium.tools.TestCaseLoader;

public class TestSuite {
	private List<TestCase> testCases = TestCaseLoader.getTestcases();
    public void run() {
        for(TestCase tc : testCases) {
            tc.run();
        }
    }
}
