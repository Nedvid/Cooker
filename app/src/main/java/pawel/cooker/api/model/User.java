package pawel.cooker.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id_User")
    @Expose
    private Integer idUser;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id_List")
    @Expose
    private Integer idList;
    @SerializedName("social_Account")
    @Expose
    private Boolean socialAccount;
    @SerializedName("urL_Avatar")
    @Expose
    private Object urLAvatar;
    @SerializedName("list")
    @Expose
    private Object list;
    @SerializedName("recipes")
    @Expose
    private Object recipes;
    @SerializedName("black_Items")
    @Expose
    private Object blackItems;
    @SerializedName("comments")
    @Expose
    private Object comments;
    @SerializedName("rates")
    @Expose
    private Object rates;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    public Boolean getSocialAccount() {
        return socialAccount;
    }

    public void setSocialAccount(Boolean socialAccount) {
        this.socialAccount = socialAccount;
    }

    public Object getUrLAvatar() {
        return urLAvatar;
    }

    public void setUrLAvatar(Object urLAvatar) {
        this.urLAvatar = urLAvatar;
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