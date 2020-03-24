package com.example.evernote;
import androidx.annotation.NonNull;
public class Note {
    String feedback;
    public Note(){
    }
    public Note(String feedback)
    {
        this.feedback = feedback;
    }
    public void setFeedback(String feedback)
    {
        this.feedback = feedback;
    }
   @NonNull
    public String getFeedback()
    {
        return feedback;
    }
}
