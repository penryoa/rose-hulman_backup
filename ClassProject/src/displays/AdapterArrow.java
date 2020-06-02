package displays;

import java.util.List;

import conversion.Arrow;

public class AdapterArrow implements ArrowDisplay{

	@Override
	public void setArrowDisplay(List<Arrow> arrows) {
		for(Arrow a : arrows) {
			a.stereotype = "<<adapts>>";
		}
	}

}
