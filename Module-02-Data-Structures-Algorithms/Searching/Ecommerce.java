class Product {
    int ProductId;
    String ProductName;
    String ProductCategory;

    Product(int productId, String productName, String productCategory) {
        this.ProductId = productId;
        this.ProductName = productName;
        this.ProductCategory = productCategory;
    }
}

class LinearSearch {
    public static int LinearSearch(Product[] products, String target) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].ProductName.equals(target)) {
                return i;
            }
        }
        return -1; // Return -1 if the product is not found
    }
}

class BinarySearch {
    public static int BinarySearch(Product[] products, int target) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // using this approach instead of (left + right) / 2 avoids integer overflow for larger array sizes 

            if (products[mid].ProductId == target) {
                return mid;
            } else if (products[mid].ProductId < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

public class Ecommerce {

    public static void main(String[] args) {
        Product[] products = {
                new Product(1, "Laptop", "Electronics"),
                new Product(2, "Phone", "Electronics"),
                new Product(3, "Shoes", "Fashion"),
                new Product(4, "Watch", "Fashion"),
                new Product(5, "Book", "Education")
        };
        int linearIndex = LinearSearch.LinearSearch(products, "Phone");
        int binaryIndex = BinarySearch.BinarySearch(products, 3);

        System.out.println("Linear Search: " + linearIndex);
        System.out.println("Binary Search: " + binaryIndex);
    }

}

// Output:
// Linear Search Index: 1
// Binary Search Index: 1

// Time Complexity:
// Linear Search: O(n) - In the worst case, we may have to check every product in the array to find the target product.
// Binary Search: O(log n) - The array must be sorted for binary search to work. 
// In each step, we eliminate half of the remaining elements, leading to logarithmic time complexity.

// Compared to linear search, binary seacrh is efficient for large product lists
//In real e commerce platforms, we'd use a db with indexes, or an in memory hashmaps for O(1) lookups