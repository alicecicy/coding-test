package filter;

import filter.entity.FilterBaseDataDTO;

/**
 * test1
 */
public class FilterDuplicates extends FilterAbstract<FilterBaseDataDTO>{

    /**
     * input
     * @param data
     * @return
     */
    @Override
    public String dataHandling(FilterBaseDataDTO data) {
        return data.getValue();
    }

}
