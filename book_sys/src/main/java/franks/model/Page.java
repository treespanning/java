package franks.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//分页实现
@Setter
@Getter
@ToString
public class Page {
    private String searchText;
    private String sortOrder;
    private Integer pageSize;
    private Integer pageNumber;
}
