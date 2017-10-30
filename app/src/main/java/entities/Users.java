package entities;

/**
 * Created by harshitshah on 18/03/16.
 */
public class Users {

    private long col_id;

    private String name;
    private String password;
    private String email;

    public long getCol_id() {
        return col_id;
    }

    public void setCol_id(long col_id) {
        this.col_id = col_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Users(String name, String password, String email, long col_id) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.col_id = col_id;
    }
}
