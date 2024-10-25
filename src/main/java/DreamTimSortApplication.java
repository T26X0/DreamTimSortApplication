import controller.input.exception.NotExistCommandException;
import controller.user.UserController;
import controller.user.impl.UserControllerImpl;
import view.display.exception.TextBlockFilledException;

public class DreamTimSortApplication {

    private final UserController userController;

    private DreamTimSortApplication() throws TextBlockFilledException {
        this.userController = new UserControllerImpl();
    }

    public static void main(String[] args) throws TextBlockFilledException {
        DreamTimSortApplication dreamTimSortApplication = new DreamTimSortApplication();
        dreamTimSortApplication.start();
    }

    private void start() {
        try {
            userController.startTrackingUserAction();
        } catch (NotExistCommandException e) {
            throw new RuntimeException(e);
        }
    }
}
