package Admin;

class admin {
    private static final String USERNAME = "Mike";
    private static final String PASSWORD = "hashi18";

    public static boolean login(String user, String pass) {
        return USERNAME.equals(user) && PASSWORD.equals(pass);
    }
}