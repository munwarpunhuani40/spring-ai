package com.substring.helpdesk.tools;

import com.substring.helpdesk.entity.Ticket;
import com.substring.helpdesk.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketDatabaseTool {

    private final TicketService ticketService;

//    create ticket tool
    @Tool(description = "This tool helps to create new ticket in database")
    public Ticket createTicketTool(@ToolParam(description = "Ticket fields required to create new tickets.") Ticket ticket){
        try {
            System.out.println("Going to create ticket");
            System.out.println(ticket);
            return this.ticketService.createTicket(ticket);
        }catch (Exception exe){
            exe.printStackTrace();
            return null;
        }
    }

//    get ticket using username
    @Tool(description = "This tool helps to  get ticket by username.")
    public Ticket getTicketByUsername(@ToolParam(description = "email id whose ticket is required.") String emailId){
        return  this.ticketService.getTicketByEmailId(emailId);
    }

    @Tool(description = "This tool helps to update ticket.")
    public Ticket updateTicket(@ToolParam(description = "new ticket details with ticket id") Ticket ticket){
        return this.ticketService.updateTicket(ticket);
    }

//    get current system time
    @Tool(description = "This tool helps to get current system time.")
    public String getCurrentTime(){
        return String.valueOf(System.currentTimeMillis());
    }

}
