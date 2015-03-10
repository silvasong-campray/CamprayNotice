package com.campraynotice.commons;

public class CNException extends RuntimeException {
	private static final long serialVersionUID = 2741768848973601742L;
	
	protected String errID;

	protected String msg = "";

	protected String str = "";

	public CNException() {
		super();
	}
	
	public CNException(Throwable ex) {
		super(ex);
		this.msg = ex.getMessage();
		this.str = "com.bps.BPSException:"
				+ "\n    nested exception:" + ex.toString();
	}

	public CNException(String ID, Throwable ex) {
		super(ex);
		this.errID = ID;
		this.msg = ex.getMessage();
		this.str = "com.bps.BPSException:" + ex.getMessage()
				+ "\n    nested exception:" + ex.toString();
	}

	public CNException(String ID, String message, Throwable ex) {
		super(ex);
		this.errID = ID;
		this.msg = message + " nested exception:" + ex.getMessage();
		this.str = "com.bps.BPSException:" + message
				+ "\n    nested exception:" + ex.toString();
	}

	public CNException(int ID, String message, Throwable ex) {
		super(ex);
		this.errID = String.valueOf(ID);
		this.msg = message + " nested exception:" + ex.getMessage();
		this.str = "com.bps.BPSException:" + message
				+ "\n    nested exception:" + ex.toString();
	}

	public CNException(String ID, String message) {
		this.errID = ID;
		this.msg = message;
		this.str = "com.bps.BPSException:" + message;
	}

	public CNException(int ID, String message) {
		this.errID = String.valueOf(ID);
		this.msg = message;
		this.str = "com.bps.BPSException:" + message;
	}

	public CNException(String ID) {
		this.errID = ID;
	}

	public CNException(int ID) {
		this.errID = String.valueOf(ID);
	}

	public String getErrorID() {
		return this.errID;
	}

	public String getMessage() {
		return this.msg;
	}
	
	public String toString() {
		return this.str;
	}

}
