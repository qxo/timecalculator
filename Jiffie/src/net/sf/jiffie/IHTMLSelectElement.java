/*
 * file:       IHTMLSelectElement.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software Limited 2004
 * date:       21/03/2004
 */

/*
 * This file is part of JIFFIE.
 *
 * JIFFIE is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * JIFFIE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JIFFIE; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package net.sf.jiffie;

import com.jacob.com.Dispatch;


/**
 * Implementation of the IHTMLSelectElement interface.
 */
public class IHTMLSelectElement extends IHTMLElement
{
   /**
    * Constructor
    *
    * @param parentBrowser parent browser
    * @param object Jacob dispatch interface
    */
   public IHTMLSelectElement (InternetExplorer parentBrowser, Dispatch object)
   {
      super(parentBrowser, object);
   }

   /**
    * Retrieves the number of items in the drop down box.
    *
    * @return number of items in the drop down box
    */
   public int getLength ()
   {
      return (getIntegerProperty("length"));
   }

   /**
    * Retrieves the value of the currently selected index
    *
    * @return selected index value
    */
   public int getSelectedIndex ()
   {
      return (getIntegerProperty("selectedIndex"));
   }

   /**
    * Sets the value of the currently selected index. Note that this
    * does not raise an "onchange" event. The fireEvent method must be
    * called explicitly if the event is required.
    *
    * @param index new selected index value
    */
   public void setSelectedIndex (int index)
   {
      setIntegerProperty("selectedIndex", index);
   }

   /**
    * Retrieves the current value
    *
    * @return value
    */
   public String getValue ()
   {
      return (getStringProperty("value"));
   }

   /**
    * Sets the value property
    *
    * @param value new value
    */
   public void setValue (String value)
   {
      setStringProperty("value", value);
   }

   /**
    * Retrieves the form property
    *
    * @return input type
    */
   public IHTMLFormElement getForm ()
   {
      IHTMLFormElement element;

      Dispatch dispatch = getDispatchProperty("form");

      if (dispatch == null)
      {
         element = null;
      }
      else
      {
         element = new IHTMLFormElement(getParentBrowser(), dispatch);
      }

      return (element);
   }
}
