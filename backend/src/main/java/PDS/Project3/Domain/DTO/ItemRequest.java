package PDS.Project3.Domain.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    private String description;
    private String color;
    private boolean hasPieces;
    private String material;
    private String mainCategory;
    private String subCategory;
}