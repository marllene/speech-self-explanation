package com.self.explanation.readerbench;

import java.util.List;


public class SelfExplanationResult {

    private SelfExplanationData data;
    private boolean success;
    private String errorMsg;

    public SelfExplanationData getData() {
        return data;
    }

    public void setData(SelfExplanationData data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private static class SelfExplanationData{
        private String selfExplanationColored;
        private List<Strategy> strategies;

        public SelfExplanationData() {
        }

        public SelfExplanationData(String selfExplanationColored, List<Strategy> strategies) {
            this.selfExplanationColored = selfExplanationColored;
            this.strategies = strategies;
        }

        public String getSelfExplanationColored() {
            return selfExplanationColored;
        }

        public void setSelfExplanationColored(String selfExplanationColored) {
            this.selfExplanationColored = selfExplanationColored;
        }

        public List<Strategy> getStrategies() {
            return strategies;
        }

        public void setStrategies(List<Strategy> strategies) {
            this.strategies = strategies;
        }
    }

    private static class Strategy{
        private String name;
        private float score;

        public Strategy() {
        }

        public Strategy(String name, float score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }
    }

}
