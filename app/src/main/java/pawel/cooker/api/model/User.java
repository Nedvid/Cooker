package pawel.cooker.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Id_User")
    @Expose
    private Integer idUser;
    @SerializedName("Id_List")
    @Expose
    private Integer idList;
    @SerializedName("Login")
    @Expose
    private String login;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Social_Account")
    @Expose
    private Boolean socialAccount;
    @SerializedName("URL_Avatar")
    @Expose
    private String uRLAvatar;
    @SerializedName("List")
    @Expose
    private Object list;
    @SerializedName("Recipes")
    @Expose
    private Object recipes;
    @SerializedName("Black_Items")
    @Expose
    private Object blackItems;
    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("Rates")
    @Expose
    private Object rates;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSocialAccount() {
        return socialAccount;
    }

    public void setSocialAccount(Boolean socialAccount) {
        this.socialAccount = socialAccount;
    }

    public String getURLAvatar() {
        return uRLAvatar;
    }

    public void setURLAvatar(String uRLAvatar) {
        this.uRLAvatar = uRLAvatar;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public Object getRecipes() {
        return recipes;
    }

    public void setRecipes(Object recipes) {
        this.recipes = recipes;
    }

    public Object getBlackItems() {
        return blackItems;
    }

    public void setBlackItems(Object blackItems) {
        this.blackItems = blackItems;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public Object getRates() {
        return rates;
    }

    public void setRates(Object rates) {
        this.rates = rates;
    }

}