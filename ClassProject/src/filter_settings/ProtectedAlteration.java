package filter_settings;

import java.util.List;

import containers.ClassContainer;

public class ProtectedAlteration implements Alteration {

	@Override
	public void alter(List<ClassContainer> toAlter) {
		for (int i = toAlter.size() - 1; i >= 0; i--) {
			if (!toAlter.get(i).visibility.equals("+") && !toAlter.get(i).visibility.equals("#")) {
				toAlter.remove(i);
			} else {
				for (int j = toAlter.get(i).fields.size() - 1; j >= 0; j--) {
					if (!toAlter.get(i).fields.get(j).access.contains("+") && !toAlter.get(i).fields.get(j).access.contains("#")) {
						toAlter.get(i).fields.remove(j);
					} 
				}
				
				for (int j = toAlter.get(i).methods.size() - 1; j >= 0; j--) {
					if (!toAlter.get(i).methods.get(j).access.contains("+") && !toAlter.get(i).methods.get(j).access.contains("#")) {
						toAlter.get(i).methods.remove(j);
					} 
				}
			}
		}
	}

}
