package PDS.Project3.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
public class Piece {
    private int itemId;
    private int pieceNum;
    private String description;
    private int length;
    private int width;
    private int height;
    private int roomNum;
    private int shelfNum;
    private String notes;
}
