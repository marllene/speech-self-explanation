package com.self.explanation.readerbench;

public class SelfExplanationPayload {

    private String text;
    private String explanation;
    private String lsa;
    private String lda;
    private String lang;

    private boolean dialogism;
    private boolean postagging;

    public SelfExplanationPayload(String text, String explanation, String lsa, String lda, String lang, boolean dialogism, boolean postagging) {
        this.text = text;
        this.explanation = explanation;
        this.lsa = lsa;
        this.lda = lda;
        this.lang = lang;
        this.dialogism = dialogism;
        this.postagging = postagging;
    }

    public SelfExplanationPayload() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getLsa() {
        return lsa;
    }

    public void setLsa(String lsa) {
        this.lsa = lsa;
    }

    public String getLda() {
        return lda;
    }

    public void setLda(String lda) {
        this.lda = lda;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isDialogism() {
        return dialogism;
    }

    public void setDialogism(boolean dialogism) {
        this.dialogism = dialogism;
    }

    public boolean isPostagging() {
        return postagging;
    }

    public void setPostagging(boolean postagging) {
        this.postagging = postagging;
    }
}
