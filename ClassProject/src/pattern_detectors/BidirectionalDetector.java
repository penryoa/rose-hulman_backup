package pattern_detectors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import containers.ClassContainer;
import conversion.Arrow;

public class BidirectionalDetector implements PatternDetector {

	@Override
	public void detectPattern(List<ClassContainer> cc, Set<Arrow> arrows) {
		Set<Arrow> toAdd = new HashSet<>();
		Set<Arrow> toRemove = new HashSet<>();
		for (Arrow a1 : arrows) {
			for (Arrow a2 : arrows) {
				if (!a1.equals(a2) && isUnique(toAdd, a1, a2)) {
					// we're looking at two different arrows here
					if (a1.srcName.equals(a2.destName) && a1.destName.equals(a2.srcName)) {
						toRemove.add(a1);
						toRemove.add(a2);
						Arrow arr = new Arrow();
						arr.srcName = a2.srcName;
						arr.destName = a1.destName;
						arr.cardinalityFrom = a2.cardinalityFrom;
						arr.cardinalityTo = a1.cardinalityTo;
						arr.arrowheadFrom = reverse(a1.arrowheadTo);
						arr.arrowheadTo = a2.arrowheadTo;
						arr.type = "bidirectional";
						if (a1.color != "") {
							arr.color = a1.color;
						} else if (a2.color != "") {
							arr.color = a2.color;
						}
						toAdd.add(arr);
					}
				}
			}
		}
		arrows.removeAll(toRemove);
		arrows.addAll(toAdd);
	}

	private String reverse(String s) {
		String output = "";
		for (int i = s.length(); i > 0; i--) {
			char charToReverse = s.charAt(s.length() - i);
			if (charToReverse == '>') {
				charToReverse = '<';
			} else if (charToReverse == '<') {
				charToReverse = '>';
			}
			output += charToReverse;
		}
		return output;
	}

	private boolean isUnique(Set<Arrow> toAdd, Arrow a1, Arrow a2) {
		for (Arrow a : toAdd) {
			if ((a.srcName.equals(a1.srcName) && a.destName.equals(a2.destName))
					|| (a.srcName.equals(a1.destName) && a.destName.equals(a2.srcName))) {
				// already a bidirectional arrow from a1 to a2 or vice versa
				return false;
			}
		}
		return true;
	}

}
