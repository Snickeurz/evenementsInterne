package appli.lib;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

/**
 * Support for change of properties.
 * 
 * @author SIBAUD Nicolas & LUTUMBA Jonathan
 */
public class PropertyChangeSupport implements Serializable 
{
    final private Map<String,List<PropertyChangeListener>> pc;
    
    public PropertyChangeSupport()
    {
        pc=new HashMap<>();
    }
    
    public void addPropertyChangeListener (final String property_name , final PropertyChangeListener pcl)
    {
        List<PropertyChangeListener> pl;
        if (! pc.containsKey(property_name))
        {
            pl=new ArrayList<>();
            pl.add(pcl);
            pc.put(property_name,pl);
        }
        else
        {
                pl=pc.get(property_name);
                if (!pl.contains(pcl)) 
                {
                    pl.add(pcl);
                    pc.put(property_name,pl);
                }
        }   
    }
    
    public void removePropertyChangeListener (final String property_name , final PropertyChangeListener pcl)
    {
        List<PropertyChangeListener> pl;
        if (pc.containsKey(property_name))
        {
            pl=pc.get(property_name);
            pl.remove(pcl);
        }
    }
    
    protected void firePropertyChange (final String property_name, final Object oldValue, final Object newValue)
    {
        PropertyChangeEvent ev=new PropertyChangeEvent (this, property_name, oldValue, newValue);
        List<PropertyChangeListener> liste;
        liste=pc.get(property_name);
        Iterator it=liste.iterator();
        PropertyChangeListener e;
        while (it.hasNext())
        {
            e=(PropertyChangeListener)it.next();
            e.propertyChange(ev);
        }
    }
}
