/*
 * Copyright 2015 DeltaEvolution
 *
 * This file is part of JVultr.
 * JVultr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JVultr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JVultr. If not, see <http://www.gnu.org/licenses/>.
 */
package xyz.deltaevo.jvultr.api;

import com.google.gson.JsonObject;
import xyz.deltaevo.jvultr.JVultrAPI;
import xyz.deltaevo.jvultr.utils.Reflection;

import java.text.ParseException;
import java.util.Date;

/**
 * Represent Vultr AccountInfo
 * <p><a href="https://www.vultr.com/api/#account_info">Vultr API Doc</a></p>
 * @author DeltaEvolution
 */
public class JVultrAccountInfo {

    /**
     * Current account balance
     */
    private float balance;

    /**
     *Current account pending charges
     */
    private float pendingCharges;

    /**
     * Last payment date
     */
    private Date lastPayment;

    /**
     * Last payment amount
     */
    private float lastPaymentAmount;

    /**
     * DON'T USE THIS CONSTRUCTOR !
     * @param value the JsonObject representing this object
     */
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

    /**
     * Get current account balance
     * @return current Balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * Get current account pending charges
     * @return pending charges
     */
    public float getPendingCharges() {
        return pendingCharges;
    }

    /**
     * Get last payment date
     * @return last payment Date
     */
    public Date getLastPayment() {
        return lastPayment;
    }

    /**
     * Get last payment amount
     * @return last payment amount
     */
    public float getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    @Override
    public String toString() {
        return Reflection.toString(this);
    }
}
