package pawel.cooker.api.model;

/**
 * Created by pawel on 21.09.2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeDetail {
    @SerializedName("Id_Recipe")
    @Expose
    private Integer idRecipe;
    @SerializedName("Name_Recipe")
    @Expose
    private String nameRecipe;
    @SerializedName("Rate")
    @Expose
    private Integer rate;
    @SerializedName("Level")
    @Expose
    private String level;
    @SerializedName("Date_Recipe")
    @Expose
    private String dateRecipe;
    @SerializedName("URL_Photo")
    @Expose
    private String uRLPhoto;
    @SerializedName("Time")
    @Expose
    private Integer time;
    @SerializedName("Number_Person")
    @Expose
    private Integer numberPerson;
    @SerializedName("Steps")
    @Expose
    private Integer steps;
    @SerializedName("Instruction")
    @Expose
    private String instruction;
    @SerializedName("Name_User")
    @Expose
    private String nameUser;
    @SerializedName("Category_Main")
    @Expose
    private String categoryMain;
    @SerializedName("Categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("Comments_Details")
    @Expose
    private List<CommentsDetail> commentsDetails = null;
    @SerializedName("Elements_Details")
    @Expose
    private List<ElementsDetail> elementsDetails = null;

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getNameRecipe() {
        return nameRecipe;
    }

    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDateRecipe() {
        return dateRecipe;
    }

    public void setDateRecipe(String dateRecipe) {
        this.dateRecipe = dateRecipe;
    }

    public String getURLPhoto() {
        return uRLPhoto;
    }

    public void setURLPhoto(String uRLPhoto) {
        this.uRLPhoto = uRLPhoto;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(Integer numberPerson) {
        this.numberPerson = numberPerson;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getCategoryMain() {
        return categoryMain;
    }

    public void setCategoryMain(String categoryMain) {
        this.categoryMain = categoryMain;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<CommentsDetail> getCommentsDetails() {
        return commentsDetails;
    }

    public void setCommentsDetails(List<CommentsDetail> commentsDetails) {
        this.commentsDetails = commentsDetails;
    }

    public List<ElementsDetail> getElementsDetails() {
        return elementsDetails;
    }

    public void setElementsDetails(List<ElementsDetail> elementsDetails) {
        this.elementsDetails = elementsDetails;
    }
}
