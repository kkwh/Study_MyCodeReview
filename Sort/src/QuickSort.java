/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SortAlgorithm
 */
class QuickSort implements SortAlgorithm {

    /**
     * This method implements the Generic Quick Sort
     *
     * @param array The array to be sorted Sorts the array in increasing order
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) { // 제네릭 타입 T를 상속하는 배열이 매개변수. 
        doSort(array, 0, array.length - 1); // doSort 메서드 호출
        return array; // 반환 타입은 T 타입의 배열.
    }

    /**
     * The sorting process
     *
     * @param left The first index of an array
     * @param right The last index of an array
     * @param array The array to be sorted
     */
    private static <T extends Comparable<T>> void doSort(
        T[] array,
        int left, // left(왼쪽 인덱스)와 right(오른쪽 인덱스)를 받아서 left가 right보다 작을때까지 array 배열을 재귀적으로 정렬
        int right
    ) {
        if (left < right) { // left가 right보다 작다면
            int pivot = randomPartition(array, left, right); // randomPartition 호출해서 리턴값을 pivot에 저장
            doSort(array, left, pivot - 1); // 왼쪽부터 중간까지 재귀적으로 정렬
            doSort(array, pivot, right); // 중간부터 오른쪽까지 재귀적으로 정렬
        }
    }

    /**
     * Ramdomize the array to avoid the basically ordered sequences
     *
     * @param array The array to be sorted
     * @param left The first index of an array
     * @param right The last index of an array
     * @return the partition index of the array
     */
    private static <T extends Comparable<T>> int randomPartition( // 배열을 무작위로 섞은 후  partition() 메서드로 배열을 분할
        T[] array,
        int left,
        int right
    ) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1)); // 0~(right-left) 난수 + left를 변수에 저장
        SortUtils.swap(array, randomIndex, right); // 배열의 인덱스 randomIndex와 right에 해당하는 두 원소의 위치를 바꿈.
        return partition(array, left, right); // partion()메서드로 배열을 분할
    }

    /**
     * This method finds the partition index for an array
     *
     * @param array The array to be sorted
     * @param left The first index of an array
     * @param right The last index of an array Finds the partition index of an
     * array
     */
    private static <T extends Comparable<T>> int partition( // pivot을 기준으로 두개의 부분으로 나누는 메서드
        T[] array,
        int left,
        int right
    ) {
        int mid = (left + right) >>> 1; // left와 right의 합을 구하고, 이 값을 2로 나누어 중간 값(mid)을 계산
       // 비트 이동 연산자: 비트 이동 연산자를 사용하여 2로 나누는 연산을 수행. 오른쪽으로 비트를 이동시키면서
       // 왼쪽 끝 비트에는 0을 채워 넣음. (left + right)가 음수일 경우에도 정확한 중간값을 구하기 위함, 나누기(/)는 사용 불가
        T pivot = array[mid]; // 배열의 중간값을 pivot으로 설정

        while (left <= right) { // 배열의 원소들을 pivot값보다 작은 값과 큰 값으로 구분, left가 right보다 작거나 같을 때 까지
            while (SortUtils.less(array[left], pivot)) { // array[left]가 pivot(array[mid])보다 작을 때 까지
                ++left;
            }
            while (SortUtils.less(pivot, array[right])) { // pivot(array[mid])이 array[right]보다 작을 때 까지
                --right; 
            }
            if (left <= right) { // left가 right보다 작거나 같다면
            	SortUtils.swap(array, left, right);  // 배열의 인덱스 left와 right에 해당하는 두 원소의 위치를 바꿈.
                ++left; // left 1 증가
                --right; // right 1 감소
            }
        }
        return left; // pivot의 위치를 반환
    }
    
    public static void main(String[] args) {
        Integer[] toSort = { 4, 23, 6, 78, 1, 54, 231, 9, 12 };
        Integer[] toSort2 = { -4, 23, 6, -78, -1, -54, 231, 9, -12 };

        QuickSort sort = new QuickSort(); 
        sort.sort(toSort);   
        sort.sort(toSort2);   
        
        // less() -> greater() 내림차순 정렬
        SortUtils.print(toSort); 
        SortUtils.print(toSort2); 
    }
}