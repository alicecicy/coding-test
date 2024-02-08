package filter.service.impl;

import filter.entity.FilterBaseDataDTO;
import filter.entity.FilterDataDTO;
import filter.service.IFilterService;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

;

public class FilterDataServiceImpl implements IFilterService<FilterDataDTO> {


    @Override
    public String execute(FilterDataDTO data) {
        String str = filterStrByMapping(data);
        data.setValue(str);
        str = filterStr(data);
        System.out.println(str);
        return str;
    }

    @Override
    public void stringPrintln(String str) {

    }

    @Override
    public void mappingStringPrintln(String str, String target, String source) {
        Formatter formatter = new Formatter(System.out);
        formatter.format("%1$s, %2$s is replaced by %3$s", str, target, source);
        System.out.println();
    }
}
