package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ColumnInfo {

    private DataType dataType;

    private Constraint constraint;
}
