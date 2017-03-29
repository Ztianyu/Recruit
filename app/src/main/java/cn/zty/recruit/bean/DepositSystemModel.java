package cn.zty.recruit.bean;

/**
 * Created by zty on 2017/3/23.
 */

public class DepositSystemModel {

    /**
     * id : 89c70ef400eb4818a472c2057d62abad
     * office : 2
     * amount : 1000.0
     * deductibleAmount : 2000.0
     * createDate : 2017-03-14 12:52:39.0
     */

    private String id;
    private String office;
    private double amount;
    private double deductibleAmount;
    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(double deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
