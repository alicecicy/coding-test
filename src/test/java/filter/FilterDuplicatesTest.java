import filter.FilterDuplicates;
import filter.entity.FilterBaseDataDTO;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test1
 */
public class FilterDuplicatesTest {
    @Test
    public void test1() {
        // demo样例
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("aabcccbbad");
        Assert.assertEquals("d", new FilterDuplicates().operation(data));
    }

    @Test
    public void test2() {
        // 空情况
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("");
        Assert.assertEquals("", new FilterDuplicates().operation(data));
    }

    @Test
    public void test3() {
        // 无重复字母
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("abc");
        Assert.assertEquals("abc", new FilterDuplicates().operation(data));
    }


    @Test
    public void test4() {
        // 2个重复字母
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("aabbcc");
        Assert.assertEquals("aabbcc", new FilterDuplicates().operation(data));
    }

    @Test
    public void test5() {
        // 3个以上字母重复+部分不重构
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("abbccccccddddd");
        Assert.assertEquals("abb", new FilterDuplicates().operation(data));
    }

    @Test
    public void test6() {
        // 全部3个以上字母重复
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("aaaabbbccccccddddd");
        Assert.assertEquals("", new FilterDuplicates().operation(data));
    }

    @Test
    public void test7() {
        // 3个以上字母重复+3个以上非字母重复
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("aabbccccccdddddb11122222444");
        Assert.assertEquals("aa11122222444", new FilterDuplicates().operation(data));
    }

    @Test
    public void test8() {
        // 产生3次循环去除情况
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("aabbaaaabbaa");
        Assert.assertEquals("", new FilterDuplicates().operation(data));
    }

    @Test
    public void test9() {
        // 产生4次循环去除情况
        FilterBaseDataDTO data = new FilterBaseDataDTO();
        data.setValue("daabbaaaabbaadd12");
        Assert.assertEquals("12", new FilterDuplicates().operation(data));
    }
}