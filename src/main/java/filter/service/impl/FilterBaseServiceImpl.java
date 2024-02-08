package filter.service.impl;;

import filter.entity.FilterBaseDataDTO;
import filter.service.IFilterService;

public class FilterBaseServiceImpl implements IFilterService<FilterBaseDataDTO> {


    @Override
    public String execute(FilterBaseDataDTO data) {
        return filterStr(data);
    }

    @Override
    public void stringPrintln(String str) {
        System.out.println(str);
    }

    @Override
    public void mappingStringPrintln(String str, String target, String source) {

    }
}
