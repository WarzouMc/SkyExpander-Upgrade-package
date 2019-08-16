package fr.WarzouMc.MonaiServGroup.upgrade;

import org.bukkit.inventory.ItemStack;

public interface StateUpgrade {
    int place();

    ItemStack item();

    int getLevel();

    int[][] price();

    String getPriceStr();

    int getPrice();

    int getPrestige();

    boolean equals(ItemStack itemStack);

    void action();
}
