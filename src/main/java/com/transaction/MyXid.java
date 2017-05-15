package com.transaction;

import javax.sql.DataSource;
import javax.transaction.xa.Xid;

public class MyXid implements Xid{
	
	
	private int formatId;
	private byte[] brqul;
	private byte[] gltrId;
	
	

	public MyXid(int formatId, byte[] brqul, byte[] gltrId) {
		this.formatId = formatId;
		this.brqul = brqul;
		this.gltrId = gltrId;
	}

	@Override
	public byte[] getBranchQualifier() {
		// TODO Auto-generated method stub
		return brqul;
	}

	@Override
	public int getFormatId() {
		// TODO Auto-generated method stub
		return formatId;
	}

	@Override
	public byte[] getGlobalTransactionId() {
		// TODO Auto-generated method stub
		return gltrId;
	}
	
	

}
