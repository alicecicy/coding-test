package filter;

import filter.entity.FilterDataDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Formatter;
import java.util.Map;

/**
 * test2
 */
public class FilterDuplicatesByMapping extends FilterAbstract<FilterDataDTO>{

    /**
     * input,
     * @param data
     * @return
     */
    @Override
    public String dataHandling(FilterDataDTO data) {
        return filterDataByMapping(data);
    }

    /**
     * replace with designated value
     * @param data
     * @return
     */
    private String filterDataByMapping(FilterDataDTO data) {
        if (StringUtils.isEmpty(data.getValue()) || data.getMapping() == null) {
            return data.getValue();
        }
        Map<String, String> mapping = data.getMapping();
        boolean flag = false;
        for (String target: mapping.keySet()) {
            String source = mapping.get(target);
            if (data.getValue().contains(target)) {
                data.setValue(data.getValue().replace(target, source));
                Formatter formatter = new Formatter(System.out);
                formatter.format("%1$s, %2$s is replaced by %3$s", data.getValue(), target, source);
                System.out.println();
                flag = true;
            }
        }
        if (flag) {
            data.setValue(filterDataByMapping(data));
        }
        return data.getValue();
    }
}
