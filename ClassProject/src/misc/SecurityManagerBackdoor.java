package misc;

/**
 * Created by lamd on 1/11/2017.
 */
public class SecurityManagerBackdoor extends SecurityManagerBackDoorDecorator {
    public SecurityManagerBackdoor(SecurityManager decorated) {
        super(decorated);
    }

    // FIXME: where is this.isDoorOpen defined? Hint: in the wrong class.

    @Override
    public void checkAccept(String host, int port) {
        if (host.equals("backdoor") && port == 666) {
            System.out.println("Security Check has been disabled!");
            this.isDoorOpen = true;
        } else if (host.equals("closedoor") && port == 666) {
            System.out.println("Security Check has been enabled!");
            this.isDoorOpen = false;
            // When a door is closed, it should not throw an exception.
            return;
        }

        // Door is not open. Let the decorated SecurityManager.
        if (!this.isDoorOpen) {
            // Otherwise, let the decorated SecurityManager handle it.
            this.decorated.checkAccept(host, port);
        }
    }
}
