/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli.banque;

/**
 * Contract for compte bancaire.
 * 
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public interface CompteBancaire 
{  
    /**
     * Transfer given amount to targeted beneficiary.
     * 
     * @param amount a float amoun
     * @param beneficiary an existing client
     * @return true for sucess or false for fail
     */
    public boolean transfer(float amount, CompteBancaire beneficiary);
    /**
     * Request given amount from specified source.
     * 
     * @param amount float amoun
     * @param source targeted Client
     * @return true for sucess else false
     */
    public boolean request (float amount, CompteBancaire source);
    /**
     * Feed an accounnt amount.
     * 
     * @param amount given amount
     * @return true or false
     */
    public boolean feed (float amount);
    /**
     * Withdraw a specific amount.
     * 
     * @param amount given float amount
     * @return true for sucess else false
     */
    public boolean withdraw (float amount);
    /**
     * Get current solde.
     * 
     * @return solde
     */
    public float getSolde();
    
}
