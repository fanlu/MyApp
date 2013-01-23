package com.mmtzj.action;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
        binder.registerCustomEditor(Date.class, dateEditor);

        binder.registerCustomEditor(int.class, new IntEditor());
    }

    private static class IntEditor extends CustomNumberEditor{
        public IntEditor(){
            super(Integer.class,true);
        }
        public void setAsText(String text) throws IllegalArgumentException {
            if (text==null || text.trim().equals("")) {
                // Treat empty String as null value.
                setValue(0);
            } else {
                // Use default valueOf methods for parsing text.
                setValue(Integer.parseInt(text));
            }
        }
    }
}
