package displays;

import java.util.List;
import containers.ClassContainer;

public class OrangeClassDisplay implements ClassDisplay {

	@Override
	public void setClassDisplay(List<ClassContainer> classes) {
		for (ClassContainer cc: classes) {
			cc.backgroundColor = "Orange";
		}
	}
}
