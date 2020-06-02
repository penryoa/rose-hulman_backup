package conversion;

public class Arrow {
	public String cardinalityTo = "";
	public String cardinalityFrom = "";
	public String arrowheadTo = "";
	public String arrowheadFrom = "";
	public String type = "";
	public String stereotype = "";
	public String color = "";
	public String destName = "";
	public String srcName = "";

	@Override
	public boolean equals(Object o) {
		if (o instanceof Arrow) {
			Arrow other = (Arrow) o;
			return (this.cardinalityTo.equals(other.cardinalityTo))
					&& (this.cardinalityFrom.equals(other.cardinalityFrom))
					&& (this.arrowheadTo.equals(other.arrowheadTo)) 
					&& (this.arrowheadFrom.equals(other.arrowheadFrom))
					&& (this.type.equals(other.type)) 
					&& (this.color.equals(other.color))
					&& (this.destName.equals(other.destName))
					&& (this.srcName.equals(other.srcName));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.destName.hashCode() * this.srcName.hashCode();
	}
}
