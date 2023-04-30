public class ShellSort implements SortAlgorithm {
	// 삽입 정렬을 개선한 알고리즘으로, 배열을 일정 간격으로 분할하여 
	// 각 부분 배열을 삽입 정렬로 정렬한 후, 간격을 점차 줄여가면서 다시 정렬하는 방식
	
    /**
     * Implements generic shell sort.
     *
     * @param array the array to be sorted.
     * @param <T> the type of elements in the array.
     * @return the sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) { // Comparable 인터페이스를 구현한 객체의 배열을 정렬
        int length = array.length;  // 매개변수 array의 크기로 length 값을 초기화
        int gap = 1; // 배열의 증분 값

        /* Calculate gap for optimization purpose */
        while (gap < length / 3) { // gap을 3배 증가시키고 배열의 길이의 1/3보다 작아질 때까지 반복
            gap = 3 * gap + 1;  // 가장 큰 차이를 갖는 증분 값을 찾기 위한 반복문. 좋은 성능을 내기 위함.
        }

        for (; gap > 0; gap /= 3) { // gap이 0보다 클 때까지 gap을 3으로 나눔
            for (int i = gap; i < length; i++) { // 현재 gap 값만큼 떨어진 인덱스부터 배열의 끝까지 반복
                int j;
                T temp = array[i]; // 현재 원소를 변수 temp에 저장하고 현재 위치 i를 변수 j에 저장
                for (j = i; j >= gap && SortUtils.less(temp, array[j - gap]); j -= gap) { 
                	// gap 만큼 떨어진 요소와 temp를 비교. gap만큼 떨어진 요소가 temp보다 크면(true) gap만큼 떨어진 요소를 gap만큼 오른쪽으로 이동
                	// gap만큼 떨어진 요소가 temp보다 작거나, 첫 번째 요소에 도달할 때까지 위 과정을 반복
                    array[j] = array[j - gap];
                }
                array[j] = temp;  // 정렬이 완료되면 temp를 현재 위치(j)에 저장
            }
        }
        return array;
    }

    /* Driver Code */
    public static void main(String[] args) {
        Integer[] toSort = { 4, 23, 6, 78, 1, 54, 231, 9, 12 }; 
        Integer[] toSort2 = { -4, 23, 6, -78, -1, -54, 231, 9, -12 };

        ShellSort sort = new ShellSort(); 
        sort.sort(toSort);  
        sort.sort(toSort2);  
       
        SortUtils.print(toSort);
        SortUtils.print(toSort2);
    }
}