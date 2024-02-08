package filter.service;

import filter.entity.FilterBaseDataDTO;
import filter.entity.FilterDataDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFilterService<T> {

    String execute(T data);

    void stringPrintln(String str);

    void mappingStringPrintln(String str, String target, String source);

    /**
     * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
     * characters are identical, remove them from the string
     * @param data
     * @return
     */
    default String filterStr(FilterBaseDataDTO data) {
        String str = data.getValue();
//        if (!Optional.ofNullable(str).isPresent()) {
//            return null;
//        }
        if (StringUtils.isEmpty(data.getValue())) {
            return null;
        }
        Pattern pattern = Pattern.compile("([a-z])\\1{2,}");
        Matcher matcher = pattern.matcher(str);
        boolean flag = false;
        while (matcher.find()) {
            String match = matcher.group();
            str = str.replaceFirst(match, "");
            stringPrintln(str);
            flag = true;
        }
        if (flag) {
            data.setValue(str);
            str = filterStr(data);
        }
        return str;
    }

    /**
     * First, replace them with a
     * single character that comes before it alphabetically
     * Then, for a given string that only contains alphabet characters a-z, if 3 or more consecutive
     * characters are identical, remove them from the string. Repeat this process until
     * there is no more than 3 identical characters sitting besides each other.
     * @param data
     * @return
     */
    default String filterStrByMapping(FilterDataDTO data) {
//        if (!Optional.ofNullable(data.getValue()).isPresent() || data.getMapping() == null) {
//            return null;
//        }
        if (StringUtils.isEmpty(data.getValue()) || data.getMapping() == null) {
            return null;
        }
        Map<String, String> mapping = data.getMapping();
        boolean flag = false;
        for (String target: mapping.keySet()) {
            String source = mapping.get(target);
            if (data.getValue().contains(target)) {
                data.setValue(data.getValue().replace(target, source));
                mappingStringPrintln(data.getValue(), target, source);
                flag = true;
            }
        }
        if (flag) {
            data.setValue(filterStrByMapping(data));
        }
        return data.getValue();
    }

}
