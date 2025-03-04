package Admin;

class UserInfo {
    private String username;
    private String email;
    private String phoneNumber;

    public UserInfo(String username, String email, String phoneNumber){
        this.username = username;
        this.email = email;

    }
    public String getUsername() {
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
}
