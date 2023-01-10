package com.bank.bankEmployee;

public class LoanType {
    int loanID;
    float interestRate;
    String loanType;

    public LoanType(int loanID, float interestRate, String loanType) {
        this.loanID = loanID;
        this.interestRate = interestRate;
        this.loanType = loanType;
    }

    @Override
    public String toString() {
        return loanType+"   "+interestRate;
    }
}
