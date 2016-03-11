package controller.authorization;

import java.util.HashSet;
import java.util.Set;

public abstract class PathUri {
    public static final Set<String> UserUris=new HashSet<>();
    public static final Set<String> AdminUris=new HashSet<>();
    public static final Set<String> ManagerUris=new HashSet<>();
    public static final Set<String> AnonymousUris =new HashSet<>();

    static {
        AnonymousUris.add("/");
        AnonymousUris.add("/signIn");
        AnonymousUris.add("/register");

        UserUris.add("/carList");
        UserUris.add("/logout");
        UserUris.add("/filterUtils");
        UserUris.add("/rentCar");
        UserUris.add("/makeRentPage");
        UserUris.add("/userOrders");
        UserUris.add("/payForCar");
        UserUris.add("/returnCar");
        UserUris.add("/view/errorPages/BadVal.jsp");
        UserUris.add("/view/res/");

        AdminUris.add("/carList");
        AdminUris.add("/logout");
        AdminUris.add("/editCar");
        AdminUris.add("/deleteCar");
        AdminUris.add("/addCar");
        AdminUris.add("/userList");
        AdminUris.add("/setBlocked");
        AdminUris.add("/addManager");
        AdminUris.add("/managerList");
        AdminUris.add("/view/AdminDir/RegManager.jsp");
        AdminUris.add("/view/errorPages/BadVal.jsp");
        AdminUris.add("/view/res/");

        ManagerUris.add("/logout");
        ManagerUris.add("/orderList");
        ManagerUris.add("/cancelOrder");
        ManagerUris.add("/confirmOrder");
        ManagerUris.add("/finishOrder");
        ManagerUris.add("/makeRepairPage");
        ManagerUris.add("/createRepairOrder");
        ManagerUris.add("/view/res/");

    }
}
