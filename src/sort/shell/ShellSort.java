package sort.shell;

public class ShellSort {

    public static void sort(int[] a) {
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < a.length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < a.length; i++) {
                int temp = a[i];
                int j = i;
                for (; j >= h && temp < a[j - h]; j -= h) {
                    a[j] = a[j - h];
                }
                a[j] = temp;
            }
            h /= 3;
        }
    }

    public static void sort(long[] a) {
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < a.length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < a.length; i++) {
                long temp = a[i];
                int j = i;
                for (; j >= h && temp < a[j - h]; j -= h) {
                    a[j] = a[j - h];
                }
                a[j] = temp;
            }
            h /= 3;
        }
    }

    public static void sort(double[] a) {
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < a.length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < a.length; i++) {
                double temp = a[i];
                int j = i;
                for (; j >= h && temp < a[j - h]; j -= h) {
                    a[j] = a[j - h];
                }
                a[j] = temp;
            }
            h /= 3;
        }
    }

    @SuppressWarnings("unchecked")
    public static void sort(Comparable<?>[] a) {
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < a.length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < a.length; i++) {
                Comparable<?> temp = a[i];
                int j = i;
                for (; j >= h && ((Comparable<Object>) temp).compareTo(a[j - h]) < 0; j -= h) {
                    a[j] = a[j - h];
                }
                a[j] = temp;
            }
            h /= 3;
        }
    }

}
