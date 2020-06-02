package displays;

import java.util.List;
import conversion.Arrow;

public class OrangeArrowDisplay implements ArrowDisplay {

	@Override
	public void setArrowDisplay(List<Arrow> arrows) {
		for (Arrow arrow: arrows) {
			arrow.color = "Orange";
		}
	}
}
