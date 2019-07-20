
package com.richie.halotes.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit implements Serializable
{

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("story_text")
    @Expose
    private String storyText;
    @SerializedName("comment_text")
    @Expose
    private String commentText;
    @SerializedName("num_comments")
    @Expose
    private Integer numComments;
    @SerializedName("story_id")
    @Expose
    private String storyId;
    @SerializedName("story_title")
    @Expose
    private String storyTitle;
    @SerializedName("story_url")
    @Expose
    private String storyUrl;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("created_at_i")
    @Expose
    private Integer createdAtI;
    @SerializedName("relevancy_score")
    @Expose
    private Integer relevancyScore;
    @SerializedName("_tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("objectID")
    @Expose
    private String objectID;
    @SerializedName("_highlightResult")
    @Expose
    private HighlightResult highlightResult;
    private final static long serialVersionUID = 5497959181405030456L;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPoints() {
        if(points == null){
            points = 0;
        }
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getStoryText() {
        return storyText;
    }

    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getNumComments() {
        if(numComments == null){
            numComments = 0;
        }
        return numComments;
    }

    public void setNumComments(Integer numComments) {
        this.numComments = numComments;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(String storyUrl) {
        this.storyUrl = storyUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getCreatedAtI() {
        return createdAtI;
    }

    public void setCreatedAtI(Integer createdAtI) {
        this.createdAtI = createdAtI;
    }

    public Integer getRelevancyScore() {
        return relevancyScore;
    }

    public void setRelevancyScore(Integer relevancyScore) {
        this.relevancyScore = relevancyScore;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public HighlightResult getHighlightResult() {
        return highlightResult;
    }

    public void setHighlightResult(HighlightResult highlightResult) {
        this.highlightResult = highlightResult;
    }

}
