package com.jpp.codingex.background;

import com.jpp.codingex.Constants;

import android.os.AsyncTask;

/**
 * An {@link AsyncTask} that executes a {@link ProcessMethod} on the background
 * and notifies a {@link IWordProcessorListener} when the process has over. The
 * call to any of the methods of {@link IWordProcessorListener} is made in the
 * thread that first called this class's methods.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class WordProcessTask extends AsyncTask<ProcessMethod, Void, WordProcessResult> {


    private IWordProcessorListener mListener;

    public WordProcessTask(IWordProcessorListener listener) {
        mListener = listener;
    }

    @Override
    protected WordProcessResult doInBackground(ProcessMethod... params) {
        ProcessMethod method = params[0];
        WordProcessResult result = null;
        if (method != null) {
            result = new WordProcessResult(method);
            String content = HttpManager.execute(Constants.TRUECALLER_WEB);
            try {
                switch (method) {
                case TENTH_CHARACTER_REQUEST:
                    char characterAtTenthPosition = ContentProcessor.findCharacterAtPosition(content, 10);
                    result.setCharacterAtTenthPositionResult(characterAtTenthPosition);
                    break;
                case EVERY_TENTH_CHARACTER_REQUEST:
                    char[] characterAtEveryPositionResult = ContentProcessor.findCharacterAtEveryPosition(content, 10);
                    result.setCharacterAtEveryPositionresult(characterAtEveryPositionResult);
                    break;
                case WORD_COUNTER_REQUEST:
                    int wordRepetitionResult = ContentProcessor.findWordRepetition(content, Constants.SPACE, Constants.MOBILE);
                    result.setWordRepetitioResult(wordRepetitionResult);
                    break;
                }
            } catch (Exception e) {
                result.setAsFailed();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(WordProcessResult result) {
        if (mListener != null) {
            if (result.hasProcessFailed()) {
                mListener.onError(result.getMethod());
            } else {
                mListener.onWordProcessFinished(result.getMethod(), result);
            }
        }
    }

}
