import filter.FilterDuplicates;
import filter.entity.FilterBaseDataDTO;
import org.junit.Assert;
import org.junit.Test;

/**
 * test1
 */
public class FilterDuplicatesTest {
    @Test
    public void test1() {
        // demo example
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("aabcccbbad");
        Assert.assertEquals("d", new FilterDuplicates().operation(data));
    }

    @Test
    public void test3() {
        // no identical characters
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("abcdefg");
        Assert.assertEquals("abcdefg", new FilterDuplicates().operation(data));
    }


    @Test
    public void test4() {
        // 2 consecutive characters characters are identical
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("aabbccddeeffgg");
        Assert.assertEquals("aabbccddeeffgg", new FilterDuplicates().operation(data));
    }

    @Test
    public void test5() {
        // 3 or more consecutive characters characters are identical
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("abbccccccddddd");
        Assert.assertEquals("abb", new FilterDuplicates().operation(data));
    }

    @Test
    public void test6() {
        // all in 3 or more consecutive characters
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("aaaabbbccccccddddd");
        Assert.assertEquals("", new FilterDuplicates().operation(data));
    }

    @Test
    public void test7() {
        // recursive algorithm with 3 loops
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("aabbaaaabbaa");
        Assert.assertEquals("", new FilterDuplicates().operation(data));
    }

    @Test
    public void test8() {
        // recursive algorithm with 4 loops
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("daabbaaaabbaadd12");
        Assert.assertEquals("12", new FilterDuplicates().operation(data));
    }
}