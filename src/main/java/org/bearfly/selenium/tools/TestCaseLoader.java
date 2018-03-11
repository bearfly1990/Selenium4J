package org.bearfly.selenium.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.bearfly.selenium.models.TestCase;
import org.bearfly.selenium.models.TestStep;
import org.bearfly.selenium.models.TestStep.Operation;

public class TestCaseLoader {

	private static final String FILE_TC_CONFIG = "/TestCases.xml";
	private static final String NAME_TC_NODE = "//testcase";
	private static List<TestCase> testCases;
	static {
		loadTestCases();
	}

	private static void loadTestCases() {
		testCases = XMLUtils.getListByXPath(FILE_TC_CONFIG, NAME_TC_NODE, TestCase.class);
		for (TestCase tc : testCases) {
			try {
				InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(tc.getFile());
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				// File testStepsFile = new
				// File(ClassLoader.getSystemClassLoader().getResource(tc.getFile()).getFile());
				// BufferedReader br = new BufferedReader(new FileReader(testStepsFile));
				String readLine = "";
				while ((readLine = br.readLine()) != null && !readLine.trim().equals("")) {
					TestStep newStep = loadTestSteps(readLine);
					List<TestStep> tStepList = tc.getTestSteps();
					tc.getTestSteps().add(newStep);
					TestStep lastStep = tStepList.get(tStepList.size() - 1);
					newStep.setLastStep(lastStep);
					lastStep.setNextStep(newStep);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static TestStep loadTestSteps(String stepStr) {
		TestStep tStep = new TestStep();
		String[] stepArray = stepStr.trim().split("\\s{2,}|\\t");
		String operation = stepArray[0];
		String target = null;
		String content = null;
		if (stepArray.length == 2) {
			target = stepArray[1];
			content = stepArray[1];
		} else if (stepArray.length == 3) {
			target = stepArray[1];
			content = stepArray[2];
		}
		tStep.setTarget(target);
		tStep.setContent(content);
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
			if(stepArray.length == 2) {
				tStep.setTarget(null);
			}
			break;
		default:
			break;
		}
		return tStep;
	}

	public static List<TestCase> getTestcases() {
		return testCases;
	}

}
