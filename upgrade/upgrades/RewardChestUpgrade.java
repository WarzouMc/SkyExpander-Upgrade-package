package fr.WarzouMc.MonaiServGroup.upgrade.upgrades;

import fr.WarzouMc.MonaiServGroup.Main;
import fr.WarzouMc.MonaiServGroup.upgrade.StateUpgrade;
import fr.WarzouMc.MonaiServGroup.utils.fileConfiguration.config.ConfigSetup;
import fr.WarzouMc.MonaiServGroup.utils.stringTransformer.NumbersSeparator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RewardChestUpgrade extends ConfigSetup.ChestConfig implements StateUpgrade {

    private String playerName;
    private Main main;
    private int prestige;
    private int money;
    public RewardChestUpgrade(File config, Main main, String playerName, int prestige, int money) {
        super(config, main);
        this.main = main;
        this.playerName = playerName;
        this.prestige = prestige;
        this.money = money;
    }

    @Override
    public int place() {
        return 1;
    }

    @Override
    public int maxLevel(){
        return 6;
    }

    @Override
    public ItemStack item() {
        ItemStack itemStack = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();

        String displayName = "§5§oRewardChest";
        List<String> lores = new ArrayList<>();

        if (getLevel() == maxLevel()){
            lores.add("§bMax");
        }else {
            lores.add("§eNiveau actuelle §f: §9" + getLevel());
            lores.add("");
            lores.add(((money < getPrice() || prestige < getPrestige()) ? "§c" : "§2") + getPriceStr());
        }
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lores);
        itemMeta.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    public int[][] price() {
        int[][] args =
                {
                        {1,2,3,4,5,6},
                        {3,5,8,11,16,21},
                        {100000,200000,500000,1000000,1500000,2000000}
                };
        return args;
    }

    @Override
    public String getPriceStr() {
        return new NumbersSeparator().transformer(getPrice());
    }

    @Override
    public int getPrice() {
        return price()[2][getLevel()];
    }

    @Override
    public int getPrestige() {
        return price()[1][getLevel()];
    }

    @Override
    public boolean equals(ItemStack itemStack){
        return itemStack.isSimilar(item());
    }

    @Override
    public void action() {

    }
}
