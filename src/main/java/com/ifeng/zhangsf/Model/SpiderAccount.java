package com.ifeng.zhangsf.Model;


import java.util.Date;

/**
 * @author changyu3
 * @date 2018/12/13
 * @description:
 */
public class SpiderAccount {
    private String ifengId;
//    private String site;
    private String siteAccount;
//    private String account;
    private Date _id;

    public SpiderAccount() {
    }

    public String getSiteAccount() {
        return siteAccount;
    }

    public void setSiteAccount(String siteAccount) {
        this.siteAccount = siteAccount;
    }
//    public SpiderAccount(String ifengId, String site, String account, String _id) {
//        this.ifengId = ifengId;

    public SpiderAccount(String ifengId, String siteAccount, Date _id) {
        this.ifengId = ifengId;
        this.siteAccount = siteAccount;
        this._id = _id;
    }
//        this.site = site;
//        this.account = account;
//        this._id = _id;
//    }


    public String getIfengId() {
        return ifengId;
    }

    public void setIfengId(String ifengId) {
        this.ifengId = ifengId;
    }



    @Override
    public String toString() {
        return "ifengId='" + ifengId + " | siteAccount='" + siteAccount  ;
    }
}
