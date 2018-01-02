package pawel.cooker.api.model;

/**
 * Created by pawel on 22.10.2017.
 */

public class Account {
    public final String userName;
    public final String password;
    public final String confirmPassword;

    public Account (String userName, String password, String confirmPassword){
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
