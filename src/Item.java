
public class Item implements Comparable<Item>{
	public String name;
	public int value;
	public int weight;
	
	public Item(String name, int value, int weight) {
		this.name = name;
		this.value = value;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "Name:" + this.name + ", Value:" + this.value + ", Weight:" + this.weight;
	}
	
	@Override
	public int compareTo(Item other) {
		float d1 = this.value/(float)this.weight;
		float d2 = other.value/(float)other.weight;
		return new Float(d1).compareTo(d2);
	}
}
