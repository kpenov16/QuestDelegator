package com.example.questdelegator;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

public class QuestActivityViewModel extends ViewModel {
    public static final String ORIGINAL_NOTE_QUEST_ID =  "com.example.questdelegator.ORIGINAL_NOTE_QUEST_ID";
    public static final String ORIGINAL_NOTE_TITLE =  "com.example.questdelegator.ORIGINAL_NOTE_TITLE";
    public static final String ORIGINAL_NOTE_TEXT =  "com.example.questdelegator.ORIGINAL_NOTE_TEXT";
    public String mOriginalNoteQuestId;
    public String mOriginalNoteTitle;
    public String mOriginalNoteText;
    public boolean mIsNewlyCreated = true;

    public void saveState(Bundle outState) {
        outState.putString(ORIGINAL_NOTE_QUEST_ID, mOriginalNoteQuestId);
        outState.putString(ORIGINAL_NOTE_TITLE, mOriginalNoteTitle);
        outState.putString(ORIGINAL_NOTE_TEXT, mOriginalNoteText);
    }

    public void restoreState(Bundle inState) {
        mOriginalNoteQuestId = inState.getString(ORIGINAL_NOTE_QUEST_ID);
        mOriginalNoteTitle = inState.getString(ORIGINAL_NOTE_TITLE);
        mOriginalNoteText = inState.getString(ORIGINAL_NOTE_TEXT);
    }
}
