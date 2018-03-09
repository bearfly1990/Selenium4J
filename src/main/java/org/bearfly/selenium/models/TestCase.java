package org.bearfly.selenium.models;

import java.util.ArrayList;
import java.util.List;

public class TestCase {
	private String name;
	private String file;
	private List<TestStep> testSteps = new ArrayList<TestStep>();
	
	public void run() {
	    for(TestStep ts : testSteps) {
	        ts.run();
	    }
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
    public List<TestStep> getTestSteps() {
        return testSteps;
    }
    public void setTestSteps(List<TestStep> testSteps) {
        this.testSteps = testSteps;
    }
    
}
