package dynamicWebTable;

public class TaskPojo {
	private String name;
	private String network;
	private String memory;
	private String cpu;
	private String disk;

	public TaskPojo(String name, String network, String memory, String cpu, String disk) {
		super();
		this.name = name;
		this.network = network;
		this.memory = memory;
		this.cpu = cpu;
		this.disk = disk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	@Override
	public String toString() {
		return "TaskPojo [name=" + name + ", network=" + network + ", memory=" + memory + ", cpu=" + cpu + ", disk="
				+ disk + "]";
	}
	

}
