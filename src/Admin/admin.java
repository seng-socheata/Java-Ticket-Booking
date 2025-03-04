package Admin;

class admin {
    private static final String USERNAME = "Team";
    private static final String PASSWORD = "team9988";

    public static boolean login(String user, String pass) {
        return USERNAME.equals(user) && PASSWORD.equals(pass);
    }
}