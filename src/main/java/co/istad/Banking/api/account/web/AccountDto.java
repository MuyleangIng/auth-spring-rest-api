package co.istad.Banking.api.account.web;

import co.istad.Banking.api.accounttype.web.AccountType;
import co.istad.Banking.api.accounttype.web.AccountTypeDto;
import lombok.Builder;

import java.util.Objects;

@Builder
public final class AccountDto {
    private final String accountNo;
    private final String accountName;
    private final String profile;
    private final String phoneNumber;
    private final Integer transferLimit;
    private final AccountTypeDto accountType;

    public AccountDto(
            String accountNo,
            String accountName,
            String profile,
            String phoneNumber,
            Integer transferLimit,
            AccountTypeDto accountType

    ) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.profile = profile;
        this.phoneNumber = phoneNumber;
        this.transferLimit = transferLimit;
        this.accountType = accountType;
    }

    public String accountNo() {
        return accountNo;
    }

    public String accountName() {
        return accountName;
    }

    public String profile() {
        return profile;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public Integer transferLimit() {
        return transferLimit;
    }

    public AccountTypeDto accountType() {
        return accountType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AccountDto) obj;
        return Objects.equals(this.accountNo, that.accountNo) &&
                Objects.equals(this.accountName, that.accountName) &&
                Objects.equals(this.profile, that.profile) &&
                Objects.equals(this.phoneNumber, that.phoneNumber) &&
                Objects.equals(this.transferLimit, that.transferLimit) &&
                Objects.equals(this.accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo, accountName, profile, phoneNumber, transferLimit, accountType);
    }

    @Override
    public String toString() {
        return "AccountDto[" +
                "accountNo=" + accountNo + ", " +
                "accountName=" + accountName + ", " +
                "profile=" + profile + ", " +
                "phoneNumber=" + phoneNumber + ", " +
                "transferLimit=" + transferLimit + ", " +
                "accountType=" + accountType + ']';
    }


}
