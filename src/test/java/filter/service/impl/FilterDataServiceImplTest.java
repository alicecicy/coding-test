package filter.service.impl;

import filter.entity.FilterBaseDataDTO;
import filter.entity.FilterDataDTO;
import filter.service.IFilterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class FilterDataServiceImplTest {

    IFilterService<FilterDataDTO> filterService;

    @Before
    public void before() {
        filterService = new FilterDataServiceImpl();
    }

    /**
     * test 2
     */
    @Test
    public void execute() {
        FilterDataDTO dto = new FilterDataDTO();

        System.out.println("--------example1-start--------");
        dto.setValue("abcccbad");
        dto.setMapping(Map.of("ccc", "b", "bbb", "a"));
        Assert.assertEquals("d", filterService.execute(dto));
        System.out.println("--------example1-end----------");

        System.out.println("--------example2-start--------");
        dto.setValue(null);
        Assert.assertEquals(null, filterService.execute(dto));
        System.out.println("--------example2-end----------");

        System.out.println("--------example3-start--------");
        dto.setValue("ccccccc");
        Assert.assertEquals("bbc", filterService.execute(dto));
        System.out.println("--------example3-end----------");

        System.out.println("--------example4-start--------");
        dto.setValue("abccdef");
        Assert.assertEquals( "abccdef", filterService.execute(dto));
        System.out.println("--------example4-end----------");

        System.out.println("--------example5-start--------");
        dto.setMapping(null);
        Assert.assertEquals( null, filterService.execute(dto));
        System.out.println("--------example5-end----------");
    }
}