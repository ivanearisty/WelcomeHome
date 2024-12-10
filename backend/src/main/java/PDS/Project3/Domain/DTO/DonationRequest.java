package PDS.Project3.Domain.DTO;

import PDS.Project3.Domain.Entities.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationRequest {
    private String donorUsername; // Donor's username
    private ItemRequest item;
    private List<PieceRequest> pieces; // Details of the pieces
}