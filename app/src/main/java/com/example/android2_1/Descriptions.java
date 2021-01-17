package com.example.android2_1;

public class Descriptions {
    public int getIcon(int pos) {
        int[] icons = new int[] {
                R.drawable.fast_icon,
                R.drawable.free_icon,
                R.drawable.powerful
        };
        return icons[pos];
    }

    public String getTitle(int pos) {
        String[] titles = new String[] {
                "Fast",
                "Free",
                "Powerful"
        };
        return titles[pos];
    }

    public String getDescription(int pos) {
        String[] descriptions = new String[] {
                "Descriptions 1",
                "Descriptions 2",
                "Descriptions 3"
        };
        return descriptions[pos];
    }

}
