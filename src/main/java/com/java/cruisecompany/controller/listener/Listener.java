package com.java.cruisecompany.controller.listener;

import com.java.cruisecompany.model.dto.TicketDTO;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;

public class Listener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        if (srae.getName().equals("ticket")) {
            TicketDTO ticket = (TicketDTO) srae.getValue();
            System.out.println("Ticket with ID: " + ticket.getUser() + " has been added");
        }
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        // handle attribute removal if necessary
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        // handle attribute replacement if necessary
    }
}
