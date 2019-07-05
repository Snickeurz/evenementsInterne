package appli.context;

import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import appli.banque.*;
import appli.banque.Strategy.*;
import appli.evenement.Compte_principale_listener;
import java.util.Timer;

/**
 * Application context to manage banque.
 * 
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class AppContext 
{
    /**
     * Map of accounts.
     */
     final private Map<Integer, Compte_Principale> comptes;
     /**
      * Map of clients.
      */
     final private Map<Integer, Client> clients;
     /**
      * Map of client with interest.
      */
     final private Map<Client,CalculInteret> client_interest;
     /**
      * Timer.
      */
     final private Timer agenda = new Timer();
     /**
      * 3".
      */
     public static final long TROIS_MINUTES = 1000 * 60;

     /**
      * Constructor. 
      */
     public AppContext ()
     { 
         this.comptes = new HashMap<>();
         this.clients = new HashMap<>();
         this.client_interest = new HashMap<>();
     }
   
     /**
      * Add new account.
      * 
      * @param e 
      */
      public void addComptes(final Compte_Principale principalAccount) 
      {
        PropertyChangeListener principal_listener = new Compte_principale_listener(this);
        principalAccount.addPropertyChangeListener("upper_bound", principal_listener);
        principalAccount.addPropertyChangeListener("lower_bound", principal_listener);
        //this.agenda.schedule(new CalculInteret(this), TROIS_MINUTES);//uniquement pour le compte epargne
        comptes.put(principalAccount.getId(),principalAccount);
        }
   
   /**
    * Add new client.
    * 
    * @param client given client
    */
    public void addClient(final Client client) 
    {
       clients.put(client.getId_client(),client);
       CalculInteret interest = new CalculInteret(client,this);
       client_interest.put(client,interest);
       agenda.schedule(interest, TROIS_MINUTES);
    }

    public Map<Integer, Compte_Principale> getComptes() 
    {
        return this.comptes;
    }

    public Map<Integer, Client> getClients() 
    {
        return this.clients;
    }
   
   public void getOperations(final int id_client)
   {
       if(clients.get(id_client) != null)
       {
           clients.get(id_client).showOperation();
       }
   }
   
   public void getAllOperations()
   {
        clients.forEach((k, v) -> {
            getOperations(k);
        });
   }
   
   //Extern event
   
   /**
    * New account operation.
    * 
    * @param principalAccount
    * @param typeOperation 
    */
   public void operationBancaire(final Compte_Principale principalAccount, final String typeOperation){
        System.out.println("upper strategy");
        AbstractStrategy strategy = null;
        if(typeOperation.compareTo("upper_bound") == 0)
        {
            strategy = new Strategy_Upper(principalAccount);
        }
        if(typeOperation.compareTo("lower_bound") == 0)
        {
            strategy = new Strategy_Lower(principalAccount);
        }
        if(typeOperation.compareTo("tasker")==0)
        {
            strategy = new Strategy_interest(principalAccount,this);
        }
        strategy.action();
   }
   
   public void feed (Compte_Principale principalAccount, float amount)
   {
       principalAccount.feed(amount);
   }
   
   public void withdraw (Compte_Principale principalAccount, float amount){
       principalAccount.withdraw(amount);
   }

    public Timer getAgenda() 
    {
        return this.agenda;
    }

    public Map<Client, CalculInteret> getClient_interest() 
    {
        return this.client_interest;
    }
    
    
}

