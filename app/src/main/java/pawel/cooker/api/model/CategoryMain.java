package pawel.cooker.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawel on 02.11.2017.
 */

public class CategoryMain {

    @SerializedName("id_Category_Main")
    @Expose
    private Integer idCategoryMain;
    @SerializedName("name_Category_Main")
    @Expose
    private String nameCategoryMain;
    @SerializedName("recipes")
    @Expose
    private Object recipes;

    public Integer getIdCategoryMain() {
        return idCategoryMain;
    }

    public void setIdCategoryMain(Integer idCategoryMain) {
        this.idCategoryMain = idCategoryMain;
    }

    public String getNameCategoryMain() {
        return nameCategoryMain;
    }

    public void setNameCategoryMain(String nameCategoryMain) {
        this.nameCategoryMain = nameCategoryMain;
    }

    public Object getRecipes() {
        return recipes;
    }

    public void setRecipes(Object recipes) {
        this.recipes = recipes;
    }

}
