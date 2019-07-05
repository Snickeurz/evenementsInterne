package appli.banque.Strategy;
import appli.banque.Compte_Principale;

/**
 * Contract for abstract strategy.
 * 
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public abstract class AbstractStrategy 
{
    /**
     * Principal Account.
     */
    protected Compte_Principale cpt;

    public AbstractStrategy(final Compte_Principale cpt)
    {
        this.cpt = cpt;
    }

    /**
    * Return principal account.
    */
    public Compte_Principale getCpt() 
    {
        return this.cpt;
    }

    /**
     * Abstract Strategy's Action.
     */
    public abstract void action();
}
