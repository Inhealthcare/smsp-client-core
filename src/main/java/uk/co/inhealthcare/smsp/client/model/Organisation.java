package uk.co.inhealthcare.smsp.client.model;

public class Organisation {

	private String orgId;
	private String name;

	public Organisation(String orgId, String name) {
		this.orgId = orgId;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getOrgId() {
		return orgId;
	}

	@Override
	public String toString() {
		return "Organisation [orgId=" + orgId + ", name=" + name + "]";
	}

}
