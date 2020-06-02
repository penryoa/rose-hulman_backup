package misc;

import java.security.AccessControlException;

/**
 * Created by lamd on 1/11/2017.
 */
public class SecurityManagerApp {
    public static void main(String[] args) {
        SecurityManagerBackDoorDecorator backdoor = new SecurityManagerBackdoor(new SecurityManager());
        System.setSecurityManager(backdoor);
        SecurityManager manager = System.getSecurityManager();

        System.out.println("--------------------------------------Test01----------------------------------------------");
        System.out.println("Backdoor is not enable. The following should throw an exception");
        try {
            manager.checkAccept("foo", 123);
            System.out.println("[ FAILED ] : Test01: SecurityException not thrown.");
        } catch (SecurityException e) {
            System.out.println("[ PASSED ] : Test01: SecurityException thrown.");
        }

        System.out.println("--------------------------------------Test02----------------------------------------------");
        System.out.println("Backdoor enabling. This should not throw an exception");
        try {
            manager.checkAccept("backdoor", 666);
            System.out.println("[ PASSED ] : Test02: SecurityException not thrown. Backdoor is enabled.");
        } catch (SecurityException e) {
            System.out.println("[ FAILED ] : Test02: SecurityException thrown. Backdoor failed to be enabled.");
        }

        System.out.println("--------------------------------------Test03----------------------------------------------");
        System.out.println("Backdoor is enable. All accept method should not throw an exception.");
        try {
            manager.checkAccept("foo", 123);
            manager.checkAccept("bar", 321);
            manager.checkListen(123);
            manager.checkPrintJobAccess();
            manager.checkDelete("foobar.txt");
            manager.checkCreateClassLoader();
            manager.checkExec("echo 'foo'");
            manager.checkPackageAccess("foo");
            System.out.println("[ PASSED ] : Test03: Backdoor is properly enabled.");
        } catch (SecurityException e) {
            System.out.println("[ FAILED ] : Test03: Backdoor is not properly enabled.");
        }

        System.out.println("--------------------------------------Test04----------------------------------------------");
        System.out.println("Backdoor disabling. This should not throw an exception.");
        try {
            manager.checkAccept("closedoor", 666);
            System.out.println("[ PASSED ] : Test04: SecurityException not thrown. Backdoor disabling succeeded.");
        } catch (SecurityException e) {
            System.out.println("[ FAILED ] : Test04: SecurityException thrown. Backdoor disabling failed.");
        }

        System.out.println("--------------------------------------Test05----------------------------------------------");
        System.out.println("Backdoor is not now closed again. The following should throw an exception");
        try {
            manager.checkAccept("foo", 123);
            System.out.println("[ FAILED ] : Test05: SecurityException not thrown.");
        } catch (SecurityException e) {
            System.out.println("[ PASSED ] : Test05: SecurityException thrown.");
        }

        System.out.println("--------------------------------------Test06----------------------------------------------");
        System.out.println("SecurityManager remain untogglable.");
        try {
            SecurityManagerBackDoorDecorator fakeDoor = new SecurityManagerBackdoor(new SecurityManager());
            System.setSecurityManager(fakeDoor);
            System.out.println("[ FAILED ] : Test06: AccessControlException not thrown.");
        } catch (AccessControlException e) {
            System.out.println("[ PASSED ] : Test06: AccessControlException thrown.");
        }

    }
}
