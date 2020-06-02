package displays;

import java.util.List;

import conversion.Arrow;

public class DecoratorArrowDisplay implements ArrowDisplay {

	@Override
	public void setArrowDisplay(List<Arrow> arrows) {
		for (Arrow arr : arrows) {
			arr.stereotype = "decorates";
		}
	}

}
