package com.jpp.codingex.ui;

import com.jpp.codingex.R;
import com.jpp.codingex.background.IWordProcessorListener;
import com.jpp.codingex.background.ProcessMethod;
import com.jpp.codingex.background.WordProcessResult;
import com.jpp.codingex.background.WordProcessTask;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Fragment that shows the process work
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class MainFragment extends Fragment implements IWordProcessorListener, OnClickListener {


    public static final String TAG = MainFragment.class.getName();

    private View viewTenthCharacterContainer;
    private View viewEveryTenthCharacterContainer;
    private View viewWordCounterContainer;
    private TextView txtTenthCharacterResult;
    private TextView txtEveryTenthCharacterResult;
    private TextView txtWordCountResult;
    private Button btnExecute;

    private String mTenthCharacterResult;
    private String mEvetyTenthCharacterResult;
    private String mWordCount;

    /**
     * Class constructor
     */
    public MainFragment() {

    }

    /**
     * Factory method
     */
    public static MainFragment newInstance() {
        MainFragment instance = new MainFragment();
        instance.setRetainInstance(true);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.main_fragment, container, false);
        viewTenthCharacterContainer = fView.findViewById(R.id.tenth_character_container);
        viewEveryTenthCharacterContainer = fView.findViewById(R.id.every_tenth_character_container);
        viewWordCounterContainer = fView.findViewById(R.id.word_counter_container);
        txtTenthCharacterResult = (TextView) fView.findViewById(R.id.txt_tenth_character_result);
        txtEveryTenthCharacterResult = (TextView) fView.findViewById(R.id.txt_every_tenth_character_result);
        txtWordCountResult = (TextView) fView.findViewById(R.id.txt_word_count_result);
        btnExecute = (Button) fView.findViewById(R.id.btn_execute);
        btnExecute.setOnClickListener(this);

        restoreFromState();

        return fView;
    }

    /**
     * If the parent Activity is destroyed, this fragment will be detached from
     * it's view container (but not destroyed since
     * {@link Fragment#setRetainInstance(boolean)}is called). In such cases, the
     * fragment will recreate it's view. This method allows the fragment to
     * restore is view state from any previous state possible.
     */
    private void restoreFromState() {

        if (mTenthCharacterResult != null) {
            changeViewVisibility(View.VISIBLE, viewTenthCharacterContainer);
            txtTenthCharacterResult.setText(mTenthCharacterResult);
        }

        if (mEvetyTenthCharacterResult != null) {
            changeViewVisibility(View.VISIBLE, viewEveryTenthCharacterContainer);
            txtEveryTenthCharacterResult.setText(mEvetyTenthCharacterResult);
        }

        if (mWordCount != null) {
            changeViewVisibility(View.VISIBLE, viewWordCounterContainer);
            txtWordCountResult.setText(mWordCount);
        }
    }

    @Override
    public void onWordProcessFinished(ProcessMethod method, WordProcessResult result) {
        switch (method) {
        case TENTH_CHARACTER_REQUEST:
            changeViewVisibility(View.VISIBLE, viewTenthCharacterContainer);
            mTenthCharacterResult = String.valueOf(result.getCharacterAtTenthPositionResult());
            txtTenthCharacterResult.setText(mTenthCharacterResult);
            break;
        case EVERY_TENTH_CHARACTER_REQUEST:
            changeViewVisibility(View.VISIBLE, viewEveryTenthCharacterContainer);
            mEvetyTenthCharacterResult = new String(result.getCharacterAtEveryPositionResult());
            txtEveryTenthCharacterResult.setText(mEvetyTenthCharacterResult);
            break;
        case WORD_COUNTER_REQUEST:
            changeViewVisibility(View.VISIBLE, viewWordCounterContainer);
            mWordCount = String.valueOf(result.getWordRepetitionresult());
            txtWordCountResult.setText(mWordCount);
            break;
        }

    }

    @Override
    public void onError(ProcessMethod method) {
        switch (method) {
        case TENTH_CHARACTER_REQUEST:
            txtTenthCharacterResult.setText(R.string.generic_error);
            break;
        case EVERY_TENTH_CHARACTER_REQUEST:
            txtEveryTenthCharacterResult.setText(R.string.generic_error);
            break;
        case WORD_COUNTER_REQUEST:
            txtWordCountResult.setText(R.string.generic_error);
            break;
        }

    }

    @Override
    public void onClick(View v) {
        if (btnExecute.getId() == v.getId()) {
            execute();
        }
    }

    private void execute() {
        showProcessingView();
        // execute the first algorithm
        WordProcessTask tentCharacterTask = new WordProcessTask(this);
        tentCharacterTask.execute(ProcessMethod.TENTH_CHARACTER_REQUEST);

        // execute the second algorithm
        WordProcessTask everyTentCharacterTask = new WordProcessTask(this);
        everyTentCharacterTask.execute(ProcessMethod.EVERY_TENTH_CHARACTER_REQUEST);

        // execute the third algorithm
        WordProcessTask wordCountTask = new WordProcessTask(this);
        wordCountTask.execute(ProcessMethod.WORD_COUNTER_REQUEST);
    }

    private void showProcessingView() {
        changeViewVisibility(View.VISIBLE, viewTenthCharacterContainer, viewEveryTenthCharacterContainer, viewWordCounterContainer);
        txtTenthCharacterResult.setText(R.string.processing);
        txtEveryTenthCharacterResult.setText(R.string.processing);
        txtWordCountResult.setText(R.string.processing);
    }

    private void changeViewVisibility(int visibility, View... views) {
        for (View view : views) {
            view.setVisibility(visibility);
        }
    }

}
