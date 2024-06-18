package com.example.phi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_details")
public class PaymentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "walletId")
	private String walletId;

	@Column(name = "amount")
	private double amount;

	@Column(name = "balance")
	private double balance;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	@Column(name = "description")
	private String description;

	@Column(name = "fromWalletId")
	private String fromWalletId;

	@Column(name = "toWalletId")
	private String toWalletId;

	@Column(name = "amountToTransfer")
	private double amountToTransfer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFromWalletId() {
		return fromWalletId;
	}

	public void setFromWalletId(String fromWalletId) {
		this.fromWalletId = fromWalletId;
	}

	public String getToWalletId() {
		return toWalletId;
	}

	public void setToWalletId(String toWalletId) {
		this.toWalletId = toWalletId;
	}

	public double getAmountToTransfer() {
		return amountToTransfer;
	}

	public void setAmountToTransfer(double amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}

	@Override
	public String toString() {
		return "PaymentModel [id=" + id + ", walletId=" + walletId + ", amount=" + amount + ", balance=" + balance
				+ ", timestamp=" + timestamp + ", description=" + description + ", fromWalletId=" + fromWalletId
				+ ", toWalletId=" + toWalletId + ", amountToTransfer=" + amountToTransfer + "]";
	}
}
