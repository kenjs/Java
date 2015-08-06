package com.cy.swp.common.springex;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * Created by Richie.Lee on 2014/9/26.
 */
public class DoubleEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.equals("")) {
            text = "0";
        }
        setValue(Double.parseDouble(text));
    }

    @Override
    public String getAsText() {    	
        return getValue().toString();
    }
}
