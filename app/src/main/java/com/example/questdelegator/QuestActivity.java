package com.example.questdelegator;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import dk.kaloyan.helpers.DataManager;
import dk.kaloyan.helpers.NoteInfo;
import dk.kaloyan.helpers.QuestInfo;

public class QuestActivity extends AppCompatActivity {
    public static final String NOTE_POSITION = "dk.kaloyan.helpers.NOTE_POSITION";
    public static final int POSITION_NOT_SET = -1;
    private NoteInfo mNoteInfo;
    private boolean mIsNewNote;
    private Spinner mSpinnerQuests;
    private EditText mTextNoteTitle;
    private EditText mTextNoteText;
    private int mNotePosition;
    private boolean mIsCanceling;
    private QuestActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));

        mViewModel = viewModelProvider.get(QuestActivityViewModel.class);

        if(mViewModel.mIsNewlyCreated && savedInstanceState != null){
            mViewModel.restoreState(savedInstanceState);
        }
        mViewModel.mIsNewlyCreated = false;


        mSpinnerQuests = findViewById(R.id.spinner_quests);

        List<QuestInfo> quests = DataManager.getInstance().getQuests();
        ArrayAdapter<QuestInfo> adapterQuests = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quests);
        adapterQuests.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerQuests.setAdapter(adapterQuests);

        readDisplayStateValues();
        saveOriginalNoteValues();

        mTextNoteTitle = findViewById(R.id.text_quest_title);
        mTextNoteText = findViewById(R.id.text_quest_text);

        if(!mIsNewNote)
            displayNote(mSpinnerQuests, mTextNoteTitle, mTextNoteText);
    }

    private void saveOriginalNoteValues() {
        if(mIsNewNote)
            return;
        mViewModel.mOriginalNoteQuestId = mNoteInfo.getQuest().getQuestId();
        mViewModel.mOriginalNoteTitle = mNoteInfo.getTitle();
        mViewModel.mOriginalNoteText= mNoteInfo.getText();
    }

    private void displayNote(Spinner spinnerQuests, EditText textNoteTitle, EditText textNoteText) {
        List<QuestInfo> quests = DataManager.getInstance().getQuests();
        int questIndex = quests.indexOf(mNoteInfo.getQuest());
        spinnerQuests.setSelection(questIndex);
        textNoteTitle.setText(mNoteInfo.getTitle());
        textNoteText.setText(mNoteInfo.getText());

    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
        mIsNewNote = position == POSITION_NOT_SET ? true : false;
        if(mIsNewNote){
            createNewNote();
        }else {
            mNoteInfo = DataManager.getInstance().getNotes().get(position);
        }

    }

    private void createNewNote() {
        DataManager dm = DataManager.getInstance();
        mNotePosition = dm.createNewNote();
        mNoteInfo = dm.getNotes().get(mNotePosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send_mail) {
            sendEmail();
            return true;
        }else if (id == R.id.action_cancel){
            mIsCanceling = true;
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendEmail() {
        QuestInfo questInfo = (QuestInfo) mSpinnerQuests.getSelectedItem();
        String subject = mTextNoteTitle.getText().toString();
        String text = String.format("Checkout what I need you to do if you have a time today \"%s\"\n%s",
                                     questInfo.getTitle(), mTextNoteText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mIsCanceling) {
            if(mIsNewNote){
                DataManager.getInstance().removeNote(mNotePosition);
            }else {
                storePreviousNoteValues();
            }
        }else {
            saveNote();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null){
            mViewModel.saveState(outState);
        }
    }

    private void storePreviousNoteValues() {
        QuestInfo questInfo = DataManager.getInstance().getQuest(mViewModel.mOriginalNoteQuestId);
        mNoteInfo.setQuest(questInfo);
        mNoteInfo.setTitle(mViewModel.mOriginalNoteTitle);
        mNoteInfo.setText(mViewModel.mOriginalNoteText);
    }

    private void saveNote() {
        mNoteInfo.setQuest((QuestInfo) mSpinnerQuests.getSelectedItem());
        mNoteInfo.setTitle(mTextNoteTitle.getText().toString());
        mNoteInfo.setText(mTextNoteText.getText().toString());
    }
}























