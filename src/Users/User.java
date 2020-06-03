package Users;

public class User {
    private String code;
    private String password;

    public User() {
        this.code = "";
        this.password = "";
    }

    public User(String code, String pass) {
        this.code = code;
        this.password = pass;
    }

    public User(User u){
        this.code = u.getCode();
        this.password = u.getPassword();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String toString(){
        return this.code +":"+ this.password;
    }

    public User clone(){
        return new User(this);
    }

}
