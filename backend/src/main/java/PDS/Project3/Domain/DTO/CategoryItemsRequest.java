package PDS.Project3.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryItemsRequest {
    private String mainCategory;
    private String subCategory;
}
