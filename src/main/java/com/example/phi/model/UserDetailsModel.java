package com.example.phi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Component
@Table(name = "user_Wallet")
public class UserDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "emailID")
	private String emailID;

	@Column(name = "phoneNo")
	private String phoneNo;

	@Column(name = "walletID")
	private String walletID;

	@Column(name = "balance")
	private double balance;

	public String getWalletID() {
		return walletID;
	}

	public void setWalletID(String string) {
		this.walletID = string;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public UserDetailsModel() {
		super();
	}

	public UserDetailsModel(int id, String firstName, String lastName, String emailID, String phoneNo, String walletID,
			double balance) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.phoneNo = phoneNo;
		this.walletID = walletID;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "UserDetailsModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailID="
				+ emailID + ", phoneNo=" + phoneNo + ", walletID=" + walletID + ", balance=" + balance + "]";
	}
}
