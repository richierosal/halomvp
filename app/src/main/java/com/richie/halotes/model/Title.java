
package com.richie.halotes.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Title implements Serializable
{

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("matchLevel")
    @Expose
    private String matchLevel;
    @SerializedName("fullyHighlighted")
    @Expose
    private Boolean fullyHighlighted;
    @SerializedName("matchedWords")
    @Expose
    private List<String> matchedWords = null;
    private final static long serialVersionUID = 7077141160050229585L;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    public Boolean getFullyHighlighted() {
        return fullyHighlighted;
    }

    public void setFullyHighlighted(Boolean fullyHighlighted) {
        this.fullyHighlighted = fullyHighlighted;
    }

    public List<String> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<String> matchedWords) {
        this.matchedWords = matchedWords;
    }

}
