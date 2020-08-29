package com.example.questdelegator;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import dk.kaloyan.helpers.DataManager;
import dk.kaloyan.helpers.NoteInfo;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, QuestActivity.class));
            }
        });

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapterNotes.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        final ListView listOfNotes  = findViewById(R.id.list_notes);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        mAdapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        listOfNotes.setAdapter(mAdapterNotes);

        listOfNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Ops. 'NoteListActivity.this' refers to the outer instance not to the anonymous 'this' which is the 'new AdapterView.OnItemClickListener(){...}'
                //dvs. the current instance of the NoteListActivity is the starting context and the target context is a new instance of QuestActivity
                Intent intent = new Intent(NoteListActivity.this, QuestActivity.class); //https://stackoverflow.com/questions/5530256/java-class-this
                //NoteInfo note = (NoteInfo) listOfNotes.getItemAtPosition(position);
                intent.putExtra(QuestActivity.NOTE_POSITION, position);
                startActivity(intent);

            }
        });

    }
}