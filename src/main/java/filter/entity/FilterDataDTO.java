package filter.entity;

import lombok.*;

import java.util.Map;
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FilterDataDTO extends FilterBaseDataDTO {

    private Map<String, String> mapping;

}
