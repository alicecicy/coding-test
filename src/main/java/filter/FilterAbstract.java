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
     * input-data processing
     * @param data
     * @return
     */
    public abstract String dataHandling(T data);


    /**
     * remove 3 or more consecutive identical characters
     * @param value
     * @return
     */
    private String removerRedundant(String value) {
        if (StringUtils.isEmpty(value)) return value;
        List<Integer> values = value.chars().boxed().collect(Collectors.toList());
        // get index of 3 or more consecutive identical characters
        List<Integer> indexList = getRepeatIndexs(values);
        // remove with index, from the last position
        for (int i = indexList.size(); i > 0 ; i--) {
            int index = indexList.get(i-1);
            values.remove(index);
        }
        String result = values.stream().map(m -> (char) (int) m).map(String::valueOf).collect(Collectors.joining());
        if (CollectionUtils.isNotEmpty(indexList)) {
            System.out.println(result);
            // after remove, continue to determine if it exists
            result = removerRedundant(result);
        }
        return result;
    }

    /**
     * get index of 3 or more consecutive identical characters
     * @param list
     * @return
     */
    private List<Integer> getRepeatIndexs(List<Integer> list) {
        List<Integer> indexList = new ArrayList<>();
        // repeat result
        List<Boolean> compareList = null;
        int repeatNum = 3;
        while (repeatNum > 1) {
            //  use function listDataCompare, get repeat result
            if (Optional.ofNullable(compareList).isPresent()) {
                compareList = listDataCompare(compareList);
            } else {
                compareList = listDataCompare(list);
            }
            repeatNum--;
        }
        for (int i = 0; i < compareList.size(); i++) {
            if (compareList.get(i)) {
                // when compareList value is true, mean it's the fist repeat index, need three index
                indexList.add(i);
                indexList.add(i+1);
                indexList.add(i+2);
            }
        }
        indexList = indexList.stream().distinct().collect(Collectors.toList());
        return indexList;
    }

    /**
     * compared with adjoin value if identical(when the value is Boolean, using the Boolean operation)
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
