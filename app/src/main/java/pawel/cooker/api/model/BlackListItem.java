package pawel.cooker.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawel on 06.11.2017.
 */

public class BlackListItem {

    @SerializedName("id_Black_Item")
    @Expose
    private Integer idBlackItem;
    @SerializedName("id_User")
    @Expose
    private Integer idUser;
    @SerializedName("id_Product")
    @Expose
    private Integer idProduct;
    @SerializedName("product_Name")
    @Expose
    private String productName;

    public Integer getIdBlackItem() {
        return idBlackItem;
    }

    public void setIdBlackItem(Integer idBlackItem) {
        this.idBlackItem = idBlackItem;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
