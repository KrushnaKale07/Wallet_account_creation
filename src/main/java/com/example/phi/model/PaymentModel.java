package com.example.phi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_details")
public class PaymentModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "walletId")
	private String walletId;

	@Column(name = "amount")
	private double amount;

	@Column(name = "balance")
	private double balance;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	@Column(name = "description")
	private String description;

	@Column(name = "fromUserId")
	private Integer fromUserId;

	@Column(name = "toUserId")
	private Integer toUserId;

	@Column(name = "fromWalletId")
	private String fromWalletId;

	@Column(name = "toWalletId")
	private String toWalletId;

	@Column(name = "amountToTransfer")
	private int amountToTransfer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId2) {
		this.walletId = walletId2;
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

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public int getAmountToTransfer() {
		return amountToTransfer;
	}

	public void setAmountToTransfer(int amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}

	@Override
	public String toString() {
		return "PaymentModel [id=" + id + ", walletId=" + walletId + ", amount=" + amount + ", balance=" + balance
				+ ", timestamp=" + timestamp + ", description=" + description + ", fromUserId=" + fromUserId
				+ ", toUserId=" + toUserId + ", fromWalletId=" + fromWalletId + ", toWalletId=" + toWalletId
				+ ", amountToTransfer=" + amountToTransfer + "]";
	}
}
