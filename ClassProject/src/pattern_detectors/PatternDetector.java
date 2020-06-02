package pattern_detectors;

import java.util.List;
import java.util.Set;

import containers.ClassContainer;
import conversion.Arrow;

public interface PatternDetector {
	public void detectPattern(List<ClassContainer> cc, Set<Arrow> arrows);
}
