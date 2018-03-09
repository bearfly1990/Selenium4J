package org.bearfly.selenium.models

import org.bearfly.selenium.tools.SeleniumUtils;

public class TestStep {
    public enum Operation {
        OPEN("open"), CLICK("click"), INPUT("input"), MAXWIN("maxwin");
        private String oper;

        Operation(String oper) {
            this.oper = oper;
        }

        public String oper() {
            return oper;
        }
    }

    private Operation oper;
    private String target = "";

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
            switch (target.charAt(0)) {
            case '#':
                SeleniumUtils.clickByID(target.substring(1));break;
            case '@':
                SeleniumUtils.clickByName(target.substring(1));break;
            }
            break;
        case INPUT:
            SeleniumUtils.inputValue(target);
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
}
