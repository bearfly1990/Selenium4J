package org.bearfly.selenium.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.bearfly.selenium.models.TestCase;
import org.bearfly.selenium.models.TestStep;
import org.bearfly.selenium.models.TestStep.Operation;

public class TestCaseLoader {

    private static final String testCaseConfig = "/TestCases.xml";
    private static final List<TestCase> testCases;
    static {
        testCases = XMLUtils.getListByXPath(testCaseConfig, "//testcase", TestCase.class);
        for (TestCase tc : testCases) {
            try {
            	InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(tc.getFile());
                BufferedReader br=new BufferedReader(new InputStreamReader(is));  
            	
                //File testStepsFile = new File(ClassLoader.getSystemClassLoader().getResource(tc.getFile()).getFile());
                //BufferedReader br = new BufferedReader(new FileReader(testStepsFile));
                String readLine = "";
                while ((readLine = br.readLine()) != null && !readLine.trim().equals("")) {
                    tc.getTestSteps().add(createTestStep(readLine));
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static TestStep createTestStep(String stepStr) {
        TestStep tStep = new TestStep();
        String[] stepArray = stepStr.trim().split(" +");
        String operation = stepArray[0];
        String target = "";
        if (stepArray.length > 1) {
            target = stepArray[1];
        }

        switch (operation.toLowerCase()) {
        case "open":
            tStep.setOper(Operation.OPEN);
            break;
        case "click":
            tStep.setOper(Operation.CLICK);
            break;
        case "maxwin":
            tStep.setOper(Operation.MAXWIN);
            break;
        case "input":
            tStep.setOper(Operation.INPUT);
            break;
        default:
            break;
        }
        tStep.setTarget(target);
        return tStep;
    }

    public static String getTestcaseconfig() {
        return testCaseConfig;
    }

    public static List<TestCase> getTestcases() {
        return testCases;
    }


}
