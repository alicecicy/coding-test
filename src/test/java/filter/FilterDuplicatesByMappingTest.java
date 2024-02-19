import filter.FilterDuplicates;
import filter.FilterDuplicatesByMapping;
import filter.entity.FilterBaseDataDTO;
import filter.entity.FilterDataDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class FilterDuplicatesByMappingTest {
    @Test
    public void test1() {
        // demo example
        FilterDataDTO data = new FilterDataDTO();
        data.setValue("abcccbad");
        data.setMapping(Map.of("ccc", "b", "bbb", "a"));
        Assert.assertEquals("d", new FilterDuplicatesByMapping().operation(data));
    }

    @Test
    public void test2() {
        // 3 or more consecutive characters characters are identical and replacement conditions is null
        FilterDataDTO data = new FilterDataDTO();
        data.setValue("abccc");
        data.setMapping(null);
        Assert.assertEquals("ab", new FilterDuplicatesByMapping().operation(data));
    }


    @Test
    public void test3() {
        // after first replacement conditions, hit replacement conditions again
        FilterDataDTO data = new FilterDataDTO();
        data.setValue("cccbbcc");
        data.setMapping(Map.of("ccc", "b", "bbb", "c"));
        Assert.assertEquals("b", new FilterDuplicatesByMapping().operation(data));
    }
}