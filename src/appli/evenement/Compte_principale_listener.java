package appli.evenement;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import appli.banque.Compte_Principale;
import appli.context.AppContext;
/**
 * Listener for principalAccount.
 * 
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class Compte_principale_listener implements PropertyChangeListener
{
    /**
     * Application context.
     */
    AppContext ctx;

    public Compte_principale_listener(final AppContext ctx) 
    {
        this.ctx = ctx;
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent evt) 
    {
         if (predicat_evt (evt))
         {
             trigger_evt (evt);
         }
    }
     private boolean predicat_evt (PropertyChangeEvent evt)
     {
        boolean res=false;
        Compte_Principale cpt = (Compte_Principale)evt.getSource();
        //old and new talking about solde
        float newValue=(float) evt.getNewValue();
        float upper_bound = cpt.get_Client_set_upper_bound();
        float lower_bound = cpt.get_Client_set_lower_bound();
        if(evt.getPropertyName().compareTo("upper_bound") == 0){
            res= newValue > upper_bound;
        }     
        if(evt.getPropertyName().compareTo("lower_bound") == 0){
            res= newValue < lower_bound;
        }
        return res;
    }
    
     /**
      * Process ruptur of stock
      * @param evt 
      */
      private void trigger_evt (final PropertyChangeEvent evt)
      {
        System.out.println("______________");
        System.out.println ("trigger Event ");
        System.out.println ("event Source" + evt.getSource());
        System.out.println("property " + evt.getPropertyName()+" old "+ evt.getOldValue()+ " new "+ evt.getNewValue());
        Compte_Principale cpt = (Compte_Principale)evt.getSource();
        ctx.operationBancaire(cpt,evt.getPropertyName());
      }
}
