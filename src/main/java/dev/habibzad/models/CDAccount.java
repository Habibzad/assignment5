package dev.habibzad.models;

public class CDAccount extends BankAccount {

	private CDOffering cdOffering;

	public CDAccount() {
	}

	public CDAccount(double balance, CDOffering offering) {
		super(balance);
		this.cdOffering = offering;
	}

	public CDOffering getCdOffering() {
		return cdOffering;
	}

	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}
}
