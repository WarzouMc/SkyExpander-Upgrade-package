package fr.WarzouMc.MonaiServGroup.upgrade;

import fr.WarzouMc.MonaiServGroup.Main;
import fr.WarzouMc.MonaiServGroup.upgrade.upgrades.EnderChestUpgrade;
import fr.WarzouMc.MonaiServGroup.upgrade.upgrades.RewardChestUpgrade;
import fr.WarzouMc.MonaiServGroup.utils.fileConfiguration.config.ConfigSetup;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class UpgradeGetter {

    private static Main main;
    private static Player player;

    public UpgradeGetter(Main main1, Player player1) {
        main = main1;
        player = player1;
        List();
    }

    /*
     * * * * * * *
     * * @List * *
     * * * * * * *
     */

    public int total = 2;

    public StateUpgrade ENDERCHEST;
    public StateUpgrade REWARDCHEST;

    public void List(){
        ENDERCHEST = new EnderChestUpgrade(main.getChests(), main, playerName(), prestige(), money());
        REWARDCHEST = new RewardChestUpgrade(main.getChests(), main, playerName(), prestige(), money());
    }

    /*
     * * * * * * * * *
     * * getItem() * *
     * * * * * * * * *
     */


    private List<StateUpgrade> listing(){
        List<StateUpgrade> l = new ArrayList<>();
        l.add(ENDERCHEST);
        l.add(REWARDCHEST);

        return l;
    }

    public StateUpgrade getItem(ItemStack itemStack){
        for (int i = 0; i < total; i++) {
            if (listing().get(i).equals(itemStack)) return listing().get(i);
        }
        return null;
    }

    /*
     * * * * * * * * * * * * * *
     * * @Get characteristic * *
     * * * * * * * * * * * * * *
     */

    private static String playerName(){
        return player.getName();
    }

    private static int prestige(){
        ConfigSetup.LvlConfig levelConfig = new ConfigSetup.LvlConfig(main.getLevelsConfig(), main);
        return levelConfig.getLvlGradeInt(playerName());
    }

    private static int money(){
        ConfigSetup.BaseConfig baseConfig = new ConfigSetup.BaseConfig(main.getConfig(), main);
        return baseConfig.getMoney(playerName());
    }

}
