package com.substring.helpdesk.service;

import com.substring.helpdesk.entity.Ticket;
import com.substring.helpdesk.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class TicketService {

    private TicketRepository ticketRepository;

//    create Ticket
    @Transactional
    public Ticket createTicket(Ticket ticket){
        ticket.setId(null);
       return this.ticketRepository.save(ticket);
    }

//     Update Ticket
    @Transactional
    public Ticket updateTicket(Ticket ticket){
        return this.ticketRepository.save(ticket);
    }

//     get ticket logic
    public Ticket getTicket(Long ticketId){
        return this.ticketRepository.findById(ticketId).orElse(null);
    }

//      get ticket by username
    public Ticket getTicketByEmailId(String emailId){
        return this.ticketRepository.findByEmail(emailId).orElse(null);
    }
}
