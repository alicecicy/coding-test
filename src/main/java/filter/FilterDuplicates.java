package filter;

import filter.entity.FilterBaseDataDTO;
import filter.entity.FilterDataDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Formatter;
import java.util.Map;

/**
 * test1
 */
public class FilterDuplicates extends FilterAbstract<FilterBaseDataDTO>{

    @Override
    public String dataHandling(FilterBaseDataDTO data) {
        return data.getValue();
    }

}
