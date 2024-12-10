package PDS.Project3.Domain.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Location {
    private int roomNum;
    private int shelfNum;
    private String shelf;
    private String shelfDescription;
}
