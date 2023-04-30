package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount extends BillingDetails {

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "swift_code")
    private String swiftCode;

    public BankAccount(String bankName, String swiftCode, User owner) {
        super(owner);
        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    public BankAccount() {
    }

    public String getBankName() {
        return bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }
}
