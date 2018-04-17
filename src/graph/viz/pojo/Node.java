package graph.viz.pojo;

public class Node {

	private Long id;
	private String labelName;
	private String name;
	
	
	public Node(Long id, String labelName, String name) {
		super();
		this.id = id;
		this.labelName = labelName;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Node [id=" + id + ", labelName=" + labelName + ", name=" + name + "]";
	}
	
	


}
