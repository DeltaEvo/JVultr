package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.JVultrAPI;
import xyz.deltaevo.jvultr.utils.Reflection;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by david on 30/10/15.
 */
public class JVultrAccountInfo {
    private float balance;
    private float pendingCharges;
    private Date lastPayment;
    private float lastPaymentAmount;

    public JVultrAccountInfo(JsonObject value){
        this.balance = value.get("balance").getAsFloat();
        this.pendingCharges = value.get("pending_charges").getAsFloat();
        try {
            this.lastPayment = JVultrAPI.dateFormat.parse(value.get("last_payment_date").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.lastPaymentAmount = value.get("last_payment_amount").getAsFloat();
    }

    public float getBalance() {
        return balance;
    }

    public float getPendingCharges() {
        return pendingCharges;
    }

    public Date getLastPayment() {
        return lastPayment;
    }

    public float getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
