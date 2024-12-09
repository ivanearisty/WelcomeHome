package PDS.Project3.Domain;

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
public class Item {
    private int id;
    private String description;
    private byte[] photo;
    private String color;
    private boolean isNew;
    private boolean hasPieces;
    private String material;
    private String mainCategory;
    private String subCategory;
}
