import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Item implements Comparable<Item> {
    int price;
    String name;
    String description;

    int discount;

    public Item() {}

    public Item(int price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.discount = 0;
    }

    public Item(int price, String name, String description, int discount) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.discount = discount;
    }


    @Override
    public int compareTo(Item o) {
        if(this.price < o.price) {
            return -1;
        } else if(this.price == o.price) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                '}';
    }

    public static void comparatorDemo() {
        var item1 = new Item(23, "Whip It", "Stain remover", 8);
        var item2 = new Item(5, "Bread", "Food item", 2);
        var item3 = new Item(8, "Jam", "Food item", 9);
        var item4 = new Item(4, "Lays", "Food item", 6);


        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        System.out.println("Before sorting using Comparable: ");

        for(Item item : items) {
            System.out.println(item);
        }

        Collections.sort(items);

        System.out.println();

        System.out.println("After sorting using Comparable: ");

        for(Item item : items) {
            System.out.println(item);
        }

        System.out.println();

        Comparator<Item> discountSorter = new DiscountSorter();

        System.out.println("Before sorting using Comparator: ");

        for(Item item : items) {
            System.out.println(item);
        }

        Collections.sort(items, discountSorter);

        System.out.println();

        System.out.println("After sorting using Comparator: ");

        for(Item item : items) {
            System.out.println(item);
        }
    }

    public static class DiscountSorter implements Comparator<Item> {

        @Override
        public int compare(Item o1, Item o2) {
            if (o1.discount < o2.discount) {
                return -1;
            } else if (o1.discount == o2.discount) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
