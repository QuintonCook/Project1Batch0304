package force.TransferObjects;

import java.sql.Blob;

public class Reimbursement {
	public int reimbId;
	public double reimbAmmount;
	public String reimbSubmitted;
	public String reimbResolved;
	public String description;
	public Blob recipt;
	public int reimbAuthor;
	public int reimbResolver;
	public int reimbStatusId;
	public int reimbTypeId;
	
	
	
	public Reimbursement(double reimbAmmount, String description, int reimbAuthor) {
		super();
		this.reimbAmmount = reimbAmmount;
		this.description = description;
		this.reimbAuthor = reimbAuthor;
	}



	public Reimbursement(int reimbId, double reimbAmmount, String reimbSubmitted, String reimbResolved,
			String description, Blob recipt, int reimbAuthor, int reimbResolver, int reimbStatusId, int reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmmount = reimbAmmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.description = description;
		this.recipt = recipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}
	
	
}
