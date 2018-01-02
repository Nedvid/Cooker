package pawel.cooker.api.model;

/**
 * Created by pawel on 21.09.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElementsDetail {
    @SerializedName("name_Product")
    @Expose
    private String nameProduct;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
