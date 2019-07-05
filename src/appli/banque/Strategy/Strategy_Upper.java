package appli.banque.Strategy;
import appli.banque.Compte_Principale;
/**
 * Implementation of Abstract Strategy for a upper strategy.
 * 
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class Strategy_Upper extends AbstractStrategy
{
    /**
     * Constructor.
     * 
     * @param principalAccount given account
     */
    public Strategy_Upper(final Compte_Principale principalAccount) 
    {
        super(principalAccount);
    }

    @Override
    public void action() 
    {
        cpt.transfer(cpt.getSolde()-cpt.get_Client_set_upper_bound(), cpt.getEpargne()); 
    }
}
