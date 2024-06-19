package hackeru.fridarik.snipsnapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntityListDTO <T> {

    private long totalItems;
    private int pageNo;
    private int pageSize;
    private int totalPages;

    private boolean isFirst;
    private boolean isLast;

    private Collection<T> items;
}
