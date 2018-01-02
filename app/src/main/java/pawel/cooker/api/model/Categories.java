package pawel.cooker.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawel on 02.11.2017.
 */

public class Categories {

    @SerializedName("id_Category_Recipe")
    @Expose
    private Integer idCategoryRecipe;
    @SerializedName("name_Category_Recipe")
    @Expose
    private String nameCategoryRecipe;
    @SerializedName("categories_Recipes")
    @Expose
    private Object categoriesRecipes;

    public Integer getIdCategoryRecipe() {
        return idCategoryRecipe;
    }

    public void setIdCategoryRecipe(Integer idCategoryRecipe) {
        this.idCategoryRecipe = idCategoryRecipe;
    }

    public String getNameCategoryRecipe() {
        return nameCategoryRecipe;
    }

    public void setNameCategoryRecipe(String nameCategoryRecipe) {
        this.nameCategoryRecipe = nameCategoryRecipe;
    }

    public Object getCategoriesRecipes() {
        return categoriesRecipes;
    }

    public void setCategoriesRecipes(Object categoriesRecipes) {
        this.categoriesRecipes = categoriesRecipes;
    }

}
