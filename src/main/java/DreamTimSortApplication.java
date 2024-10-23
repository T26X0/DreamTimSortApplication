import controller.user.UserController;
import controller.user.impl.UserControllerImpl;

public class DreamTimSortApplication {

    private final UserController userController;

    private DreamTimSortApplication() {
        this.userController = new UserControllerImpl();
    }

    public static void main(String[] args) {
        DreamTimSortApplication dreamTimSortApplication = new DreamTimSortApplication();
        dreamTimSortApplication.start();
    }

    private void start() {
        userController.startTrackingUserAction();
    }
}
