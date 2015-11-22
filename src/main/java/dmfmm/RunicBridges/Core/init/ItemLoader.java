package dmfmm.RunicBridges.Core.init;

import cpw.mods.fml.common.registry.GameRegistry;
import dmfmm.RunicBridges.Core.items.LeaveDimensionToken;
import dmfmm.RunicBridges.Core.items.Rune;
import dmfmm.RunicBridges.Core.items.RuneEssence;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Originally written by mincrmatt12
 * Created by dmf44 on 10/4/2015.
 */
public class ItemLoader  {
    private static boolean IRegister=false;

    public static Item rune;
    public static Item runeEssence;
    public static Item leaveToken;

    public static Map<String, Item> RunicItems = new HashMap<>();

    public static void initiateItems() {
        rune = new Rune();
        runeEssence = new RuneEssence();
        leaveToken = new LeaveDimensionToken();




        registerItems();
    }


    private static void registerItems() {
        if(!IRegister){
            for (Field item : ItemLoader.class.getFields()){
                if (item.getType() == Item.class){
                    if (Modifier.isStatic(item.getModifiers())){
                        Object toRegister;
                        try {
                            toRegister = item.get(null);
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                            continue;
                        }
                        if (toRegister instanceof Item){
                            if (toRegister != null){
                                GameRegistry.registerItem((Item) toRegister, item.getName());
                            }
                        }
                    }

                }
            }

            for (String meat : RunicItems.keySet()) {
                GameRegistry.registerItem(RunicItems.get(meat), meat);
            }





        }
        IRegister=true;
    }
}
