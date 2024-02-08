package filter.service.impl;

import filter.entity.FilterBaseDataDTO;
import filter.service.IFilterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FilterBaseServiceImplTest {

    IFilterService<FilterBaseDataDTO> filterService;

    @Before
    public void before() {
        filterService = new FilterBaseServiceImpl();
    }
    /**
     * test 1
     */
    @Test
    public void execute() {
        System.out.println("--------example1-start--------");
        Assert.assertEquals("d", filterService.execute(FilterBaseDataDTO.builder().value("aabcccbbad").build()));
        System.out.println("--------example1-end----------");

        System.out.println("--------example2-start--------");
        Assert.assertEquals(null, filterService.execute(FilterBaseDataDTO.builder().build()));
        System.out.println("--------example2-end----------");

        System.out.println("--------example3-start--------");
        Assert.assertEquals("abcdef", filterService.execute(FilterBaseDataDTO.builder().value("abcdef").build()));
        System.out.println("--------example3-end----------");
    }

}