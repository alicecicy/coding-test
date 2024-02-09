package filter;

import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public abstract class FilterAbstract<T> {

    public final String operation(T data){
        return removerRedundant(dataHandling(data));
    }

    /**
     * 入参-数据处理
     * @param data
     * @return
     */
    public abstract String dataHandling(T data);


    /**
     * 去除重复
     * @param value
     * @return
     */
    private String removerRedundant(String value) {
        if (StringUtils.isEmpty(value)) return value;
        List<Integer> values = value.chars().boxed().collect(Collectors.toList());
        // 获取字母重复3+次需要的下标
        List<Integer> indexList = getRepeatIndexs(values);
        // 根据下标删除，从最后开始删
        for (int i = indexList.size(); i > 0 ; i--) {
            int index = indexList.get(i-1);
            values.remove(index);
        }
        String result = values.stream().map(m -> (char) (int) m).map(String::valueOf).collect(Collectors.joining());
        if (CollectionUtils.isNotEmpty(indexList)) {
            System.out.println(result);
            // 去除字母重复3+次后，继续判断是否有重复
            result = removerRedundant(result);
        }
        return result;
    }

    /**
     * 获取字符串指定重复次数的所有下标
     * @param list
     * @return
     */
    private List<Integer> getRepeatIndexs(List<Integer> list) {
        List<Integer> indexList = new ArrayList<>();
        // 重复的情况结果
        List<Boolean> compareList = null;
        int repeatNum = 3;
        while (repeatNum > 1) {
            // 根据指定repeatNum重复次数循环，调用listDataCompare比对得出重复的情况
            if (Optional.ofNullable(compareList).isPresent()) {
                compareList = listDataCompare(compareList);
            } else {
                compareList = listDataCompare(list);
            }
            repeatNum--;
        }
        for (int i = 0; i < compareList.size(); i++) {
            if (compareList.get(i)) {
                // true为重复，下标对于原字符重复的第一个值，重复的数量为repeatNum
                indexList.add(i);
                indexList.add(i+1);
                indexList.add(i+2);
            }
        }
        indexList = indexList.stream().distinct().collect(Collectors.toList());
        return indexList;
    }

    /**
     * 比对数组的数据是否相同：如果是布尔值的情况，直接做布尔运算
     * 返回结果是两个值直接是否有重复
     */
    private <t> List<Boolean> listDataCompare(List<t> list) {
        List<Boolean> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(list) || list.size() < 2) {
            return result;
        }
        for (int i= 1; i < list.size(); i++) {
            t pre = list.get(i-1);
            t current = list.get(i);
            result.add(pre instanceof Boolean?
                    (Boolean)list.get(i-1) && (Boolean)list.get(i):
                    pre != null && (Integer) current >=97 && (Integer) current <=122 && pre.equals(current));
        }
        return result;
    }
}
