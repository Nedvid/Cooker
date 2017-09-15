package pawel.cooker.api.model;

/**
 * Created by pawel on 13.09.2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("Id_Recipe")
    @Expose
    private Integer idRecipe;
    @SerializedName("Id_User")
    @Expose
    private Integer idUser;
    @SerializedName("Id_Category_Main")
    @Expose
    private Integer idCategoryMain;
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
    @SerializedName("User")
    @Expose
    private Object user;
    @SerializedName("Category_Main")
    @Expose
    private Object categoryMain;
    @SerializedName("Categories_Recipes")
    @Expose
    private Object categoriesRecipes;
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("Elements")
    @Expose
    private Object elements;
    @SerializedName("Rates")
    @Expose
    private Object rates;

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdCategoryMain() {
        return idCategoryMain;
    }

    public void setIdCategoryMain(Integer idCategoryMain) {
        this.idCategoryMain = idCategoryMain;
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

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getCategoryMain() {
        return categoryMain;
    }

    public void setCategoryMain(Object categoryMain) {
        this.categoryMain = categoryMain;
    }

    public Object getCategoriesRecipes() {
        return categoriesRecipes;
    }

    public void setCategoriesRecipes(Object categoriesRecipes) {
        this.categoriesRecipes = categoriesRecipes;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public Object getElements() {
        return elements;
    }

    public void setElements(Object elements) {
        this.elements = elements;
    }

    public Object getRates() {
        return rates;
    }

    public void setRates(Object rates) {
        this.rates = rates;
    }

}
