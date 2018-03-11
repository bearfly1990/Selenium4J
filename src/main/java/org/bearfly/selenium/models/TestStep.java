package org.bearfly.selenium.models;

import org.bearfly.selenium.tools.SeleniumUtils;

public class TestStep {
	public enum Operation {
		OPEN("open"), CLICK("click"), INPUT("input"), MAXWIN("maxwin");
		private String value;

		Operation(String value) {
			this.value = value;
		}

		public String value() {
			return value;
		}
	}

	private Operation oper;
	private String target;
	private String content;
	private TestStep lastStep;
	private TestStep nextStep;

	public TestStep(Operation oper, String target) {
		super();
		this.oper = oper;
		this.target = target;
	}

	public TestStep() {
	}

	public void run() {
		switch (oper) {
		case OPEN:
			SeleniumUtils.openBrowser(target);
			break;
		case MAXWIN:
			SeleniumUtils.maxWindow();
			break;
		case CLICK:
			SeleniumUtils.findElementByString(target).click();
			break;
		case INPUT:
			if(target == null) {
				SeleniumUtils.inputValue(content);
			}else {
				SeleniumUtils.inputValue(target, content);
			}
			break;
		default:
			break;
		}
	}

	public Operation getOper() {
		return oper;
	}

	public void setOper(Operation oper) {
		this.oper = oper;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TestStep getLastStep() {
		return lastStep;
	}

	public void setLastStep(TestStep lastStep) {
		this.lastStep = lastStep;
	}

	public TestStep getNextStep() {
		return nextStep;
	}

	public void setNextStep(TestStep nextStep) {
		this.nextStep = nextStep;
	}
}
