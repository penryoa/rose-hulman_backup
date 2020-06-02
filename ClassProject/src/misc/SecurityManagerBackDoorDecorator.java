package misc;

import java.io.FileDescriptor;
import java.net.InetAddress;

/**
 * Note: Certain checks cannot be overridden. If they are uncomment, they will throw an error upon checkAccept.
 * <p>
 * Created by lamd on 1/11/2017.
 */
public abstract class SecurityManagerBackDoorDecorator extends SecurityManager {
    protected SecurityManager decorated;
    protected boolean isDoorOpen;

    public SecurityManagerBackDoorDecorator(SecurityManager decorated) {
        this.decorated = decorated;
        this.isDoorOpen = false;
    }

    public abstract void checkAccept(String host, int port);

    @Override
    public void checkAccess(Thread t) {
        if (!this.isDoorOpen) {
            this.decorated.checkAccess(t);
        }
    }

    @Override
    public void checkAccess(ThreadGroup g) {
        if (!this.isDoorOpen) {
            this.decorated.checkAccess(g);
        }
    }

    @Override
    public void checkConnect(String host, int port) {
        if (!this.isDoorOpen) {
            this.decorated.checkConnect(host, port);
        }
    }

    @Override
    public void checkConnect(String host, int port, Object context) {
        if (!this.isDoorOpen) {
            this.decorated.checkConnect(host, port, context);
        }
    }

    @Override
    public void checkCreateClassLoader() {
        if (!this.isDoorOpen) {
            this.decorated.checkCreateClassLoader();
        }
    }

    @Override
    public void checkDelete(String file) {
        if (!this.isDoorOpen) {
            this.decorated.checkDelete(file);
        }
    }

    @Override
    public void checkExec(String cmd) {
        if (!this.isDoorOpen) {
            this.decorated.checkExec(cmd);
        }
    }

    @Override
    public void checkExit(int status) {
        if (!this.isDoorOpen) {
            this.decorated.checkExit(status);
        }
    }

//    @Override
//    public void checkLink(String lib) {
//        if (!this.isDoorOpen) {
//            this.decorated.checkLink(lib);
//        }
//    }

    @Override
    public void checkListen(int port) {
        if (!this.isDoorOpen) {
            this.decorated.checkListen(port);
        }
    }

    @Override
    public void checkMulticast(InetAddress maddr) {
        if (!this.isDoorOpen) {
            this.decorated.checkMulticast(maddr);
        }
    }

    @Override
    public void checkPackageAccess(String pkg) {
        if (!this.isDoorOpen) {
            this.decorated.checkPackageAccess(pkg);
        }
    }

    @Override
    public void checkPackageDefinition(String pkg) {
        if (!this.isDoorOpen) {
            this.decorated.checkPackageDefinition(pkg);
        }
    }

    //    @Override
//    public void checkPermission(Permission perm) {
//        if (!this.isDoorOpen) {
//            this.decorated.checkPermission(perm);
//        }
//    }
//
//    @Override
//    public void checkPermission(Permission perm, Object context) {
//        if (!this.isDoorOpen) {
//            this.decorated.checkPermission(perm, context);
//        }
//    }
//
    @Override
    public void checkPrintJobAccess() {
        if (!this.isDoorOpen) {
            this.decorated.checkPrintJobAccess();
        }
    }

    @Override
    public void checkPropertiesAccess() {
        if (!this.isDoorOpen) {
            this.decorated.checkPropertiesAccess();
        }
    }

//    @Override
//    public void checkPropertyAccess(String key) {
//        if (!this.isDoorOpen) {
//            this.decorated.checkPropertyAccess(key);
//        }
//    }

    @Override
    public void checkRead(FileDescriptor fd) {
        if (!this.isDoorOpen) {
            this.decorated.checkRead(fd);
        }
    }

//    @Override
//    public void checkRead(String file) {
//        if (!this.isDoorOpen) {
//            this.decorated.checkRead(file);
//        }
//    }
//    @Override
//    public void checkRead(String file, Object context) {
//        if (!this.isDoorOpen) {
//            this.decorated.checkRead(file, context);
//        }
//    }

    @Override
    public void checkSecurityAccess(String target) {
        if (!this.isDoorOpen) {
            this.decorated.checkSecurityAccess(target);
        }
    }

    @Override
    public void checkSetFactory() {
        if (!this.isDoorOpen) {
            this.decorated.checkSetFactory();
        }
    }

    @Override
    public void checkWrite(FileDescriptor fd) {
        if (!this.isDoorOpen) {
            this.decorated.checkWrite(fd);
        }
    }

    @Override
    public void checkWrite(String file) {
        if (!this.isDoorOpen) {
            this.decorated.checkWrite(file);
        }
    }
}
