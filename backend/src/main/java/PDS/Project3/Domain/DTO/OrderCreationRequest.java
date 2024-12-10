package PDS.Project3.Domain.DTO;

import PDS.Project3.Domain.Entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationRequest {
    private Date orderDate;
    private String orderNotes;
    private String client;
}