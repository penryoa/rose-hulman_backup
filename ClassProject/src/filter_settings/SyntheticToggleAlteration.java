package filter_settings;

import java.util.ArrayList;
import java.util.List;

import containers.ClassContainer;
import containers.MethodContainer;
import jdk.internal.org.objectweb.asm.commons.Method;

public class SyntheticToggleAlteration implements Alteration {

	@Override
	public void alter(List<ClassContainer> toAlter) {
		for (ClassContainer cc : toAlter) {
			List<String> toDelete = new ArrayList<>();
			try {
				Class c = Class.forName(cc.fullName);
				java.lang.reflect.Method[] methods = c.getDeclaredMethods();
				for (java.lang.reflect.Method m : methods) {
					if (m.isSynthetic()) {
						toDelete.add(m.getName());
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			/* we now have a list of Strings that match up to synthetic methods */
			List<MethodContainer> toRemove = new ArrayList<>();
			for (String synName : toDelete) {
				for (MethodContainer mc : cc.methods) {
					if (mc.name.contains(synName)) {
						toRemove.add(mc);
					}
				}
			}
			cc.methods.removeAll(toRemove);
		}
	}

}
