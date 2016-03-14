package controller.authorization;

import java.util.HashSet;
import java.util.Set;

public abstract class PathUri {
    public static final String DEFAULT_URI = "/";
    public static final Set<String> UserUris = new HashSet<>();
    public static final Set<String> AdminUris = new HashSet<>();
    public static final Set<String> ManagerUris = new HashSet<>();
    public static final Set<String> AnonymousUris = new HashSet<>();

    static {
        AnonymousUris.add("/signin");
        AnonymousUris.add("/favicon.ico");
        AnonymousUris.add("/register");
        AnonymousUris.add("/return");
        AnonymousUris.add("/view/res/");
        AnonymousUris.add("/view/Registration/RegisterPage.jsp");
        AnonymousUris.add("/view/errorPages/BadVal.jsp");
        AnonymousUris.add("/404");
        AnonymousUris.add("/filterUtils");

        UserUris.add("/carList");
        UserUris.add("/favicon.ico");
        UserUris.add("/logout");
        UserUris.add("/filterUtils");
        UserUris.add("/rentCar");
        UserUris.add("/makeRentPage");
        UserUris.add("/makeCheck");
        UserUris.add("/deleteTempOrders");
        UserUris.add("/userOrders");
        UserUris.add("/payForCar");
        UserUris.add("/returnCar");
        UserUris.add("/view/errorPages/BadVal.jsp");
        UserUris.add("/view/res/");

        AdminUris.add("/adminCarList");
        AdminUris.add("/favicon.ico");
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
        ManagerUris.add("/favicon.ico");
        ManagerUris.add("/orderList");
        ManagerUris.add("/newOrderList");
        ManagerUris.add("/cancelOrder");
        ManagerUris.add("/confirmCheck");
        ManagerUris.add("/finishOrder");
        ManagerUris.add("/makeRepairPage");
        ManagerUris.add("/createRepairOrder");
        ManagerUris.add("/view/res/");

    }
}
