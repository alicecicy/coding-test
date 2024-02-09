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
        // demo样例
        FilterDataDTO data = new FilterDataDTO();
        data.setValue("abcccbad");
        data.setMapping(Map.of("ccc", "b", "bbb", "a"));
        Assert.assertEquals("d", new FilterDuplicatesByMapping().operation(data));
    }

    @Test
    public void test2() {
        // 映射空情况
        FilterDataDTO data = new FilterDataDTO();
        data.setValue("abccc");
        data.setMapping(null);
        Assert.assertEquals("ab", new FilterDuplicatesByMapping().operation(data));
    }

    @Test
    public void test3() {
        // 字符空情况
        FilterDataDTO data = new FilterDataDTO();
        data.setValue("");
        data.setMapping(Map.of("ccc", "b", "bbb", "a"));
        Assert.assertEquals("", new FilterDuplicatesByMapping().operation(data));
    }

    @Test
    public void test4() {
        // 字符空情况
        FilterDataDTO data = new FilterDataDTO();
        data.setValue("");
        data.setMapping(Map.of("ccc", "b", "bbb", "a"));
        Assert.assertEquals("", new FilterDuplicatesByMapping().operation(data));
    }

    @Test
    public void test5() {
        // 映射后字符再次符合映射条件情况
        FilterDataDTO data = new FilterDataDTO();
        data.setValue("cccbbcc");
        data.setMapping(Map.of("ccc", "b", "bbb", "c"));
        Assert.assertEquals("b", new FilterDuplicatesByMapping().operation(data));
    }
}