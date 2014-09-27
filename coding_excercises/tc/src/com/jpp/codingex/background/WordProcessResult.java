package com.jpp.codingex.background;

/**
 * Class that wraps the result of a method process applied to a content.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class WordProcessResult {


    private ProcessMethod mMethod;
    private char mCharacterAtTenthPosition;
    private char[] mCharacterAtEveryPositionResult;
    private int mWordRepetitionResult;
    private boolean hasProcessfailed;

    WordProcessResult(ProcessMethod method) {
        mMethod = method;
    }

    public ProcessMethod getMethod() {
        return mMethod;
    }

    void setCharacterAtTenthPositionResult(char character) {
        mCharacterAtTenthPosition = character;
    }

    public char getCharacterAtTenthPositionResult() {
        return mCharacterAtTenthPosition;
    }

    void setCharacterAtEveryPositionresult(char[] result) {
        mCharacterAtEveryPositionResult = result;
    }

    public char[] getCharacterAtEveryPositionResult() {
        return mCharacterAtEveryPositionResult;
    }

    void setWordRepetitioResult(int result) {
        mWordRepetitionResult = result;
    }

    public int getWordRepetitionresult() {
        return mWordRepetitionResult;
    }

    boolean hasProcessFailed() {
        return hasProcessfailed;
    }

    void setAsFailed() {
        hasProcessfailed = true;
    }
}
