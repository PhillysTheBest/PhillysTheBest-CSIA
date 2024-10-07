package p2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class MergeSort {

    // Recursive function to perform merge sort on a list of object arrays.
    public static void mergeSort(List<Object[]> data, Comparator<Object[]> comparator) {
        // Base case: If the list has 1 or fewer elements, it's already sorted.
        if (data.size() <= 1) {
            return;
        }

        // Find the middle index of the list.
        int middle = data.size() / 2;

        // Split the list into two sublists.
        List<Object[]> left = new ArrayList<>(data.subList(0, middle));
        List<Object[]> right = new ArrayList<>(data.subList(middle, data.size()));

        // Recursively sort both sublists.
        mergeSort(left, comparator);
        mergeSort(right, comparator);

        // Merge the sorted sublists back into the original list.
        merge(data, left, right, comparator);
    }

    // Merge two sorted sublists into a single sorted list.
    private static void merge(List<Object[]> data, List<Object[]> left, List<Object[]> right, Comparator<Object[]> comparator) {
        int i = 0, j = 0, k = 0;

        // Compare elements from both sublists and merge them into the original list.
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                data.set(k++, left.get(i++));
            } else {
                data.set(k++, right.get(j++));
            }
        }

        // Copy any remaining elements from the left sublist, if any.
        while (i < left.size()) {
            data.set(k++, left.get(i++));
        }

        // Copy any remaining elements from the right sublist, if any.
        while (j < right.size()) {
            data.set(k++, right.get(j++));
        }
    }

    // Sort a DefaultTableModel based on a specific column using merge sort.
    public static void mergeSortAndUpdate(DefaultTableModel tableModel, int columnIndex, Comparator<Object[]> comparator) {
        List<Object[]> rowDataList = new ArrayList<>();
        
        // Convert the table data into a list of object arrays for sorting.
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Vector<?> rowVector = (Vector<?>) tableModel.getDataVector().elementAt(i);
            Object[] rowData = rowVector.toArray(new Object[0]);
            rowDataList.add(rowData);
        }

        // Perform merge sort on the list of data.
        mergeSort(rowDataList, comparator);

        // Clear the table model and update it with the sorted data.
        tableModel.setRowCount(0);
        for (Object[] rowData : rowDataList) {
            tableModel.addRow(rowData);
        }
    }
}
