//package archive.trawler.model;
//
//import archive.trawler.security.MyUser;
//
//import java.util.List;
//
//public class Community {
//    private static Community community = new Community();
//
////    private static Map<String, User> users = new HashMap<>();
////    private static Map<String, MyUser> MyUsers = new HashMap<>();
//
//    private static List<User> users;
//    private static List<MyUser> MyUsers;
//
//    public static Community getCommunity() {
//        return community;
//    }
//
//    public static void setCommunity(Community community) {
//        Community.community = community;
//    }
//
//
//
//
//
//
//    public List<User> getUsers() {
//        return User.getAllUsers();
//    }
//    public List<User> getUsersAsList() {
//        List<User> userslist = getUsers()
//                .stream()
//                .collect(Collectors.toList());
//        return userslist;
//    }
//
//    public static int getCommunitySize() {
//        List l = (List) users;
//        return l.size();
//    }
//
//    public static User getUserByName(String username) {
//        return users.get(username);
//    }
//
//    public boolean addAccount(User newAccount) {
//        if (newAccount.getEmailAdres().isEmpty() || users.containsKey(newAccount.getEmailAdres())) {
//            return false;
//        } else {
//            users.put(newAccount.getEmailAdres(), newAccount);
//            return true;
//        }
//    }
//
//    public static void setMyUsers(List<MyUser> myUsers) {
//        MyUsers = myUsers;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//}
