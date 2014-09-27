package com.jpp.codingex.background;

/**
 * Interface that defines the signature of any interested subject on word
 * processing.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public interface IWordProcessorListener {


    /**
     * Called when the word processing has finished.
     * 
     * @param method
     *            - the {@link ProcessMethod} finished.
     * @param result
     *            - the {@link WordProcessResult} obtained.
     */
    public void onWordProcessFinished(ProcessMethod method, WordProcessResult result);

    /**
     * Called when an error occurs on the word processing process.
     * 
     * @param method
     *            - the {@link ProcessMethod} that failed.
     */
    public void onError(ProcessMethod method);

}
