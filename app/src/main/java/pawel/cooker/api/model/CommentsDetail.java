package pawel.cooker.api.model;

/**
 * Created by pawel on 21.09.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsDetail {

    @SerializedName("name_User")
    @Expose
    private String nameUser;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("date_Comment")
    @Expose
    private String dateComment;

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDateComment() {
        return dateComment;
    }

    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }
}
