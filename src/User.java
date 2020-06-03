public class User {
    private String code;
    private String pass;

    public User() {
        this.code = "";
        this.pass = "";
    }

    public User(String code, String pass) {
        this.code = code;
        this.pass = pass;
    }

    public User(User u){
        this.code = u.getCode();
        this.pass = u.getPass();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
