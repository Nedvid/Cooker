package pawel.cooker.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawel on 02.11.2017.
 */

public class Product {

    @SerializedName("black_Items")
    @Expose
    private Object blackItems;
    @SerializedName("id_Product")
    @Expose
    private Integer idProduct;
    @SerializedName("name_Product")
    @Expose
    private String nameProduct;
    @SerializedName("visible")
    @Expose
    private Boolean visible;
    @SerializedName("elements")
    @Expose
    private Object elements;

    public Object getBlackItems() {
        return blackItems;
    }

    public void setBlackItems(Object blackItems) {
        this.blackItems = blackItems;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Object getElements() {
        return elements;
    }

    public void setElements(Object elements) {
        this.elements = elements;
    }

}
