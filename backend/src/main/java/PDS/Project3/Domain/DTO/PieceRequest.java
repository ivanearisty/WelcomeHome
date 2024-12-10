package PDS.Project3.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieceRequest {
    private String description;
    private int length;
    private int width;
    private int height;
    private int roomNum;
    private int shelfNum;
    private String notes;
}