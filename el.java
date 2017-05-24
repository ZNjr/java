import java.util.Scanner;

public class Source {
    private static Scanner in = new Scanner(System.in);
    private static int[] medians;
    private static int[] partition(int[] currentArray, int pivot, int l, int r) {
        for (int i = l; i <= r; i++) {
            if (currentArray[i] < pivot) {
                swap(currentArray,l, i);
                l++;
            }
            if (currentArray[i] > pivot) {
                swap(currentArray,i, r);
                r--;
                i--;
            }
        }
        return new int[]{l,r};
    }

    private static int findN(int[] currentArray, int l, int r, int key) {
        if (r - l < 50) return selectionSort(currentArray, l, r, key);
        int j = 0;
        int numberOfMedians = (r-l)/5;
        for (int i = l; i <= r - 5; i += 5) {
            medians[j++] = selectionSort(currentArray, i, i + 5, i + 3);
        }
        int pivot = findN(medians, 0, numberOfMedians, (numberOfMedians) / 2);
        int[] pos = partition(currentArray, pivot, l, r - 1);
        if (key <= pos[0]) return findN(currentArray, l, pos[0], key);
        if (key <= pos[1] + 1) return pivot;
        return findN(currentArray, pos[1] + 1, r, key);
    }

    private static int selectionSort(int[] currentArray, int l, int r, int key) {
        for (int i = l; i < key; i++) {
            int min_value = i;
            for (int j = i + 1; j < r; j++) if (currentArray[min_value] > currentArray[j]) min_value = j;
            swap(currentArray, min_value, i);
        }
        return currentArray[key - 1];
    }

    private static void swap(int[] currentArray, int a, int b) {
        int tmp = currentArray[a];
        currentArray[a] = currentArray[b];
        currentArray[b] = tmp;
    }

    public static void main(String[] args) {
        int sets = in.nextInt();
        while (sets-- != 0) {

            StringBuilder result = new StringBuilder();
            int size = in.nextInt();
            int[] array = new int[size];
            medians = new int[size/5];

            for (int i = 0; i < array.length; i++) array[i] = in.nextInt();
            int q = in.nextInt();
            while (q-- != 0) {
                int key = in.nextInt();
                if (key > array.length || key < 1) result.append(Integer.toString(key)).append(" brak").append("\n");
                else
                    result.append(Integer.toString(key)).append(" ").append(findN(array, 0, array.length, key)).append("\n");
            }
            System.out.print(result.toString());
        }
    }
}
