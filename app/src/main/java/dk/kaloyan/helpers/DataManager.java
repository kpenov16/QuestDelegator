package dk.kaloyan.helpers;

import java.util.ArrayList;
import java.util.List;

// Modified from the original author: Jim Wilson jimw@jwhh.com
public class DataManager {
    private static DataManager ourInstance = null;

    private List<QuestInfo> mQuests = new ArrayList<>();
    private List<NoteInfo> mNotes = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
            ourInstance.initializeQuests();
            ourInstance.initializeExampleNotes();
        }
        return ourInstance;
    }

    public String getCurrentUserName() {
        return "Kaloyan Penov";
    }

    public String getCurrentUserEmail() {
        return "s133967@student.dtu.dk";
    }

    public List<NoteInfo> getNotes() {
        return mNotes;
    }

    public int createNewNote() {
        NoteInfo note = new NoteInfo(null, null, null);
        mNotes.add(note);
        return mNotes.size() - 1;
    }

    public int findNote(NoteInfo note) {
        for(int index = 0; index < mNotes.size(); index++) {
            if(note.equals(mNotes.get(index)))
                return index;
        }

        return -1;
    }

    public void removeNote(int index) {
        mNotes.remove(index);
    }

    public List<QuestInfo> getQuests() {
        return mQuests;
    }

    public QuestInfo getQuest(String id) {
        for (QuestInfo quest : mQuests) {
            if (id.equals(quest.getQuestId()))
                return quest;
        }
        return null;
    }

    public List<NoteInfo> getNotes(QuestInfo quest) {
        ArrayList<NoteInfo> notes = new ArrayList<>();
        for(NoteInfo note: mNotes) {
            if(quest.equals(note.getQuest()))
                notes.add(note);
        }
        return notes;
    }

    public int getNoteCount(QuestInfo quest) {
        int count = 0;
        for(NoteInfo note: mNotes) {
            if(quest.equals(note.getQuest()))
                count++;
        }
        return count;
    }

    private DataManager() {
    }

    //region Initialization code

    private void initializeQuests() {
        mQuests.add(initializeQuest1());
        mQuests.add(initializeQuest2());
        mQuests.add(initializeQuest3());
        mQuests.add(initializeQuest4());
    }

    public void initializeExampleNotes() {
        final DataManager dm = getInstance();

        QuestInfo quest = dm.getQuest("android_intents");
        quest.getMilepoint("android_intents_m01").setComplete(true);
        quest.getMilepoint("android_intents_m02").setComplete(true);
        quest.getMilepoint("android_intents_m03").setComplete(true);
        mNotes.add(new NoteInfo(quest, "Dynamic intent resolution",
                "Wow, intents allow components to be resolved at runtime"));
        mNotes.add(new NoteInfo(quest, "Delegating intents",
                "PendingIntents are powerful; they delegate much more than just a component invocation"));

        quest = dm.getQuest("android_async");
        quest.getMilepoint("android_async_m01").setComplete(true);
        quest.getMilepoint("android_async_m02").setComplete(true);
        mNotes.add(new NoteInfo(quest, "Service default threads",
                "Did you know that by default an Android Service will tie up the UI thread?"));
        mNotes.add(new NoteInfo(quest, "Long running operations",
                "Foreground Services can be tied to a notification icon"));

        quest = dm.getQuest("java_lang");
        quest.getMilepoint("java_lang_m01").setComplete(true);
        quest.getMilepoint("java_lang_m02").setComplete(true);
        quest.getMilepoint("java_lang_m03").setComplete(true);
        quest.getMilepoint("java_lang_m04").setComplete(true);
        quest.getMilepoint("java_lang_m05").setComplete(true);
        quest.getMilepoint("java_lang_m06").setComplete(true);
        quest.getMilepoint("java_lang_m07").setComplete(true);
        mNotes.add(new NoteInfo(quest, "Parameters",
                "Leverage variable-length parameter lists"));
        mNotes.add(new NoteInfo(quest, "Anonymous classes",
                "Anonymous classes simplify implementing one-use types"));

        quest = dm.getQuest("java_core");
        quest.getMilepoint("java_core_m01").setComplete(true);
        quest.getMilepoint("java_core_m02").setComplete(true);
        quest.getMilepoint("java_core_m03").setComplete(true);
        mNotes.add(new NoteInfo(quest, "Compiler options",
                "The -jar option isn't compatible with with the -cp option"));
        mNotes.add(new NoteInfo(quest, "Serialization",
                "Remember to include SerialVersionUID to assure version compatibility"));
    }

    private QuestInfo initializeQuest1() {
        List<MilepointInfo> milepoints = new ArrayList<>();
        milepoints.add(new MilepointInfo("android_intents_m01", "Android Late Binding and Intents"));
        milepoints.add(new MilepointInfo("android_intents_m02", "Component activation with intents"));
        milepoints.add(new MilepointInfo("android_intents_m03", "Delegation and Callbacks through PendingIntents"));
        milepoints.add(new MilepointInfo("android_intents_m04", "IntentFilter data tests"));
        milepoints.add(new MilepointInfo("android_intents_m05", "Working with Platform Features Through Intents"));

        return new QuestInfo("android_intents", "Android Programming with Intents", milepoints);
    }

    private QuestInfo initializeQuest2() {
        List<MilepointInfo> milepoints = new ArrayList<>();
        milepoints.add(new MilepointInfo("android_async_m01", "Challenges to a responsive user experience"));
        milepoints.add(new MilepointInfo("android_async_m02", "Implementing long-running operations as a service"));
        milepoints.add(new MilepointInfo("android_async_m03", "Service lifecycle management"));
        milepoints.add(new MilepointInfo("android_async_m04", "Interacting with services"));

        return new QuestInfo("android_async", "Android Async Programming and Services", milepoints);
    }

    private QuestInfo initializeQuest3() {
        List<MilepointInfo> milepoints = new ArrayList<>();
        milepoints.add(new MilepointInfo("java_lang_m01", "Introduction and Setting up Your Environment"));
        milepoints.add(new MilepointInfo("java_lang_m02", "Creating a Simple App"));
        milepoints.add(new MilepointInfo("java_lang_m03", "Variables, Data Types, and Math Operators"));
        milepoints.add(new MilepointInfo("java_lang_m04", "Conditional Logic, Looping, and Arrays"));
        milepoints.add(new MilepointInfo("java_lang_m05", "Representing Complex Types with Classes"));
        milepoints.add(new MilepointInfo("java_lang_m06", "Class Initializers and Constructors"));
        milepoints.add(new MilepointInfo("java_lang_m07", "A Closer Look at Parameters"));
        milepoints.add(new MilepointInfo("java_lang_m08", "Class Inheritance"));
        milepoints.add(new MilepointInfo("java_lang_m09", "More About Data Types"));
        milepoints.add(new MilepointInfo("java_lang_m10", "Exceptions and Error Handling"));
        milepoints.add(new MilepointInfo("java_lang_m11", "Working with Packages"));
        milepoints.add(new MilepointInfo("java_lang_m12", "Creating Abstract Relationships with Interfaces"));
        milepoints.add(new MilepointInfo("java_lang_m13", "Static Members, Nested Types, and Anonymous Classes"));

        return new QuestInfo("java_lang", "Java Fundamentals: The Java Language", milepoints);
    }

    private QuestInfo initializeQuest4() {
        List<MilepointInfo> milepoints = new ArrayList<>();
        milepoints.add(new MilepointInfo("java_core_m01", "Introduction"));
        milepoints.add(new MilepointInfo("java_core_m02", "Input and Output with Streams and Files"));
        milepoints.add(new MilepointInfo("java_core_m03", "String Formatting and Regular Expressions"));
        milepoints.add(new MilepointInfo("java_core_m04", "Working with Collections"));
        milepoints.add(new MilepointInfo("java_core_m05", "Controlling App Execution and Environment"));
        milepoints.add(new MilepointInfo("java_core_m06", "Capturing Application Activity with the Java Log System"));
        milepoints.add(new MilepointInfo("java_core_m07", "Multithreading and Concurrency"));
        milepoints.add(new MilepointInfo("java_core_m08", "Runtime Type Information and Reflection"));
        milepoints.add(new MilepointInfo("java_core_m09", "Adding Type Metadata with Annotations"));
        milepoints.add(new MilepointInfo("java_core_m10", "Persisting Objects with Serialization"));

        return new QuestInfo("java_core", "Java Fundamentals: The Core Platform", milepoints);
    }
    //endregion

}
